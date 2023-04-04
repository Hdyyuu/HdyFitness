package com.example.hdy.controller;

import com.example.hdy.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
public class ProController {
    @Autowired
    private ProService proService;

    //返回所有工具
    @RequestMapping(value = "/pro", method = RequestMethod.GET)
    public Object allPro() {
        return proService.allPro();
    }

    //    根据歌手名查找歌手
    @RequestMapping(value = "/singer/id/detail", method = RequestMethod.GET)
    public Object proOfId(HttpServletRequest req) {
        int id = Integer.getInteger(req.getParameter("id"));
        return proService.selectByPrimaryKey(id);
    }
}
