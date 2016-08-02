package com.benson.exception;

/**
 * 秒杀关闭异常
 *
 * @author yaz
 * @create 2016-08-02 18:01
 */

public class SeckillCloseExpection extends SeckillException {
    public SeckillCloseExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseExpection(String message) {

        super(message);
    }
}
