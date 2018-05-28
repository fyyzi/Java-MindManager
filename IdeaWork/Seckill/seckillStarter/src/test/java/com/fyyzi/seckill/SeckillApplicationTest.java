package com.fyyzi.seckill;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional // TODO 事物回滚（测试类事物回滚操作）
public abstract class SeckillApplicationTest {


}
