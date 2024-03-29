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
            userRepository.save(user);
        } else
            throw new RuntimeException("Wrong email or password.");
    }

    public void signout(String id) {
        var user = findById(id);

        userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id not found."));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found."));
    }

    public List<User> findAll() {
        
        if(userRepository.findAll()==null){
            throw new RuntimeException("No users found.");
        }

        return userRepository.findAll();
    }
}
