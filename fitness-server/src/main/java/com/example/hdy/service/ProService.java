package com.example.hdy.service;

import com.example.hdy.domain.Pro;

import java.util.List;

public interface ProService {
    List<Pro> allPro();
    Pro selectByPrimaryKey(int id);
}
