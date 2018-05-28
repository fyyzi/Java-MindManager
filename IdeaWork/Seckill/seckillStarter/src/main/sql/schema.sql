-- 数据库初始化脚本
  -- CREATE DATABASE seckill;
  -- USE SECKILL;
-- 创建数据库
-- DROP TABLE IF EXISTS seckill;
CREATE TABLE `seckill` (
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` VARCHAR ( 120 ) NOT NULL COMMENT '商品名称',
  `number` INT COMMENT '库存数量',
  start_time datetime NOT NULL COMMENT '秒杀开始时间',
  end_time datetime NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL COMMENT '秒杀创建时间',
  PRIMARY KEY ( seckill_id ),
  KEY `idx_start_time` ( `start_time` ),
  KEY `idx_end_time` ( `end_time` ),
  KEY `idx_create_time` ( `create_time` )
) ENGINE = INNODB auto_increment = 1000 CHARACTER SET = utf8 COMMENT = '秒杀库存表';

-- 添加数据
INSERT INTO seckill ( NAME, number, start_time, end_time )
VALUES
  ( '1000元秒杀iPhone12', 100, '2018-05-24', '2018-05-25' ),
  ( '300元秒杀iPa9', 200, '2018-05-24', '2018-05-25' ),
  ( '500元秒杀小米22', 300, '2018-05-24', '2018-05-25' ),
  ( '1000元秒杀红米note', 400, '2018-05-24', '2018-05-25' );

-- 秒杀成功明细表
-- 用户登陆认证和相关信息
CREATE TABLE success_killed (
  `seckill_id` BIGINT NOT NULL COMMENT '商品库存ID',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT - 1 COMMENT '状态标识，-1表示无效，0成功，1 已付款，2 已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY ( seckill_id, user_phone ),
  KEY idx_create_time ( create_time ) /*联合主键*/

) ENGINE = INNODB CHARACTER
SET = utf8 COMMENT = '秒杀库存表';
