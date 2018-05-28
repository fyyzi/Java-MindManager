package com.fyyzi.seckill.services.impl;

import com.fyyzi.seckill.beans.Seckill;
import com.fyyzi.seckill.beans.SuccessKilled;
import com.fyyzi.seckill.dao.redis.SeckillRedisDao;
import com.fyyzi.seckill.dto.ExportUrl;
import com.fyyzi.seckill.dto.SeckillExecution;
import com.fyyzi.seckill.enums.SeckillStateEnum;
import com.fyyzi.seckill.exceptions.*;
import com.fyyzi.seckill.dao.mappers.SeckillMapper;
import com.fyyzi.seckill.dao.mappers.SuccessKilledMapper;
import com.fyyzi.seckill.services.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 羽化荼2018年5月26日
 */
@Service
//@EnableTransactionManagement // TODO 这个注解不知道到底有啥作用   没看出来
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    @Autowired
    private SeckillRedisDao seckillRedisDao;

    /**
     * MD5盐值加密，用于混淆MD5
     */
    private final String SLAT = "10293lzlxcu04nzxcj";
//    private final String SLAT = UUID.randomUUID().toString();

    @Override
    public List<Seckill> getSeckillList() {
        List<Seckill> seckills = seckillMapper.queryAll(0, 10);
        return seckills;
    }

    @Override
    public Seckill getSeckillByPrimaryKey(Long seckillId) {
        Seckill seckill = seckillMapper.queryById(seckillId);
        return seckill;
    }

    @Override
    public ExportUrl exportSeckillUrl(Long seckillId) {

        Seckill seckill = seckillRedisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillMapper.queryById(seckillId);
            if (seckill == null) {
                ExportUrl exportUrl = new ExportUrl(false, seckillId);
                return exportUrl;
            } else {
                seckillRedisDao.putSeckill(seckill);
            }
        }

        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        long nowTime = new Date().getTime();

        if (nowTime < startTime || nowTime > endTime) {
            ExportUrl exportUrl1 = new ExportUrl(false, seckillId, nowTime, startTime, endTime);
            return exportUrl1;
        }

        String md5 = getMD5(seckillId);
        ExportUrl exportUrl = new ExportUrl(true, md5, seckillId);
        return exportUrl;

    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + this.SLAT;
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5DigestAsHex;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillAbsException, SeckillRepeatException, SeckillClosedException, SeckillDataRewriteException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillDataRewriteException(100, "seckill data rewrite");
        }

        // 执行秒杀业务逻辑 → 减库存 & 记录秒杀行为
        try {
            Date nowTime = new Date();
            {
                SuccessKilled insert = new SuccessKilled(seckillId, userPhone);
                insert.setState((byte) 0);
                // 如果捕获外键重复异常，则说明重复插入，即 进行了 重复秒杀操作
                try {
                    int insert1 = successKilledMapper.insert(insert);
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);

                    int updateCount = seckillMapper.reduceNumber(seckillId, nowTime);
                    // 若更新结果为0 则 没有更新记录  即 秒杀结束
                    if (updateCount <= 0) {
                        throw new SeckillClosedException(100, "seckill is closed");
                    } else {
                        // 秒杀成功
                        SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                        return seckillExecution;
                    }
                } catch (DuplicateKeyException e) {
                    throw new SeckillRepeatException(100, "seckill Repeat");
                }
            }
        } catch (SeckillAbsException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillInnerError(100, "seckill inner error" + e.getMessage());
        }
    }
}
