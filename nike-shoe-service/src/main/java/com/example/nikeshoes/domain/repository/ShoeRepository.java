package com.example.nikeshoes.domain.repository;

import com.example.nikeshoes.domain.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {
}