package org.peng.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.peng.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 减库存-多个参数的时候需要加上注解@Param("xxx")
     * @param seckillId
     * @param killTime
     * @return
     */
    public int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     *
     * @param seckillId
     * @return
     */
    public Seckill queryById(long seckillId);

    /**
     *
     * @param offet
     * @param limit
     * @return
     */
    public List<Seckill> queryAll(@Param("offet")int offet,@Param("limit") int limit);
}
