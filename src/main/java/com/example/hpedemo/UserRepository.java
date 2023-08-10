package com.example.hpedemo;

import com.redis.om.spring.repository.RedisDocumentRepository;

public interface UserRepository extends RedisDocumentRepository<User,String> {
}
