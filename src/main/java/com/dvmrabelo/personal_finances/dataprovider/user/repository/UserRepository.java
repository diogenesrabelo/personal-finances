package com.dvmrabelo.personal_finances.dataprovider.user.repository;

import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}