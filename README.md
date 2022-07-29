# gulimall

### Introduction

The gulimall project is a set of B2C e-commerce projects, including the front-end mall system and the back-end management system. The front-end is developed based on Vue and ElementUI, while the back-end is developed based on SpringBoot, SpringCloudAlibaba and MyBatis-Plus. It is also deployed in Docker containers. The front-end mall system includes: user login, registration, product search, product details, shopping cart, order process, spike activity and other modules. The background management system includes seven modules: system management, commodity system, preferential marketing, inventory system, order system, user system, and content management.

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
