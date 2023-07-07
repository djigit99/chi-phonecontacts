package dev.djigit.phonecontacts.security;

import dev.djigit.phonecontacts.dao.UserRepository;
import dev.djigit.phonecontacts.dto.UserDto;
import dev.djigit.phonecontacts.entity.PhoneContactUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public AuthService(JwtProvider jwtProvider, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    public Map<String, Object> createLoginInfo(PhoneContactUser user) {
        Optional<PhoneContactUser> userByLogin = userRepository.findByLogin(user.getLogin());
        if(userByLogin.isEmpty()) {
            throw new UsernameNotFoundException("User with login " + user.getLogin() + " not found.");
        }


        if (!user.getPassword().equals(userByLogin.get().getPassword())) {
            throw new BadCredentialsException("Password " + user.getPassword() + " is incorrect for user " + user.getLogin());
        }

        UserDto userDto = new UserDto();
        userDto.setId(userByLogin.get().getId());
        userDto.setLogin(userByLogin.get().getLogin());

        String token = this.jwtProvider.createToken(user.getLogin());
        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
