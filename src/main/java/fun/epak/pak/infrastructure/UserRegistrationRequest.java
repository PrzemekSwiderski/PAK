package fun.epak.pak.infrastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegistrationRequest {
    private final String email;
    private final String password;
    private final String username;
    private final String imageAddress;
}
