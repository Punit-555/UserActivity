package com.devil.bbdproject1;

import javax.xml.namespace.QName;

public class dataUser {
    String name;
    String email;
    String phone;
    String college;
    dataUser(){

    }
    dataUser(String name,String email,String phone,String college){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.college = college;
    }
}
