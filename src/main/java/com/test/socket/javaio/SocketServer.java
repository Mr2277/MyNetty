package com.test.socket.javaio;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static final int port=1234;
    public void init() throws IOException {
        ServerSocket serverSocket=new ServerSocket(port);
        while (true){
            Socket client=serverSocket.accept();
            System.out.println("success");
            DataOutputStream out=new DataOutputStream(client.getOutputStream());
            out.writeUTF("rrrr");
        }
    }
    public static void main(String[] args) throws IOException {
        new SocketServer().init();
    }
}
