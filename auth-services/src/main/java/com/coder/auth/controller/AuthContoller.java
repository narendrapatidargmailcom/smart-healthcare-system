package com.coder.auth.controller;

import com.coder.auth.request.LoginRequest;
import com.coder.auth.request.UserRequest;
import com.coder.auth.response.LoginResponse;
import com.coder.auth.response.MessageResponse;
import com.coder.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("http://localhost:5173")
public class AuthContoller {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody UserRequest userrequest){
        MessageResponse messageResponse =userService.createUser(userrequest);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse =userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
