package com.example.hdy.dao;

import com.example.hdy.domain.Collect;
import com.example.hdy.domain.Like;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    int addCollect(Collect collect);
    int existVideoID(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
    int countCollect(Integer videoID);
    int deleteCollect(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
    List<Collect> getCollectOfUser(Integer userID);
}
