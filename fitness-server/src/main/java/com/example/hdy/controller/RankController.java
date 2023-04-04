package com.example.hdy.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hdy.domain.Rank;
import com.example.hdy.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

//    提交评分
    @ResponseBody
    @RequestMapping(value = "/rank/add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        String score = req.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setUserID(Integer.parseInt(userID));
        rank.setVideoID(Integer.parseInt(videoID));
        rank.setScore(Integer.parseInt(score));

        boolean res = rankService.addRank(rank);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

    //    获取总评分
    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Object rankOfVideoId(HttpServletRequest req){
        String videoID = req.getParameter("videoID");
        return rankService.rankOfVideoId(Integer.parseInt(videoID));
    }
    //    获取单个评分
    @RequestMapping(value = "/rank/person", method = RequestMethod.GET)
    public Object rankOfOne(HttpServletRequest req){
        String videoID = req.getParameter("videoID");
        String userID = req.getParameter("userID");
        return rankService.selectOneScore(Integer.parseInt(userID),Integer.parseInt(videoID));
    }

    //    是否存在
    @RequestMapping(value = "/rank/exist", method = RequestMethod.GET)
    public boolean isRanked (HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        return rankService.IsRanked(Integer.parseInt(userID), Integer.parseInt(videoID));
    }

    //删除
    @RequestMapping(value = "/rank/delete", method = RequestMethod.GET)
    public boolean deleteRank (HttpServletRequest req){
        String userID = req.getParameter("userID").trim();
        String videoID = req.getParameter("videoID").trim();
        return rankService.deleteRank(Integer.parseInt(userID), Integer.parseInt(videoID));
    }
}
