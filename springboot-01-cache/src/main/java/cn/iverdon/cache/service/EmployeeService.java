package cn.iverdon.cache.service;

import cn.iverdon.cache.bean.Employee;
import cn.iverdon.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp")//抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；以后在要相同的数据直接从缓存中获取；
     * <p>
     * CacheManager管理多个Cache组件的，对缓存的真正的CRUD操作在Cache组件中，每一个缓存组件都有自己的唯一名字
     * 几个属性：
     * cacheName/Value：指定缓存的名字
     * key：缓存数据使用到的key，可以用它来指定。默认是使用方法参数的值 1-方法的返回值
     * 编写SpEL #id;参数id的值  #a0  #p0 #root.args[0]
     * keyGenerator:key生成器；可以自己指定key的生成器的组建id
     * key/keyGenerator二选一使用
     * cacheManager：指定缓存管理器：或者指定缓存解析器
     * condition：制定符合条件的情况下才缓存；
     * unless：否定缓存，当unless制定的条件为true，方法的返回值就不会被缓存 可以获取到结果进行判断
     * unless="#result == null"
     * sync:是否使用异步
     *
     * @param id
     * @return
     */
    //@Cacheable(keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "a0==2")
    @Cacheable(keyGenerator = "myKeyGenerator")
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut:既调用方法，又更新缓存数据； 修改了数据库的某个数据，同时更新缓存
     * 运行时机
     * 现调用方法
     * 将目标方法的结果缓存起来
     */
    @CachePut(key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict: 缓存清除
     * key:指定要清除的数据
     * allEntries = true 指定清除这个缓存中所有的数据
     * beforeInvocation = false.缓存的清除是否在方法之前执行
     * 默认是在方法之后执行，如果出现异常缓存就会不清除
     */
    @CacheEvict(key = "#id")
    public void deleteEmp(Integer id) {
        System.out.println("delete:" + id);

    }


    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
