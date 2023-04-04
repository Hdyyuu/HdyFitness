package com.example.hdy.controller;

import com.example.hdy.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
public class ToolController {
    @Autowired
    private ToolService toolService;

    //返回所有工具
    @RequestMapping(value = "/tool", method = RequestMethod.GET)
    public Object allVideo(){
        return toolService.AllTool();
    }


}
