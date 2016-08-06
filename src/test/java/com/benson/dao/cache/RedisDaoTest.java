package com.benson.dao.cache;

import com.benson.dao.SeckillDao;
import com.benson.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.awt.Color.red;
import static org.junit.Assert.*;

/**
 * Created by yaz on 2016/8/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:spring/spring-dao.xml"}
)
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;

    /**
     * 全局测试
     *
     * @throws Exception
     */
    @Test
    public void testSeckill() throws Exception {
        Long id = 1000L;
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                System.out.println("向redis中赋值");
                redisDao.putSeckill(seckill);
            }
        } else {
            System.out.println("redis中读取的：" + seckill);
        }

    }


}