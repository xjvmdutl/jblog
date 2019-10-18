package kr.co.itcen.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kr.co.itcen.config.web.FileUploadConfig;
import kr.co.itcen.config.web.MVCConfig;
import kr.co.itcen.config.web.MessageConfig;
import kr.co.itcen.config.web.SecurityConfig;



@Configuration
@ComponentScan({"kr.co.itcen.jblog.controller"})
@Import({MVCConfig.class,SecurityConfig.class,MessageConfig.class,FileUploadConfig.class})
public class WebConfig {

}
