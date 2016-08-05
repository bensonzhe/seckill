package com.benson.dto;

import com.benson.entity.SuccessKilled;
import com.benson.enums.SeckillStatusEnum;

/**
 * 秒杀后的结果
 *
 * @author yaz
 * @create 2016-08-02 17:56
 */

public class SeckillExecution {
    private long seckillId;
    //状态表示
    private int status;
    //明文显示
    private String stateInfo;
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatusEnum statusEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.stateInfo = statusEnum.getStateInfo();
        this.status = statusEnum.getState();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatusEnum statusEnum) {
        this.seckillId = seckillId;
        this.stateInfo = statusEnum.getStateInfo();
        this.status = statusEnum.getState();
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", status=" + status +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
