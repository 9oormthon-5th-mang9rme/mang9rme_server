package com.manggoormy.manggoormy.domain.stone.repository;

import com.manggoormy.manggoormy.domain.stone.model.Stone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoneRepository extends JpaRepository<Stone, Long> {
}
