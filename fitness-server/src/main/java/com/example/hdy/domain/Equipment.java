package com.example.hdy.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private int id;
    private String name;
    private String function;
    private String pic;

}
