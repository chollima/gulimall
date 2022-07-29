package com.atguigu.gulimall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.utils.HttpUtils;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.auth.feign.MemberFeignService;
import com.atguigu.gulimall.auth.vo.GiteeUser;
import com.atguigu.common.vo.MemberRespVo;
import com.atguigu.gulimall.auth.vo.WeiboUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理社交登录请求
 */
@Slf4j
@Controller
public class OAuth2Controller {

    @Autowired
    MemberFeignService memberFeignService;

    @GetMapping("/oauth2.0/gitee/success")
    public String gitee(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse) throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("client_id","449d80359601a3601073f455a47526607091a35bbb92252fdf68dcbcd59d7bce");
        params.put("client_secret","e549871381451c6b3712c43ef609844e773dedb5ba4f1a78bb4329587fd2594a");
        params.put("grant_type","authorization_code");
        params.put("redirect_uri","http://auth.gulimall.com/oauth2.0/gitee/success");
        params.put("code",code);
        //根据code换取accessToken
        //HttpResponse response = HttpUtils.doPost("http://api.weibo.com", "/oauth2/access_token", "post", null, null, map);
        HttpResponse response = HttpUtils.doPost("https://gitee.com", "/oauth/token", "post", null,null,params);
        //处理
        if (response.getStatusLine().getStatusCode()==200) {
            //获取到了accessToken
            String json = EntityUtils.toString(response.getEntity());
            GiteeUser giteeUser = JSON.parseObject(json, GiteeUser.class);
            //知道当前是哪个社交用户，如果当前用户是首次登录，自动注册（为当前社交用户生成会员信息账号）
            R r = memberFeignService.oauthGiteelogin(giteeUser);
            if(r.getCode()==0) {
                MemberRespVo data = r.getData("data", new TypeReference<MemberRespVo>() {});
                log.info("登录成功！用户：{}",data.toString());
                //第一次使用session，命令浏览器保存cookie
                session.setAttribute("loginUser",data);
                //登录成功跳回首页
                return "redirect:http://gulimall.com";
            } else {
                return "redirect:http://auth.gulimall.com/login.html";
            }
        } else {
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }


    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse) throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("client_id","741145953");
        params.put("client_secret","bea27597a92c55e68f68fbbed8983020");
        params.put("grant_type","authorization_code");
        params.put("redirect_uri","http://auth.gulimall.com/oauth2.0/weibo/success");
        params.put("code",code);
        //根据code换取accessToken
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", null, null, params);
        //处理
        if (response.getStatusLine().getStatusCode()==200) {
            //获取到了accessToken
            String json = EntityUtils.toString(response.getEntity());
            WeiboUser weiboUser = JSON.parseObject(json, WeiboUser.class);
            //知道当前是哪个社交用户，如果当前用户是首次登录，自动注册（为当前社交用户生成会员信息账号）
            R r = memberFeignService.oauthWeibologin(weiboUser);
            if(r.getCode()==0) {
                MemberRespVo data = r.getData("data", new TypeReference<MemberRespVo>() {});
                log.info("登录成功！用户：{}",data.toString());
                //第一次使用session，命令浏览器保存cookie
                session.setAttribute("loginUser",data);
                //登录成功跳回首页
                return "redirect:http://gulimall.com";
            } else {
                return "redirect:http://auth.gulimall.com/login.html";
            }
        } else {
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }
}
