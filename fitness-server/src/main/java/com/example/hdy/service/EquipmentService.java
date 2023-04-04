package com.example.hdy.service;

import com.example.hdy.domain.Equipment;
import com.example.hdy.domain.Video;

import java.util.List;

public interface EquipmentService {
    List<Equipment> AllEquipment();
    List<Equipment> EquipmentOfFunc(String func);
}
