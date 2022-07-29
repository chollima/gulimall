package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author chollima
 * @email chollima_zhou@163.com
 * @date 2022-05-27 17:30:44
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
