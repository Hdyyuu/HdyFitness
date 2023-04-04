package com.example.hdy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    private Integer id;

    private String title;

    private String introduction;

    private String name;

    private String type;

    private String pic;

    private String url;

    private Integer likeCount;
}
