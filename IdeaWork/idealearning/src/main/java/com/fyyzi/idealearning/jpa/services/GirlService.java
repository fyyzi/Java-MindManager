package com.fyyzi.idealearning.jpa.services;

import com.fyyzi.idealearning.jpa.domain.Girl;

import java.util.List;

/**
 * Girl Service 层
 *
 * @author 息阳
 * 2017/12/29 15:15
 * @version 1.0
 */
public interface GirlService {

    /**
     * 根据Id获取Girl 对象
     *
     * @param id
     * @return
     */
    public Girl getGirlById(Integer id);

    /**
     * 获取所有Girl对象
     *
     * @return
     */
    public List<Girl> getAllGirl();

    /**
     * 根据年龄查询
     *
     * @param age
     * @return
     */
    List<Girl> getGirlByAge(Integer age);

    /**
     * 向数据库添加Girl对象
     *
     * @param girl
     * @return
     */
    Girl addGirl(Girl girl);
}
