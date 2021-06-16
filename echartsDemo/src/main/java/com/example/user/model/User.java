package com.example.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ToString
@Builder
public class User implements Serializable {

    private  Long id;

    private  String userName;

    private String passWord;

    private String registeredTime;


}
