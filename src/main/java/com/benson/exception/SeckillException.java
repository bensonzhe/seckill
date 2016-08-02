package com.benson.exception;

/**
 * 秒杀相关异常
 *
 * @author yaz
 * @create 2016-08-02 18:02
 */

public class SeckillException extends RuntimeException {
    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }
}
