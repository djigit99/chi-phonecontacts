package dev.djigit.phonecontacts.dao;

import dev.djigit.phonecontacts.entity.PhoneContactUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<PhoneContactUser, Integer> {
    Optional<PhoneContactUser> findByLogin(String login);
}
