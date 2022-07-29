package com.atguigu.gulimall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.common.utils.HttpUtils;
import com.atguigu.gulimall.member.dao.MemberLevelDao;
import com.atguigu.gulimall.member.entity.MemberLevelEntity;
import com.atguigu.gulimall.member.exception.MobileExistException;
import com.atguigu.gulimall.member.exception.UsernameExistException;
import com.atguigu.gulimall.member.vo.GiteeUser;
import com.atguigu.gulimall.member.vo.MemberLoginVo;
import com.atguigu.gulimall.member.vo.MemberRegistVo;
import com.atguigu.gulimall.member.vo.WeiboUser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.member.dao.MemberDao;
import com.atguigu.gulimall.member.entity.MemberEntity;
import com.atguigu.gulimall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    MemberLevelDao memberLevelDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void regist(MemberRegistVo vo) {
        MemberDao memberDao = this.baseMapper;
        MemberEntity entity = new MemberEntity();

        //设置默认登记
        MemberLevelEntity levelEntity = memberLevelDao.getDefaultLevel();
        entity.setLevelId(levelEntity.getId());

        //检测用户名和手机号是否唯一（使用异常机制）
        checkMobileUnique(vo.getMobile());
        checkUsernameUnique(vo.getUserName());

        entity.setMobile(vo.getMobile());
        entity.setUsername(vo.getUserName());
        entity.setNickname(vo.getUserName());
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);

        //保存到数据库
        memberDao.insert(entity);

    }

    @Override
    public void checkMobileUnique(String mobile)  throws MobileExistException {
        MemberDao memberDao = this.baseMapper;
        Integer mobileCount = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", mobile));
        if(mobileCount>0) {
            throw new MobileExistException();
        }
    }

    @Override
    public void checkUsernameUnique(String username) throws UsernameExistException {
        MemberDao memberDao = this.baseMapper;
        Integer usernameCount = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("username", username));
        if(usernameCount>0) {
            throw new UsernameExistException();
        }
    }

    @Override
    public MemberEntity login(MemberLoginVo vo) {
        String loginaccount = vo.getLoginaccount();
        String password = vo.getPassword();

        //去数据库查询
        MemberDao memberDao = this.baseMapper;
        MemberEntity entity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("username", loginaccount).or().eq("mobile", loginaccount));
        if(entity==null) {
            //登录失败
            return null;
        } else {
            //获取到数据库的password
            String passwordDb = entity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean matches = passwordEncoder.matches(password, passwordDb);
            if(matches) {
                return entity;
            } else {
                return null;
            }
        }
    }


    @Override
    public MemberEntity weibologin(WeiboUser weiboUser) throws Exception {
        //登录和注册合并
        String uid = weiboUser.getUid();
        //判断当前社交用户是否登陆过系统
        MemberDao memberDao = this.baseMapper;
        MemberEntity memberEntity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("weibo_uid", uid));
        if(memberEntity!=null) {
            //这个用户已经注册过
            MemberEntity update = new MemberEntity();
            update.setId(memberEntity.getId());
            update.setWeiboAccessToken(weiboUser.getAccess_token());
            update.setWeiboExpiresIn(weiboUser.getExpires_in());
            memberDao.updateById(update);
            memberEntity.setWeiboAccessToken(weiboUser.getAccess_token());
            memberEntity.setWeiboExpiresIn(weiboUser.getExpires_in());
            return memberEntity;
        } else {
            //没有查到当前社交用户对应的记录
            MemberEntity regist = new MemberEntity();
            try {
                //查询当前社交用户的社交账号信息
                Map<String,String> params = new HashMap<>();
                params.put("access_token",weiboUser.getAccess_token());
                params.put("uid",weiboUser.getUid());
                HttpResponse response = HttpUtils.doGet("https://api.weibo.com", "/2/users/show.json", "get", new HashMap<String, String>(), params);
                if(response.getStatusLine().getStatusCode()==200) {
                    //查询成功
                    String json = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObject = JSON.parseObject(json);
                    String name = jsonObject.getString("name");
                    String gender = jsonObject.getString("gender");
                    regist.setNickname(name);
                    regist.setGender("m".equals(gender)?1:0);
                    //TODO:......其他信息
                }
            } catch (Exception e) {

            }
            regist.setWeiboUid(weiboUser.getUid());
            regist.setWeiboAccessToken(weiboUser.getAccess_token());
            regist.setWeiboExpiresIn(weiboUser.getExpires_in());
            memberDao.insert(regist);

            return regist;
        }
    }

    @Override
    public MemberEntity giteelogin(GiteeUser giteeUser) throws Exception {
        //登录和注册合并
        Map<String,String> params = new HashMap<>();
        params.put("access_token",giteeUser.getAccess_token());
        //查询当前社交用户的社交账号信息
        try {
            HttpResponse response = HttpUtils.doGet("https://gitee.com", "/api/v5/user", "get", new HashMap<String, String>(), params);
            if(response.getStatusLine().getStatusCode()==200) {
                //查询成功
                String json = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = JSON.parseObject(json);
                String id = jsonObject.getString("id");
                //判断当前社交用户是否登陆过系统
                MemberDao memberDao = this.baseMapper;
                MemberEntity memberEntity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("gitee_uid", id));
                if(memberEntity!=null) {
                    //这个用户已经注册过
                    MemberEntity update = new MemberEntity();
                    update.setId(memberEntity.getId());
                    update.setGiteeAccessToken(giteeUser.getAccess_token());
                    update.setGiteeExpiresIn(giteeUser.getExpires_in());
                    memberDao.updateById(update);
                    memberEntity.setGiteeAccessToken(giteeUser.getAccess_token());
                    memberEntity.setGiteeExpiresIn(giteeUser.getExpires_in());
                    return memberEntity;
                } else {
                    //没有查到当前社交用户对应的记录
                    MemberEntity regist = new MemberEntity();
                    String name = jsonObject.getString("name");
                    regist.setNickname(name);
                    //TODO:......添加其他信息
                    regist.setGiteeUid(id);
                    regist.setGiteeAccessToken(giteeUser.getAccess_token());
                    regist.setGiteeExpiresIn(giteeUser.getExpires_in());
                    memberDao.insert(regist);
                    return regist;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }


}