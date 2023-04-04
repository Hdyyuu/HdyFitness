package com.example.hdy.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hdy.domain.Collect;
import com.example.hdy.domain.Like;
import com.example.hdy.service.impl.CollectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;

@RestController
@Controller
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

//    添加收藏关系
    @ResponseBody
    @RequestMapping(value = "/collect/add", method = RequestMethod.POST)
    public Object addLike(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String userID = req.getParameter("userID");
        String videoID = req.getParameter("videoID");
        Collect collect = new Collect();
        collect.setUserID(Integer.parseInt(userID));
        collect.setVideoID(Integer.parseInt(videoID));
        boolean res = collectService.addCollect(collect);
        jsonObject.put("id",collect.getId());
        return jsonObject;
}

    //    返回收藏数
    @RequestMapping(value = "/collect/count", method = RequestMethod.GET)
    public Object countLike(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String videoID = req.getParameter("videoID");
        int collectCount = collectService.countCollect(Integer.parseInt(videoID));
        System.out.println(collectCount);
        jsonObject.put("collectCount",collectCount);
        return jsonObject;
    }

    //    删除收藏
    @RequestMapping(value = "/collect/delete", method = RequestMethod.GET)
    public Object deleteLike(HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        return collectService.deleteCollect(Integer.parseInt(userID), Integer.parseInt(videoID));
    }
    //    是否存在
    @RequestMapping(value = "/collect/exist", method = RequestMethod.GET)
    public boolean existVideoID (HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        return collectService.existVideoID(Integer.parseInt(userID), Integer.parseInt(videoID));
    }
    //    返回收藏
    @RequestMapping(value = "/collect/detail", method = RequestMethod.GET)
    public Object getCollectionOfUser (HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        return collectService.getCollectOfUser(Integer.parseInt(userID));
    }
}

