CREATE DEFINER=`xiyang`@`%` PROCEDURE `execute_seckill`( IN `v_seckill_id` BIGINT, IN `v_phone` BIGINT, IN `v_kill_time` TIMESTAMP, OUT `result_count` INT )
BEGIN
DECLARE
insert_count INT DEFAULT 0;
START TRANSACTION;
INSERT INTO success_killed (seckill_id, user_phone, state, create_time)
VALUES
  (v_seckill_id, v_phone, 0, v_kill_time);
SELECT ROW_COUNT() INTO insert_count;
IF
( insert_count = 0 ) THEN
ROLLBACK;

SET result_count = -1;

ELSEIF ( insert_count < 0 ) THEN
ROLLBACK;

SET result_count = -2;
ELSE UPDATE seckill
SET number = number - 1
WHERE
  seckill_id = v_seckill_id
  AND end_time > v_kill_time
  AND start_time < v_kill_time AND number > 0;
SELECT ROW_COUNT() INTO insert_count;
IF
( insert_count = 0 ) THEN
ROLLBACK;

SET result_count = 0;

ELSEIF ( insert_count < 0 ) THEN
ROLLBACK;

SET result_count = -2;
ELSE COMMIT;

SET result_count = 1;

END IF;

END IF;

END