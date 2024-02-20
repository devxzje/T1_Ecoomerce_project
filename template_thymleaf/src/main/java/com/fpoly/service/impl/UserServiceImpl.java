package com.fpoly.service.impl;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Users;
import com.fpoly.repository.UserRepository;
import com.fpoly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl() {
        System.out.println("Fuck");
    }

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Integer id) throws NotFoundException {
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new NotFoundException("Can not find customer with id: " + id);
    }

}
