package com.example.hdy.service;

import com.example.hdy.domain.Like;

public interface LikeService {
    boolean addLike(Like like);
    boolean existVideoID(Integer userID, Integer videoID);
    int countLike(Integer videoID);
    int deleteLike(Integer userID,Integer videoID);
}
