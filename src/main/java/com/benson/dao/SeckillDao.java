package com.benson.dao;

import com.benson.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yaz on 2016/7/31.
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") Long seckillId ,@Param("killTime") Date killTime);

    /**
     * 根据主键，查询单个
     * @param seckillId
     * @return
     */
    Seckill queryById(Long seckillId);

    /**
     * 分页查询所有
     * @param offset
     * @param limit
     * @return
     */
    List queryAll(@Param("offset") int offset ,@Param("limit") int limit );
}
