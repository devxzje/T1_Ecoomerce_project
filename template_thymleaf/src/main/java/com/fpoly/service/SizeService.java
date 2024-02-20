package com.fpoly.service;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Size;

public interface SizeService {

    public Size findById(Integer id) throws NotFoundException;
}
