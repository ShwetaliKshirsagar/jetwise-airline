package com.jetwise_airline.user_service.repository;

import com.jetwise_airline.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   Optional<UserEntity> findByUserName(String username);

}
