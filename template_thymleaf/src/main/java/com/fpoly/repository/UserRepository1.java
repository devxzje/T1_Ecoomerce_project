package com.fpoly.repository;

import com.fpoly.model.Users;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserRepository1 extends CrudRepository<Users, Integer> {
}
