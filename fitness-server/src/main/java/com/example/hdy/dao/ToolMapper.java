package com.example.hdy.dao;

import com.example.hdy.domain.Tool;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolMapper {
    List<Tool> AllTool();
}
