package com.example.hdy.service;

import com.example.hdy.domain.Collect;
import com.example.hdy.domain.Like;

import java.util.List;

public interface CollectService {
    boolean addCollect(Collect collect);
    boolean existVideoID(Integer userID, Integer videoID);
    int countCollect(Integer videoID);
    int deleteCollect(Integer userID,Integer videoID);
    List<Collect> getCollectOfUser(Integer userID);
}
