package com.example.hdy.controller;

import com.example.hdy.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;

    //返回所有视频
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public Object allVideo(){
        return videoService.AllVideo();
    }
    //返回指定视频ID的视频
    @RequestMapping(value = "/video/detail", method = RequestMethod.GET)
    public Object videoOfId(HttpServletRequest req){
        String id = req.getParameter("id");
        return videoService.videoOfId(Integer.parseInt(id));
    }
    //返回指定视频Type的视频
    @RequestMapping(value = "/video/type/detail", method = RequestMethod.GET)
    public Object videoOfType(HttpServletRequest req){
        String type = req.getParameter("type").trim();
        return videoService.SelectByType(type);
    }
    //返回指定视频name的视频
    @RequestMapping(value = "/video/name/detail", method = RequestMethod.GET)
    public Object videoOfName(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return videoService.SelectByName(name);
    }
    //搜索视频
    @RequestMapping(value = "/video/search", method = RequestMethod.GET)
    public Object searchVideo(HttpServletRequest req){
        String keywords = req.getParameter("keywords").trim();
        System.out.println(keywords);
        System.out.println(videoService.SearchVideo(keywords));
        return videoService.SearchVideo(keywords);
    }
    //点赞加一
    @RequestMapping(value = "/video/like/detail", method = RequestMethod.GET)
    public Object updateVideoLikeCount(HttpServletRequest req){
        String id = req.getParameter("id");
        String num = req.getParameter("num");
        return videoService.updateVideoLikeCount(Integer.parseInt(id),Integer.parseInt(num));
    }

}
