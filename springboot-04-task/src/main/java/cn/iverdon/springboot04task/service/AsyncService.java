package cn.iverdon.springboot04task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author iverdon
 * @date 2020/5/19 2:13 下午
 */
@Service
public class AsyncService {

    @Async //告诉spring这是一个一步方法
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中");
    }
}
