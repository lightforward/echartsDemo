package com.example.user.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private  Long id;

    private  String userName;

    private String passWord;

    private String registeredTime;


}
