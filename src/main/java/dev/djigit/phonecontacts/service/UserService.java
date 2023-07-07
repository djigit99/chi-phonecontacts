package dev.djigit.phonecontacts.service;

import dev.djigit.phonecontacts.dao.UserRepository;
import dev.djigit.phonecontacts.entity.PhoneContactUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<PhoneContactUser> findAllUsers() {
        return userRepository.findAll();
    }
    public PhoneContactUser findUserByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow();
    }

    public PhoneContactUser saveUser(PhoneContactUser user) {
        return userRepository.save(user);
    }

}
