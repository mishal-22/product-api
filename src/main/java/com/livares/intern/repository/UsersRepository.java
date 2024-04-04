package com.livares.intern.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.livares.intern.DTO.UserFetchDTO;
import com.livares.intern.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);
	
	@Query("select new com.livares.intern.DTO.UserFetchDTO(id,firstName,lastName,username) from Users")
	List<UserFetchDTO> findAllUsers();
}
