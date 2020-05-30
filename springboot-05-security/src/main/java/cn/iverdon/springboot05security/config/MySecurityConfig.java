package cn.iverdon.springboot05security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author iverdon
 * @date 2020/5/19 3:59 下午
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");
        //开启自动配置的登录功能
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1./login来到登录页
        //2.重定向到/login?error 表示登录失败
        //3.更多详细规则
        //4.默认post形式的/login代表登录
        //5.一旦定制loginPage；那么loginPage的post请求就是登录

        //开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功来到首页
        //1、访问/logout表示拥护注销，清空session
        //2、注销成功会返回 /login？页面

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        //登录成功之后，将cookie发给浏览器，以后登录带上cookie，只要通过检查就可以免登录

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("iverdon").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP2")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("qinziming").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2","VIP3");
    }
}
