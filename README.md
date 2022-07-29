# 谷粒商城（gulimall）

### 项目介绍

谷粒商城（gulimall）项目是一套B2C电商项目，包括前台商城系统以及后台管理系统，前端基于 Vue + ElementUI 开发，后端基于 SpringCloud + SpringCloudAlibaba + MyBatis-Plus 开发，采用 Docker 容器化部署。前台商城系统包括：用户登录、注册、商品搜索、商品详情、购物车、下订单流程、秒杀活动等模块。后台管理系统包括：系统管理、商品系统、优惠营销、库存系统、订单系统、用户系统、内容管理等七大模块。

### 项目架构

```
gulimall
├── gulimall-common -- 微服务公共的依赖
├── gulimall-auth-server -- 认证服务（社交登录、OAuth2.0、单点登录）
├── gulimall-cart -- 购物车服务
├── gulimall-coupon -- 优惠券服务
├── gulimall-member -- 会员服务
├── gulimall-gateway -- 网关服务
├── gulimall-order -- 订单服务
├── gulimall-product -- 商品服务
├── gulimall-search -- 检索服务
├── gulimall-seckill -- 秒杀服务
├── gulimall-third-party -- 第三方服务（短信、OSS）
├── gulimall-ware -- 仓储服务
├── renren-fast -- 商城后台管理系统
├── renren-generator -- 人人开源代码生成器
├── gulimall-test-sso-client1 -- 单点登录客户端1
├── gulimall-test-sso-client1 -- 单点登录客户端2
└── gulimall-test-sso-server -- 单点登录服务端
```

### 系统架构图

[![UUvRAS.png](https://images.gitee.com/uploads/images/2020/0714/193425_4a1056c4_4914148.png)](https://imgchr.com/i/UUvRAS)

### 业务架构图

![UUvb7T.png](https://images.gitee.com/uploads/images/2020/0714/193425_9bb153d1_4914148.png)

### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)