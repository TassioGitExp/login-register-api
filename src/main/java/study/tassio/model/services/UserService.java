package study.tassio.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.tassio.model.entities.User;
import study.tassio.model.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    // Injeção de dependência --->
    @Autowired
    private UserRepository userRepository;

    public UserService(){}

    // public UserService(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }
    // <---

    public User create(User user) {
        if(userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email already in use.");
        return userRepository.save(user);
    }

    public void signin(String email, String password) {
        var user = findByEmail(email);

        if(Objects.equals(user.getPassword(), password)) {
            //user.setStatus(true);
            userRepository.save(user);
        } else
            throw new RuntimeException("Wrong password.");
    }

    public void signout(String email) {
        var user = findByEmail(email);

        //user.setStatus(false);

        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found."));
    }

    public List<User> findAll() {
        
        return userRepository.findAll();
    }
}
