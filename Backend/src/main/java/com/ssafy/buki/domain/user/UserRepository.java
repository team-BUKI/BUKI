package com.ssafy.buki.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findOneByEmail(String email);
    //User findByUsername(String username);
}
