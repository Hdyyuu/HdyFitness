package com.example.hdy.service.impl;

import com.example.hdy.dao.ProMapper;
import com.example.hdy.domain.Pro;
import com.example.hdy.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProServiceImpl implements ProService {
    @Autowired
    private ProMapper proMapper;

    @Override
    public List<Pro> allPro() {
        return proMapper.allPro();
    }

    @Override
    public Pro selectByPrimaryKey(int id) {
        return proMapper.selectByPrimaryKey(id);
    }
}
