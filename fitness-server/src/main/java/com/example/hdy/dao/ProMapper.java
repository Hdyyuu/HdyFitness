package com.example.hdy.dao;

import com.example.hdy.domain.Pro;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProMapper {
    List<Pro> allPro();
    Pro selectByPrimaryKey(int id);
}
