package com.test.serializable;

import java.io.Serializable;

public class SubscibeResp implements Serializable {
    private int subReqID;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private int respCode;
    private String desc;
    public String toString(){
        return "SubscibeResp[subReqID="+subReqID+",respCode"+respCode+",desc="+desc+"]";
    }
}
