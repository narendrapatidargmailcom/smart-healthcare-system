package healthcare.controller;

import healthcare.request.UserRequest;
import healthcare.response.MessageResponse;
import healthcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody UserRequest userrequest){
       MessageResponse messageResponse =userService.createUser(userrequest);
       return ResponseEntity.ok(messageResponse);
    }
}
