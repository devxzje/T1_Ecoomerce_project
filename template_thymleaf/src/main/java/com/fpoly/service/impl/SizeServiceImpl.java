package com.fpoly.service.impl;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Size;
import com.fpoly.repository.SizeRepository;
import com.fpoly.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;


    @Override
    public Size findById(Integer id) throws NotFoundException {
        Optional<Size> size = sizeRepository.findById(id);
        if(size.isPresent()){
            return size.get();
        }
        throw new NotFoundException("Can not find size with id: "+id);
    }
}
