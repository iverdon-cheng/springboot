package cn.iverdon.springboot04task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class Springboot04TaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("不是垃圾邮件");
        simpleMailMessage.setText("测试发邮件");
        simpleMailMessage.setTo("943607374@qq.com");
        simpleMailMessage.setFrom("294236220@qq.com");
        mailSender.send(simpleMailMessage);
    }

}
