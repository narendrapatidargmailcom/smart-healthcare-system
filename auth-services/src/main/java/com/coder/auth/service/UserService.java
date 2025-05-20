package com.coder.auth.service;


import com.coder.auth.model.Role;
import com.coder.auth.model.User;
import com.coder.auth.model.UserRole;
import com.coder.auth.repository.RoleRepository;
import com.coder.auth.repository.UserRepository;
import com.coder.auth.request.LoginRequest;
import com.coder.auth.request.UserRequest;
import com.coder.auth.response.LoginResponse;
import com.coder.auth.response.MessageResponse;
import com.coder.auth.security.jwt.JwtUtils;
import com.coder.auth.security.services.UserDetailsImpl;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MessageResponse createUser(UserRequest userrequest) {

         if(userRepository.existsByUsername(userrequest.getUsername())){
           return  new MessageResponse("user alredy exist in System");
          }

        if(userRepository.existsByEmail(userrequest.getEmail())){
            return  new MessageResponse("Email alredy exist in System");
        }

        User user = new User(userrequest.getEmail(),userrequest.getUsername(), passwordEncoder.encode(userrequest.getPassword()));

        Set<UserRole> strrole =userrequest.getRoles();

        Set<Role> roles = new HashSet<>();

        if(strrole== null){
           Role role= roleRepository.findByName(UserRole.ROLE_PATIENT)
                                     .orElseThrow(()-> new RuntimeException("Exception:Role not found"));
           roles.add(role);
        }else {
            strrole.forEach(role->{
                 switch (role.toString()){
                     case  "admin":
                         Role adminrole= roleRepository.findByName(UserRole.ROLE_PATIENT)
                                 .orElseThrow(()-> new RuntimeException("Exception:Admin Role not found"));
                         roles.add(adminrole);
                         break;
                     case "doctor" :
                         Role doctorrole= roleRepository.findByName(UserRole.ROLE_PATIENT)
                                 .orElseThrow(()-> new RuntimeException("Exception:Admin Role not found"));
                         roles.add(doctorrole);
                         break;
                     default:
                         Role defaulrole= roleRepository.findByName(UserRole.ROLE_PATIENT)
                                 .orElseThrow(()-> new RuntimeException("Exception: Role not found"));
                         roles.add(defaulrole);

                 }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
          return new MessageResponse("User Registered Successfully");
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {

      Authentication authentication =authenticationManager.
                  authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

       LoginResponse loginResponse = new LoginResponse(jwt,null,userPrincipal.getId().toString(),userPrincipal.getUsername(),userPrincipal.getEmail(),roles);

        return loginResponse;
    }
}
