package com.benson.service;

import com.benson.dto.Exposer;
import com.benson.dto.SeckillExecution;
import com.benson.entity.Seckill;
import com.benson.exception.RepeatKillExpection;
import com.benson.exception.SeckillCloseExpection;
import com.benson.exception.SeckillException;

import java.util.List;

/**
 * 使用者角度设计接口
 * 1、方法定义粒度
 * 2、参数
 * 3、返回类型，尽量友好，不要使用map，或返回一个entiry，但是其部分内容为空
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(Long seckillId);

    /**
     * 秒杀开始后输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException;

}

