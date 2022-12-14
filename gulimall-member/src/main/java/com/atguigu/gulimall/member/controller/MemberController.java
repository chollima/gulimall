package com.atguigu.gulimall.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.gulimall.member.exception.MobileExistException;
import com.atguigu.gulimall.member.exception.UsernameExistException;
import com.atguigu.gulimall.member.feign.CouponFeignService;
import com.atguigu.gulimall.member.vo.GiteeUser;
import com.atguigu.gulimall.member.vo.MemberLoginVo;
import com.atguigu.gulimall.member.vo.MemberRegistVo;
import com.atguigu.gulimall.member.vo.WeiboUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.atguigu.gulimall.member.service.MemberService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 会员
 *
 * @author chollima
 * @email chollima_zhou@163.com
 * @date 2022-05-28 10:26:39
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public R test() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("张三");
        R membercoupons = couponFeignService.membercoupons();
        return R.ok().put("member",memberEntity).put("coupons",membercoupons.get("coupons"));
    }

    @PostMapping("/regist")
    public R regist(@RequestBody MemberRegistVo vo) {
        try {
            memberService.regist(vo);
        } catch (MobileExistException e) {
            return R.error(BizCodeEnume.MOBILE_EXIST_EXCEPTION.getCode(), BizCodeEnume.MOBILE_EXIST_EXCEPTION.getMsg());
        } catch (UsernameExistException e) {
            return R.error(BizCodeEnume.USERNAME_EXIST_EXCEPTION.getCode(), BizCodeEnume.USERNAME_EXIST_EXCEPTION.getMsg());
        }

        return R.ok();
    }

    @PostMapping("/login")
    public R login(@RequestBody MemberLoginVo vo) {
        MemberEntity entity = memberService.login(vo);
        if(entity!=null) {
            return R.ok().setData(entity);
        } else {
           return R.error(BizCodeEnume.LOGIN_INVALID_EXCEPTION.getCode(), BizCodeEnume.LOGIN_INVALID_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/oauth2/weibo/login")
    public R oauthWeibologin(@RequestBody WeiboUser weiboUser) throws Exception {
        MemberEntity entity = memberService.weibologin(weiboUser);
        if(entity!=null) {
            //TODO:登录成功后的处理
            return R.ok().setData(entity);
        } else {
            return R.error(BizCodeEnume.LOGIN_INVALID_EXCEPTION.getCode(), BizCodeEnume.LOGIN_INVALID_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/oauth2/gitee/login")
    public R oauthGiteelogin(@RequestBody GiteeUser giteeUser) throws Exception {
        MemberEntity entity = memberService.giteelogin(giteeUser);
        if(entity!=null) {
            //TODO:登录成功后的处理
            return R.ok().setData(entity);
        } else {
            return R.error(BizCodeEnume.LOGIN_INVALID_EXCEPTION.getCode(), BizCodeEnume.LOGIN_INVALID_EXCEPTION.getMsg());
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
