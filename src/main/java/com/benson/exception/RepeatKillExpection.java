package com.benson.exception;

/**
 * 重复秒杀异常（运行期异常）
 *
 * @author yaz
 * @create 2016-08-02 17:59
 */

public class RepeatKillExpection extends SeckillException {
    public RepeatKillExpection(String message) {
        super(message);
    }

    public RepeatKillExpection(String message, Throwable cause) {
        super(message, cause);
    }
}
