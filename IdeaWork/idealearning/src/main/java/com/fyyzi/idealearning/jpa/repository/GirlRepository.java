package com.fyyzi.idealearning.jpa.repository;

import com.fyyzi.idealearning.jpa.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 继承JpaRespository，泛型内参数为 实体类 ， 主键
 *
 * @author 息阳
 * 2017/12/29 12:26
 * @version 1.0
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {

    /**
     * 方法名称  必须按照该规则定义
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);
}
