package com.example.hdy.service.impl;

import com.example.hdy.dao.RankMapper;
import com.example.hdy.service.RankService;
import com.example.hdy.domain.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;



    @Override
    public int rankOfVideoId(int videoID) {
        if(rankMapper.selectRankNum(videoID)!=0)
            return rankMapper.selectScoreSum(videoID) / rankMapper.selectRankNum(videoID);
        else
            return 0;
    }

    @Override
    public boolean addRank(Rank rank) {

        return rankMapper.insertSelective(rank) > 0 ? true:false;
    }

    @Override
    public boolean IsRanked(Integer userID, Integer videoID) {
        return rankMapper.isRanked(userID, videoID) > 0 ? true:false;
    }

    @Override
    public int selectOneScore(Integer userID, Integer videoID) {
        return rankMapper.selectOneScore(userID, videoID);
    }

    @Override
    public boolean deleteRank(Integer userID, Integer videoID) {
        return rankMapper.deleteRank(userID, videoID) > 0 ? true:false;
    }
}
