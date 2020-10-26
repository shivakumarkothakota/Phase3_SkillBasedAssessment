package com.rest.its.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.its.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
