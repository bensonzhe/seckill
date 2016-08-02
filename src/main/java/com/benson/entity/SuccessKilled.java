package com.benson.entity;

import java.util.Date;

/**
 * 成功秒杀明细表
 *
 * @author yaz
 * @create 2016-07-31 16:52
 */

public class SuccessKilled {
    private long skilledId;

    private long userphone;

    private short status;

    private Date createDate;

    //变通
    //意味着 多对一
    private  Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getSkilledId() {
        return skilledId;
    }

    public void setSkilledId(long skilledId) {
        this.skilledId = skilledId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public long getUserphone() {
        return userphone;
    }

    public void setUserphone(long userphone) {
        this.userphone = userphone;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "createDate=" + createDate +
                ", skilledId=" + skilledId +
                ", userphone=" + userphone +
                ", status=" + status +
                '}';
    }
}
