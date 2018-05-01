package com.test.serializable;

import java.io.Serializable;

public class SubscribeReq implements Serializable {
    private static final long seriaVersionUID =1L;

    public static long getSeriaVersionUID() {
        return seriaVersionUID;
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int subReqID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;
    public String toString(){
        return "SubscribeReq[subReqID"+subReqID+",userName="+userName+",productName="+productName+",phoneNumber="+phoneNumber+",address="+address+"]";

    }
}