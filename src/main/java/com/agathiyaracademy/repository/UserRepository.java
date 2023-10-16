package com.agathiyaracademy.repository;

import com.agathiyaracademy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailId(String emailId);

    User findByUserName(String emailId);
}
