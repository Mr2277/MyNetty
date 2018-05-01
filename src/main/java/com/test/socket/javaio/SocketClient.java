package com.test.socket.javaio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static final String IP_ADDR="127.0.0.1";
    public static final int port=1234;
    public static void main(String[] args) throws IOException {

            Socket socket=null;
            socket=new Socket(IP_ADDR,port);
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            System.out.println("success");
            System.out.println(dataInputStream.readUTF());

    }
}
