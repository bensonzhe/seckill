package com.benson.service.impl;

import com.benson.dao.SeckillDao;
import com.benson.dao.SuccessKilledDao;
import com.benson.dto.Exposer;
import com.benson.dto.SeckillExecution;
import com.benson.entity.Seckill;
import com.benson.entity.SuccessKilled;
import com.benson.enums.SeckillStatusEnum;
import com.benson.exception.RepeatKillExpection;
import com.benson.exception.SeckillCloseExpection;
import com.benson.exception.SeckillException;
import com.benson.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Seckill的Service实现
 *
 * @author yaz
 * @create 2016-08-02 21:59
 */
//如果不知道是什么功能，可以使用 @Component，代表是spring的一个组件
@Service
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
        //使用redis缓存起来，降低数据库压力
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

    /**
     * md5加密
     * 测试：1000
     * 加密后：019abd9848ab36c30a75bbd92ce7821a
     */
    private String getMD5(Long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 使用注解的优点：
     * 1、开发团队达成约定：明确事务编程；
     * 2、保证事务代码执行时间尽可能短；
     * 3、不要穿插其他网络操作，如RPC/HTTP请求，或者剥离到事务方法外；
     * 4、不是所有方法都需要事务，如查询或单挑数据的增删改；
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException {
        //1、验证md5；
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite!");
        }
        //2、执行秒杀； 减库存，增加秒杀明细
        Date now = new Date();
        //减库存
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, now);
            if (updateCount <= 0) {
                throw new SeckillCloseExpection("seckill is close!");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillExpection("seckill repeat!");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatusEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseExpection seckillCloseExpection) {
            throw seckillCloseExpection;
        } catch (RepeatKillExpection repeatKillExpection) {
            throw repeatKillExpection;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //编译期异常转换为运行时异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

}
