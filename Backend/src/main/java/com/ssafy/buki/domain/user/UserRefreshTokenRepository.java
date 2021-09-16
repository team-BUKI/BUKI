package com.ssafy.buki.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(Long userId);
    UserRefreshToken findByUserIdAndRefreshToken(Long userId, String refreshToken);
}
