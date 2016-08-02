package com.benson.dao;

import com.benson.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by yaz on 2016/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:spring/spring-dao.xml"}
)
public class SeckillDaoTest {

    @Autowired
    private  SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {

    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill s = seckillDao.queryById(id);
        System.out.println(s);
    }

    @Test
    public void queryAll() throws Exception {

    }

}