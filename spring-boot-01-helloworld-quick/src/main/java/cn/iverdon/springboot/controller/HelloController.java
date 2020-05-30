package cn.iverdon.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//这个累的所有方法返回的数据
@ResponseBody
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world quick";
    }
    //RESTAPI方式
}
