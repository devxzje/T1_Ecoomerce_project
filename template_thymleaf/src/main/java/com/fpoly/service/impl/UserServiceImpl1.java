package com.fpoly.service.impl;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Users;
import com.fpoly.repository.UserRepository1;
import com.fpoly.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl1 implements UserService1 {

    @Autowired
    UserRepository1 userRepository;

    public UserServiceImpl1() {
        System.out.println("Fuck");
    }

    @Override
    public List<Users> getAll() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public Users findById(Integer id) throws NotFoundException {
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new NotFoundException("Can not find customer with id: " + id);
    }

    public static void main(String[] args) {
        try {
            Users user = new UserServiceImpl1().findById(2);
            System.out.println(user.toString());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
