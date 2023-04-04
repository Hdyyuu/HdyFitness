package com.example.hdy.service.impl;

import com.example.hdy.domain.Collect;
import com.example.hdy.domain.Like;
import com.example.hdy.service.CollectService;
import com.example.hdy.dao.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;


    @Override
    public boolean addCollect(Collect collect) {
        return collectMapper.addCollect(collect) > 0 ? true:false;
    }

    @Override
    public boolean existVideoID(Integer userID, Integer videoID) {
        return collectMapper.existVideoID(userID, videoID) > 0 ? true:false;
    }

    @Override
    public int countCollect(Integer videoID) {
        return collectMapper.countCollect(videoID);
    }

    @Override
    public int deleteCollect(Integer userID, Integer videoID) {
        return collectMapper.deleteCollect(userID, videoID);
    }

    @Override
    public List<Collect> getCollectOfUser(Integer userID) {
        return collectMapper.getCollectOfUser(userID);
    }
}
