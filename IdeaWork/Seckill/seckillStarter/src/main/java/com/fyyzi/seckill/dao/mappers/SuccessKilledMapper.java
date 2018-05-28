package com.fyyzi.seckill.dao.mappers;

import com.fyyzi.seckill.beans.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

public interface SuccessKilledMapper {
//    int deleteByPrimaryKey(SuccessKilledKey key);
//
//    int insert(SuccessKilled record);
//
//    int insertSelective(SuccessKilled record);
//
//    SuccessKilled selectByPrimaryKey(SuccessKilledKey key);
//
//    int updateByPrimaryKeySelective(SuccessKilled record);
//
//    int updateByPrimaryKey(SuccessKilled record);


    /**
     * 插入购买明细，可过滤重复
     * @param successKilled
     * @return
     * @throws DuplicateKeyException 数据插入时外键重复 → 重复秒杀
     */
    int insert(SuccessKilled successKilled) throws DuplicateKeyException;

    /**
     * 根据id查询SuccessKilled并携带秒杀对象实体
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}