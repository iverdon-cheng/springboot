package cn.iverdon.cache;

import cn.iverdon.cache.bean.Employee;
import cn.iverdon.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot01CacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringredisTemplate;//操作k-v都是字符串的

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;//k-v都是对象的

    /**
     * String list set hash 散列 zSet 有序集合
     * stringredisTemplate.opsForValue()
     * stringredisTemplate.opsForList()
     * stringredisTemplate.opsForSet()
     * stringredisTemplate.opsForHash()
     */
    @Test
    public void test01() {
        //给redis
        //stringredisTemplate.opsForValue().append("msg","hello");
        String msg = stringredisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    //测试保存对象
    @Test
    public void test02() {
        Employee emp = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp-01",emp);
        empRedisTemplate.opsForValue().set("emp-01", emp);
        //1、将数据以json的方式保存
        //   自己将对象转为json
        //   redisTemplate
    }

    @Test
    void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

}
