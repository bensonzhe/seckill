package com.benson.entity;

import java.util.Date;

/**
 * 成功秒杀明细表
 *
 * @author yaz
 * @create 2016-07-31 16:52
 */

public class SuccessKilled {
    private long seckillId;

    private long userPhone;

    private short status;

    private Date createTime;

    //变通
    //意味着 多对一
    private Seckill seckill;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "createTime=" + createTime +
                ", seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", status=" + status +
                ", seckill=" + seckill +
                '}';
    }
}
