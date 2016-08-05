package com.benson.dto;

import java.io.Serializable;

/**
 * 暴露秒杀接口dto
 *
 * @author yaz
 * @create 2016-08-02 17:48
 */

public class Exposer {
    //是否开启秒杀
    private boolean exposed;
    //加密值
    private String md5;
    //秒杀id
    private long seckillId;
    //系统时间【单位统一：毫秒】
    private long now;
    //秒杀开始时间
    private long start;
    //秒杀结束时间
    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(long seckillId, boolean exposed) {
        this.seckillId = seckillId;
        this.exposed = exposed;
    }

    public Exposer(boolean exposed, long now, long end, long start, long seckillId) {
        this.exposed = exposed;
        this.now = now;
        this.end = end;
        this.start = start;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "end=" + end +
                ", exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                '}';
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
