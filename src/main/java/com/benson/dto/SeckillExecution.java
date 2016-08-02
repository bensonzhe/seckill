package com.benson.dto;

import com.benson.entity.SuccessKilled;

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

    public SeckillExecution(long seckillId, String stateInfo, int status, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.stateInfo = stateInfo;
        this.status = status;
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, String stateInfo, int status) {
        this.seckillId = seckillId;
        this.stateInfo = stateInfo;
        this.status = status;
    }
}
