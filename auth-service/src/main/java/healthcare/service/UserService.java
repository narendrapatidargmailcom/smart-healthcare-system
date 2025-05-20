package healthcare.service;

import healthcare.model.User;
import healthcare.repository.UserRepository;
import healthcare.request.UserRequest;
import healthcare.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public MessageResponse createUser(UserRequest userrequest) {
        User user = new User();
return null;
    }
}
