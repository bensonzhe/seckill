package com.benson.enums;

/**
 * 秒杀常量
 * 使用枚举(数据字典)
 *
 * @author yaz
 * @create 2016-08-03 11:18
 */

public enum SeckillStatusEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;
    private String stateInfo;

    SeckillStatusEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatusEnum statOf(int index) {
        for (SeckillStatusEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
