package com.benson.dao;

import com.benson.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


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
    public void queryById() throws Exception {
        long id = 1000;
        Seckill s = seckillDao.queryById(id);
        System.out.println(s);
    }

    @Test
    public void queryAll() throws Exception {
        List list = seckillDao.queryAll(0, 100);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        long id = 1000;
        int updateCount = seckillDao.reduceNumber(id, killTime);
        System.out.println(updateCount);
    }
}