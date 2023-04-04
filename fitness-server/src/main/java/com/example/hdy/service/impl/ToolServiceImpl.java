package com.example.hdy.service.impl;

import com.example.hdy.dao.ToolMapper;
import com.example.hdy.domain.Tool;
import com.example.hdy.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private ToolMapper toolMapper;

    @Override
    public List<Tool> AllTool() {
        return toolMapper.AllTool();
    }
}
