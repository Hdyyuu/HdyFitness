package com.example.hdy.service.impl;

import com.example.hdy.dao.EquipmentMapper;
import com.example.hdy.domain.Equipment;
import com.example.hdy.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> AllEquipment() {
        return equipmentMapper.selectAll();
    }

    @Override
    public List<Equipment> EquipmentOfFunc(String func) {
        return equipmentMapper.selectByFunc(func);
    }
}
