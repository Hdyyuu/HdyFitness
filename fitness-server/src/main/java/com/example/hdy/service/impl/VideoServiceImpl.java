package com.example.hdy.service.impl;

import com.example.hdy.dao.VideoMapper;
import com.example.hdy.domain.Video;
import com.example.hdy.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Video videoOfId(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Video> AllVideo() {
        return videoMapper.selectAll();
    }

    @Override
    public List<Video> SelectByType(String type) {
        return videoMapper.selectByType(type);
    }

    @Override
    public List<Video> SelectByName(String name) {
        return videoMapper.selectByName(name);
    }

    @Override
    public List<Video> SearchVideo(String keywords) {
        return videoMapper.searchVideo(keywords);
    }

    @Override
    public int updateVideoLikeCount(Integer id,Integer num) {
        return videoMapper.updateVideoLikeCount(id,num);
    }



}
