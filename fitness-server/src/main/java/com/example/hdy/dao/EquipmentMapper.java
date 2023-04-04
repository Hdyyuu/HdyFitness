package com.example.hdy.dao;

import com.example.hdy.domain.Equipment;
import com.example.hdy.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentMapper {
    List<Equipment> selectAll();
    List<Equipment> selectByFunc(String func);
}
