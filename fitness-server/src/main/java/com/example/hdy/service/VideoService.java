package com.example.hdy.service;

import com.example.hdy.domain.Video;

import java.util.List;

public interface VideoService {
    Video videoOfId(Integer id);
    List<Video> AllVideo();
    List<Video> SelectByType(String type);
    List<Video> SelectByName(String name);
    List<Video> SearchVideo(String keywords);
    int updateVideoLikeCount(Integer id,Integer num);
}
