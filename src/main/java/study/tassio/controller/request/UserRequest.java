package study.tassio.controller.request;

import lombok.Setter;
import study.tassio.model.entities.User;

@Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    private boolean online;

    public User toUser(){
        var user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setOnline(online);

        return user;
    }
}
