package com.ssafy.buki.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findOneByEmail(String email);
    User findUserByNickname(String nickname);

    @Modifying
    @Query("Update User u SET u.nickname = ?2 where u.id = ?1")
    void updateNickname(Long id, String nickname);

    void deleteById(Long id);
}
