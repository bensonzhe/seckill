package com.benson.web;

import com.benson.dto.Exposer;
import com.benson.dto.SeckillExecution;
import com.benson.dto.SeckillResult;
import com.benson.entity.Seckill;
import com.benson.enums.SeckillStatusEnum;
import com.benson.exception.RepeatKillExpection;
import com.benson.exception.SeckillCloseExpection;
import com.benson.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * seckillController
 *
 * @author yaz
 * @create 2016-08-03 17:51
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill s = seckillService.getSeckillById(seckillId);
        if (s == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", s);
        return "detail";
    }


    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST)
    @ResponseBody
    public SeckillResult exposer(@PathVariable("seckillId") long seckillId) {
        SeckillResult result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(exposer, true);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(e.getMessage(), false);
            return result;
        }

    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST)
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long userPhone) {
        SeckillResult<SeckillExecution> result;
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>("未注册", false);
        }
        try {
            SeckillExecution exception = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(exception, true);
        } catch (SeckillCloseExpection seckillCloseExpection) {
            SeckillExecution exception = new SeckillExecution(seckillId, SeckillStatusEnum.END);
            return new SeckillResult<SeckillExecution>(exception, true);
        } catch (RepeatKillExpection repeatKillExpection) {
            SeckillExecution exception = new SeckillExecution(seckillId, SeckillStatusEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(exception, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExecution exception = new SeckillExecution(seckillId, SeckillStatusEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(exception, true);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date date = new Date();
        return new SeckillResult<Long>(date.getTime(), true);
    }
}
