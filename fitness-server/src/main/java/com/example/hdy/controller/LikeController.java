package com.example.hdy.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.hdy.domain.Like;
import com.example.hdy.service.impl.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller

public class LikeController {
    @Autowired
    private LikeServiceImpl likeService;

    //    添加点赞关系
    @ResponseBody
    @RequestMapping(value = "/like/add", method = RequestMethod.POST)
    public Object addLike(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String userID = req.getParameter("userID");
        String videoID = req.getParameter("videoID");
       /* if (likeService.existVideoID(Integer.parseInt(userID), Integer.parseInt(videoID))) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "已收藏");
            return jsonObject;
        }*/
        Like like = new Like();
        like.setUserID(Integer.parseInt(userID));
        like.setVideoID(Integer.parseInt(videoID));

        boolean res = likeService.addLike(like);
        jsonObject.put("id",like.getId());
        return jsonObject;
    }

    //    返回点赞数
    @RequestMapping(value = "/like/count", method = RequestMethod.GET)
    public Object countLike(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String videoID = req.getParameter("videoID");
        int likeCount = likeService.countLike(Integer.parseInt(videoID));
        jsonObject.put("likeCount",likeCount);
        return jsonObject;
    }

    //    返回点赞数
    @RequestMapping(value = "/like/delete", method = RequestMethod.GET)
    public Object deleteLike(HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        return likeService.deleteLike(Integer.parseInt(userID), Integer.parseInt(videoID));
    }
    //    是否存在
    @RequestMapping(value = "/like/exist", method = RequestMethod.GET)
    public boolean existVideoID (HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        return likeService.existVideoID(Integer.parseInt(userID), Integer.parseInt(videoID));
    }

}
