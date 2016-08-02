package com.benson.dao;

import com.benson.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yaz on 2016/7/31.
 */
public interface SuccessKilledDao {

    /**
     * 插入记录，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") Long seckillId ,@Param("userPhone") Long userPhone);

    /**
     * 根据秒杀id查询成功秒杀记录，并携带秒杀产品对象
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
