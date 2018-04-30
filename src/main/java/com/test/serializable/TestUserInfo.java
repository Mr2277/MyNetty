package com.test.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info=new UserInfo();
        info.buildUserID(100).buildUserName("welcome to netty");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(info);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] bytes=byteArrayOutputStream.toByteArray();
        System.out.println("The jdk serializable length is :"+ bytes.length);
        byteArrayOutputStream.close();
        System.out.println("The byte array length is :"+info.codeC().length);

    }
}
