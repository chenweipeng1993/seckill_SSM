http://blog.csdn.net/ai15134626825/article/details/75011912
有关MYSQL建库时有关时间戳字段的问题
由于要做一个项目的后台，需要设计数模并建库，用的是PowerDesigner，数据库用的是MySQL5.5 ，在建物理模型的时候，用到了timestamp这个类型的字段（时间戳），一个是创建时间，一个是修改时间，建库成功后发现一个问题，怎么让CREATED字段在创建时生成时间戳，而ALTER字段在修改时自动生成时间戳呢？上网一查，果然也有一些小伙伴和我一样遇到了这样的问题，下面是解决办法：

MySQL 5.7之前的版本,如果直接这样写是会报错的：
CREATE TABLE `test_table` (  
`id` INT( 10 ) NOT NULL,  
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  
) ENGINE = INNODB; 
1
2
3
4
5
有网友提供了这样的解决办法
CREATE TABLE `test_table` (  
`id` INT( 10 ) NOT NULL,  
`create_time` TIMESTAMP NOT NULL DEFAULT 0,  
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  
) ENGINE = INNODB; 
1
2
3
4
5
这样写的结果就是，修改时间在创建一条记录的时候也会初始化（其实也没毛病）
这时候插入和修改一条语句是这么写的
INSERT INTO test_table (id, create_time, update_time) VALUES (1, NULL, NULL); 
INSERT INTO test_table (id, update_time) VALUES (1, NULL);  
UPDATE test_table (id) VALUES (2); 
1
2
3
MySQL5.7之后可以直接这么写了
CREATE TABLE `test_table` (  
`id` INT( 10 ) NOT NULL,  
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  
) ENGINE = INNODB;
1
2
3
4
5
事情就这么解决了!