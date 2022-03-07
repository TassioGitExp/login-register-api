package study.tassio.controller.response;

import lombok.Getter;
import study.tassio.model.entities.User;

@Getter
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    //private boolean status;

    public UserResponse fromUser(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dateOfBirth = user.getDateOfBirth();
        //this.status = user.isStatus();

        return this;
    }
}
