package com.benson.service.impl;

import com.benson.dto.Exposer;
import com.benson.dto.SeckillExecution;
import com.benson.entity.Seckill;
import com.benson.exception.SeckillException;
import com.benson.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yaz on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List list = seckillService.getSeckillList();
        logger.info("list={}", list);
        // Closing non transactional SqlSession
    }

    @Test
    public void getSeckillById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.getSeckillById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000;
        long userPhone = 18511198888L;
        String md5 = "019abd9848ab36c30a75bbd92ce7821a";
        SeckillExecution exception = seckillService.executeSeckill(id, userPhone, md5);
        logger.info("exception={}", exception);
    }

}