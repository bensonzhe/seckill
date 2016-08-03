package com.benson.dto;

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
}
