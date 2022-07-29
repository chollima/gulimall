# gulimall

### Introduction

The gulimall project is a set of B2C e-commerce projects, including the front-end mall system and the back-end management system. It is implemented based on SpringCloud + SpringCloudAlibaba + MyBatis-Plus and is deployed in Docker containers. The front-end mall system includes: user login, registration, product search, product details, shopping cart, order process, spike activity and other modules. The background management system includes seven modules: system management, commodity system, preferential marketing, inventory system, order system, user system, and content management.

### Project Architecture

```
gulimall
├── gulimall-common -- common dependencies of microservices
├── gulimall-auth-server -- authentication services (social login, OAuth2.0, single sign-on)
├── gulimall-cart -- shopping cart service
├── gulimall-coupon -- coupon service
├── gulimall-member -- membership service
├── gulimall-gateway -- gateway service
├── gulimall-order -- order service
├── gulimall-product -- product service
├── gulimall-search -- search service
├── gulimall-seckill -- seckill service
├── gulimall-third-party -- third-party services(SMS, OSS)
├── gulimall-ware -- warehousing Services
├── renren-fast -- mall backstage management system
├── renren-generator -- code generator sourced by Renren
├── gulimall-test-sso-client1 -- single sign on client 1
├── gulimall-test-sso-client1 -- single sign on client 2
└── gulimall-test-sso-server -- single sign on server
```

### System Architecture Diagram

[![UUvRAS.png](https://images.gitee.com/uploads/images/2020/0714/193425_4a1056c4_4914148.png)](https://imgchr.com/i/UUvRAS)

### Business Architecture Diagram

![UUvb7T.png](https://images.gitee.com/uploads/images/2020/0714/193425_9bb153d1_4914148.png)

### Gitee Feature

1. You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2. Gitee blog [blog.gitee.com](https://blog.gitee.com)
3. Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4. The most valuable open source project [GVP](https://gitee.com/gvp)
5. The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6. The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)