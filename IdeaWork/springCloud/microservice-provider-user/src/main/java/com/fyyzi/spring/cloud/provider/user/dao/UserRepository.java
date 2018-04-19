package com.fyyzi.spring.cloud.provider.user.dao;

import com.fyyzi.spring.cloud.provider.user.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
