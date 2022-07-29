package com.atguigu.gulimall.member.service;

import com.atguigu.gulimall.member.exception.MobileExistException;
import com.atguigu.gulimall.member.exception.UsernameExistException;
import com.atguigu.gulimall.member.vo.GiteeUser;
import com.atguigu.gulimall.member.vo.MemberLoginVo;
import com.atguigu.gulimall.member.vo.MemberRegistVo;
import com.atguigu.gulimall.member.vo.WeiboUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author chollima
 * @email chollima_zhou@163.com
 * @date 2022-05-28 10:26:39
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(MemberRegistVo vo);

    void checkMobileUnique(String mobile) throws MobileExistException;

    void checkUsernameUnique(String username) throws UsernameExistException;

    MemberEntity login(MemberLoginVo vo);

    MemberEntity weibologin(WeiboUser weiboUser) throws Exception;

    MemberEntity giteelogin(GiteeUser giteeUser) throws Exception;
}

