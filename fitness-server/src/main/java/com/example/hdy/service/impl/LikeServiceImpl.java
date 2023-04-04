package com.example.hdy.service.impl;

import com.example.hdy.dao.LikeMapper;
import com.example.hdy.domain.Like;
import com.example.hdy.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Override
    public boolean addLike(Like like) {
        return likeMapper.addLike(like) > 0 ? true:false;
    }

    @Override
    public boolean existVideoID(Integer userID, Integer videoID) {
        return likeMapper.existVideoID(userID,videoID) > 0 ? true:false;
    }

    @Override
    public int countLike(Integer videoID) {
        return likeMapper.countLike(videoID);
    }

    @Override
    public int deleteLike(Integer userID,Integer videoID) {
        return likeMapper.deleteLike(userID,videoID);
    }
}
