package fun.epak.pak.infrastructure;

import lombok.Value;

@Value
public class ChangeUserDataRequest {
    String username;
    String password;
}
