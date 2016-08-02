package com.benson.service.impl;

import com.benson.dao.SeckillDao;
import com.benson.dao.SuccessKilledDao;
import com.benson.dto.Exposer;
import com.benson.dto.SeckillExecution;
import com.benson.entity.Seckill;
import com.benson.exception.SeckillException;
import com.benson.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Seckill的Service实现
 *
 * @author yaz
 * @create 2016-08-02 21:59
 */

public class SeckillServiceImpl implements SeckillService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    //盐值加密
    private final String slat = "sdfjqoiwcxvLMNJH*324sdfXMKLJI";

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    /**
     * 假设偏移量为0-10，因为数据库中只有4条数据
     *
     * @return
     */
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    public Seckill getSeckillById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = this.getSeckillById(seckillId);
        if (null == seckill) {
            return new Exposer(seckillId, false);
        }
        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < start.getTime() ||
                now.getTime() > end.getTime()) {
            return new Exposer(false, now.getTime(), end.getTime(), start.getTime(), seckillId);
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(Long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException {
        return null;
    }
}
