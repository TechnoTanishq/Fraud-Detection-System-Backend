package com.tksolutions.astraguard.repository;

import com.tksolutions.astraguard.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByUpiId(String upiId);

    boolean existsByUpiId(String upiId);
}
