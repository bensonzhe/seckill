package com.benson.dao;

import com.benson.entity.Success_killed;

/**
 * Created by yaz on 2016/7/31.
 */
public interface Success_killedDao {

    /**
     * 插入记录，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(Long seckillId , Long userPhone);

    /**
     * 根据秒杀id查询成功秒杀记录，并携带秒杀产品对象
     * @param SeckillId
     * @return
     */
    Success_killed queryByIdWithSeckill(Long SeckillId);
}
