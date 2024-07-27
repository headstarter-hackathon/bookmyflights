package com.excelr.bookmyflights.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.excelr.bookmyflights.model.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByName(String username);
}