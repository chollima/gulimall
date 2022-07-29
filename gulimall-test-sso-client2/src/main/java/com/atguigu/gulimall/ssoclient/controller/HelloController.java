package com.atguigu.gulimall.ssoclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Value("${sso.server.url}")
    String ssoServerUrl;

    /**
     * 无需登录就能访问
     * @return
     */
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 登录访问
     * @param model
     * @param session
     * @param token 感知是去server服务器上登录后调回来的
     * @return
     */
    @GetMapping("/boss")
    public String employees(Model model, HttpSession session, @RequestParam(value = "token",required = false) String token) {
        if(!StringUtils.isEmpty(token)) {
            //去server服务器上登录后调回来的
            //去ssoserver获取当前token对应的用户信息
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/userInfo?token=" + token, String.class);
            String body = forEntity.getBody();
            session.setAttribute("loginUser",body);
        }

        Object loginUser = session.getAttribute("loginUser");
        if (loginUser==null) {
            //没登陆，跳转到登录服务器
            return "redirect:"+ssoServerUrl+"?redirect_url=http://localhost:8082/boss";
        } else {
            List<String> emps=new ArrayList<>();
            emps.add("张三");
            emps.add("李四");
            model.addAttribute("emps",emps);

            return "list";
        }
    }

}
