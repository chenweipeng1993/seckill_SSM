package org.peng.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.peng.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {
        /**
         * org.mybatis.spring.MyBatisSystemException:
         * nested exception is org.apache.ibatis.binding.BindingException:
         * Parameter 'seckillId' not found. Available parameters are [arg1, arg0, param1, param2]
         */
        Date killTime = new Date();
        int updateColumn = seckillDao.reduceNumber(1000,killTime);
        System.out.println(updateColumn);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.toString());
        /**
         * Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100, startTime=Fri Jan 01 08:00:00 CST 2016, endTime=Sat Jan 02 08:00:00 CST 2016, createTime=Tue Feb 27 06:15:57 CST 2018}
         */
    }

    @Test
    public void queryAll() throws Exception {
        /**
         * org.mybatis.spring.MyBatisSystemException:
         * nested exception is org.apache.ibatis.binding.BindingException:
         * Parameter 'offet' not found. Available parameters are [arg1, arg0, param1, param2]
         * java没有保存形参的记录 有多个参数的时候java有这样的错误
         * 解决方法是在形参前面加上注解@Param("limit") int limit
         *
         */
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for(Seckill seckill:seckills){
            System.out.println(seckill.toString());
        }
    }

}