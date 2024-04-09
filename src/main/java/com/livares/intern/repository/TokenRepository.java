package com.livares.intern.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livares.intern.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
	Optional<Token> findByToken(String token);
}
