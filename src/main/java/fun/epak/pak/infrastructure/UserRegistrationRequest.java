package fun.epak.pak.infrastructure;

import lombok.Value;

@Value
public class UserRegistrationRequest {
    String email;
    String password;
    String username;
}
