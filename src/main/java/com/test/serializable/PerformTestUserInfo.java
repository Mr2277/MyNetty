package com.test.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class PerformTestUserInfo {
    public static void main(String[] srgs) throws IOException {
        UserInfo info=new UserInfo();
        info.buildUserID(100).buildUserName("Welcome to Netty");
        int loop=1000000;
        ByteArrayOutputStream byteArrayOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        long startTime=System.currentTimeMillis();
        for(int i=0;i<loop;i++){
            byteArrayOutputStream=new ByteArrayOutputStream();
            objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(info);
            objectOutputStream.flush();
            objectOutputStream.close();
            byte[]bytes=byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        long endTime =System.currentTimeMillis();
        System.out.println("The jdk :"+(endTime-startTime));
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        startTime=System.currentTimeMillis();
        for(int i=0;i<loop;i++){
            byte[] bytes=info.codeC();
        }
        endTime=System.currentTimeMillis();
        System.out.println("The byte "+(endTime - startTime));
    }
}
