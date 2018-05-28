package com.fyyzi.seckill.controller;

import com.fyyzi.seckill.beans.Seckill;
import com.fyyzi.seckill.dto.ExportUrl;
import com.fyyzi.seckill.dto.SeckillExecution;
import com.fyyzi.seckill.dto.SeckillReturn;
import com.fyyzi.seckill.enums.SeckillStateEnum;
import com.fyyzi.seckill.exceptions.SeckillAbsException;
import com.fyyzi.seckill.exceptions.SeckillClosedException;
import com.fyyzi.seckill.exceptions.SeckillDataRewriteException;
import com.fyyzi.seckill.exceptions.SeckillRepeatException;
import com.fyyzi.seckill.services.SeckillService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/seckill")
public class SeckillController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取集合
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public List<Seckill> list(Model model) {
        List<Seckill> seckillList = seckillService.getSeckillList();
//        model.addAttribute("list",seckillList);
        return seckillList;
    }

    /**
     * 获取秒杀对象
     *
     * @param seckillId
     * @param model
     * @return
     */
    @GetMapping(value = "/{seckillId}/detail")
    public Object detail(@PathVariable(value = "seckillId", required = true) Long seckillId, Model model) {

        if (seckillId == null) {
            return "redirect:/seckill/list";
        }

        Seckill seckillByPrimaryKey = seckillService.getSeckillByPrimaryKey(seckillId);
        if (seckillByPrimaryKey == null) {
            return "forward:/seckill/list";
        }

        return seckillByPrimaryKey;
    }

    /**
     * 获取秒杀地址
     *
     * @param seckillId
     * @return
     */
    @PostMapping(value = "/{seckillId}/exposer")
    public SeckillReturn<ExportUrl> exposer(@PathVariable(value = "seckillId") Long seckillId) {
        SeckillReturn result;
        ExportUrl exportUrl = null;
        try {
            exportUrl = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillReturn(true, exportUrl);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillReturn(false, e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/cookie")
    public Cookie getCookies(HttpServletResponse response){
        Cookie userPhone = new Cookie("userPhone", "" + 123123);
        userPhone.setMaxAge(60*60);
        response.addCookie(userPhone);
        return userPhone;
    }


    /**
     * 秒杀动作
     */
    @PostMapping(value = "/{seckillId}/{md5}/execution")
    public SeckillReturn<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable(value = "md5",required = false) String md5,
                                                   @CookieValue(value = "userPhone", required = false) Long userPhone) {
        SeckillReturn seckillReturn;

        if (userPhone == null) {
            seckillReturn = new SeckillReturn(false, "null userPhone");
            return seckillReturn;
        }

        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            seckillReturn = new SeckillReturn(true, seckillExecution);
        } catch (SeckillRepeatException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPETA_KILL);
            seckillReturn = new SeckillReturn(true, seckillExecution);
        } catch (SeckillClosedException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            seckillReturn = new SeckillReturn(true, seckillExecution);
        }catch (SeckillDataRewriteException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            seckillReturn = new SeckillReturn(true, seckillExecution);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            seckillReturn = new SeckillReturn(true, seckillExecution);
        }
        return seckillReturn;
    }

    @GetMapping(value = "/time/now")
    public SeckillReturn<Long> time(){
        Date date = new Date();
        long now = date.getTime();
        return new SeckillReturn<Long>(true,now);
    }


}
