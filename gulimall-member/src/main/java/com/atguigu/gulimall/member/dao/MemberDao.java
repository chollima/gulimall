package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chollima
 * @email chollima_zhou@163.com
 * @date 2022-05-28 10:26:39
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
