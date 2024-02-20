package com.fpoly.service.impl;

import com.fpoly.model.ShoppingCartDetail;
import com.fpoly.repository.ShoppingCartDetailRepository;
import com.fpoly.repository.ShoppingCartRepository;
import com.fpoly.service.ShoppingCartDetailService;
import com.fpoly.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartDetailServiceImpl implements ShoppingCartDetailService {

    @Autowired
    ShoppingCartDetailRepository shoppingCartDetailRepository;

    @Override
    public List<ShoppingCartDetail> getAll() {
        return shoppingCartDetailRepository.findAll();
    }
}
