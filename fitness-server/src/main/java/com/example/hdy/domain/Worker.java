package com.example.hdy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    private int id;

    private String name;

    private String department;

    private String position;

    private int age;

    private String  sex;

    private String phone;

    private String password;

    private int level;

}

