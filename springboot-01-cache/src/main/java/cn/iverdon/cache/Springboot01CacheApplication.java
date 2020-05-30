package cn.iverdon.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 搭建基本环境
 * 1.导入数据库文件 创建出department和employee表
 * 2.创建javabean封装数据
 * 3.整合Mybatis操作数据库
 * 1.配置数据源信息
 * 2.使用注解版的Mybatis
 *
 * @ MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 * 步骤
 * 1。开启基于注解的缓存
 * 2.标注缓存注解即可
 * @Cacheable
 * @CacheEvict
 * @CachePut 原理：
 * 1.自动配置类:CacheAutoConfiguration
 * 2.缓存的配置类
 * org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 * 3.哪个配置累默认生效 SimpleCacheConfiguration
 * 4.给容器中注册ncurrentMapCacheManager
 * 5.可以获取和创建ConcurrentMapCache类型的胡纳村组件，它的作用将数据保存在ConcurrentMap中；
 * <p>
 * 运行流程
 * @Cacheable 1、方法运行之前，先去查询Cache，按照cacheNames指定的名字获取
 * 2、去Cache中查找缓存内容，使用一个key，默认就是方法的参数
 * key是按照某种策略生成的默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
 * SimpleLeyGenerator生成key的默认策略：
 * 如果没有参数：key=new SimpleKey();
 * 如果有一个参数：key=参数的值
 * 如果有多个参数：key=new SimpleKey(params)
 * 3、没有查到缓存就调用目标方法
 * 4、将目标方法返回结果，放进缓存中
 * <p>
 * 核心：
 * 1、使用CacheManager「ConcurrentMapCacheManager」按照名字得到Cache「ConcurrentMapCache」组件
 * 2、key使用keyGenerator生成的，默认是SimpleKeyGenerator
 * <p>
 * 整合redis作为缓存
 * 1、使用docker作为缓存
 * 2、引入redis的starter
 * 3、配置redis
 * 4、测试缓存
 * 原理：CacheManager===Cache 缓存组件来实际给缓存中CRUD存取数据
 * 1)、引入redis的starter，容器中保存的事RedisCacheManager；
 * 2）、RedisCacheManager帮我们创建RedisCache来作为缓存组件；RedisCache通过操作redis缓存数据
 * 3)、默认保存数据k-v都是object，利用序列化保存；如何保存为json
 * 1、引入了redis的starter，cacheManager变为RedisCacheManager；
 * 2、默认创建的RedisCacheManager操作redis的时候使用的是 RedisTemplate<>
 * 3、RedisTemplate是默认使用jdk的序列化机制
 * 4）、
 */
@MapperScan("cn.iverdon.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01CacheApplication.class, args);
    }

}
