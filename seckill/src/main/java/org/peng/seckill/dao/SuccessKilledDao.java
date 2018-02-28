package org.peng.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.peng.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可以过滤重复
     * @param seckillId
     * @param userphone
     * @return
     */
    public int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userphone") long userphone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象seckill实体
     * @param seckillId
     * @return
     */
    public SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userphone") long userphone);

}
