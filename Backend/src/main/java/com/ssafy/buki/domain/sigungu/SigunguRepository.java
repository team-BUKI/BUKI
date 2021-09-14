package com.ssafy.buki.domain.sigungu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SigunguRepository extends JpaRepository<Sigungu, Integer> {
    List<Sigungu> findBySidoId(Integer sidoId);
}
