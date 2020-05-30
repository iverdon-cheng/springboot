package cn.iverdon.springboot04task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author iverdon
 * @date 2020/5/19 2:22 下午
 */
@Service
public class ScheduleService {
    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Scheduled(cron = "0 6 * * * MON-SAT")
    public void hello() throws MessagingException {
        System.out.println("hello....");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("520神秘邮件");
        helper.setText("<b style='color:red'>收到这封邮件的幸运女神，将会和程栋先生在三天后领证！</b>",true);
        helper.setTo("849049364@qq.com");
        helper.setFrom("294236220@qq.com");

        //上传文件
        helper.addAttachment("Love.jpg",new File("/Users/a123/Desktop/WechatIMG13.jpeg"));

        javaMailSender.send(mimeMessage);
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setSubject("520神秘邮件");
//        simpleMailMessage.setText("测试发邮件");
//        simpleMailMessage.setTo("2018022031@m.scnu.edu.cn");
//        simpleMailMessage.setFrom("294236220@qq.com");
//        javaMailSender.send(simpleMailMessage);
    }
}
