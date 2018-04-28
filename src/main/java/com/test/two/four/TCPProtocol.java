package com.test.two.four;
import java.nio.channels.SelectionKey;
import java.io.IOException;
public interface TCPProtocol {
    //accept I/O形式
    void handleAccept(SelectionKey key) throws IOException;
    //read I/O形式
    void handleRead(SelectionKey key) throws IOException;
    //write I/O形式
    void handleWrite(SelectionKey key) throws IOException;
}
