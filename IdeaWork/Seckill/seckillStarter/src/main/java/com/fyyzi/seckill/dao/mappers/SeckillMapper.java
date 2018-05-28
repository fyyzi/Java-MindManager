package com.fyyzi.seckill.dao.mappers;

import com.fyyzi.seckill.beans.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillMapper {
//    int deleteByPrimaryKey(Long seckillId);
//
//    int insert(Seckill record);
//
//    int insertSelective(Seckill record);
//
//    Seckill selectByPrimaryKey(Long seckillId);
//
//    int updateByPrimaryKeySelective(Seckill record);
//
//    int updateByPrimaryKey(Seckill record);


    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return 如果影响的行数>1，表示更新的记录行数SeckillMapper
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据ID查询秒杀对象
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀对象
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}