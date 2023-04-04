package com.example.hdy.dao;

import com.example.hdy.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoMapper {
    Video selectByPrimaryKey(Integer id);
    List<Video> selectAll();
    List<Video> selectByType(String type);
    List<Video> selectByName(String name);
    List<Video> searchVideo(String keywords);
    int updateVideoLikeCount(Integer id,Integer num);
}
