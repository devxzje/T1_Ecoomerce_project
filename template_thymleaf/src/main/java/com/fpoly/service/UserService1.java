package com.fpoly.service;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Users;

import java.util.List;

public interface UserService1 {

    public List<Users> getAll();

    public Users findById(Integer id) throws NotFoundException;
}
