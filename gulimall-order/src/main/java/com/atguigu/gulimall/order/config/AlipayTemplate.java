package com.atguigu.gulimall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gulimall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2021000121633062";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDSvJY4SbdUtzYA/t5vvw9zQ1BgJ5J7wRun7euTDaXEwVhK6nOGP4da5HMW0BklQQM42l4onidoxqkipuJPR62ZHLYpeXJ1fbHNepedq4Z/0lqyDGzLQYn7esFeY/AMWW+KjBuTeztCZfPNmcugyPenYQ1+GUPYXT9E6Uzl7lo4OnjXWSFE7rTmByc8D4U4jNnvzU0feknG8JAMw96SJXsoiKvmS2u2hwoteJDBSLXsu6da7BhOKpUEu9Zry5iyQROWwfieUSmfXnWWpJRfyn83wPwquwSASemgRsuBIpY+nLJJ/XJDNvz328fGqHJITVamo+6j60fvOFW4en8T48qjAgMBAAECggEBAMUoTtqGjRCi/9FaobymYcwSlCYBn9hMSJWJ0DYaNJap1PLx+fcFhJsS1GHpez0RX/RWyHTz0icChca3n09Q3ic7Z2nSB7XITQrSwPpwOTtpEBU37qkSvgcbR8IJedHz8pkKj9ohFsX+IGSgmJPqsVhVQoxREtpgEwtjwd/0aU/kILeRry4C7feZf1cOLuxPYIfuofusE5r4XEid9nt7965FmhghOajQp/fgpW1tvEduM+hOB3zlvyc9iNqurlXie2x0DVEjoWXTROvkmxcrnlvTbChVi07RkJ00KgKkTlxbmV4hf34aVf/Wde+GyVt8EDgVqc6GDpenMZeAK53KElECgYEA+uAw/fkiCT25Wp/iZhY0Pt1pM89nGk1qj+7M/URvC1qsIlgc78bLEAIhD7mfwEIoBZ6BH+RBkbBJo6Rh3hsHi6Jj9uvhI1u2tp1IZOL9a2bTLcgq8dqjQZGKit0suMpOQvI2lJleLVSmMK8eLL6k9SqmN7UEBq+amg7Q+j+PDQ0CgYEA1wqC8AitlPVsMOWSB1VTDJdCNovluoOJKPjFOULhViaJuWvdMXvNtdf34KJOPdFYidFz8q5yE8SOdLaY3OrGqB3avyn4b3DJZS0+VBjLBE2k2Sqdr4JIfaAsCxoxd2iw6byWmg9QZccuYnvvRmv3RvROEk2bQLCy7f8Ue8tdKm8CgYEA3J2Kd80+QSImfatcTKn6B85tVUJuSJMLwscdPRuRC0sRVKJnz/PhXpKAwHNcKJYE2+wnYQQ5JQUBvQYxij/6wrBreBi2Q4HDi5OEtjNm56vkyb3dBhJevqpeSv909Ivzhqu5k7PLPdHS070rq+164p4+zX5BrB+AtTmSTH7YLYECgYEAx6Ruo8KqxNyFMXAw/rXsgIf9ZP7ErUBpGqy7DcpVwdHNk2Pc25ABD44OB3MoCKRO+sSGTQw/xLJ7P0g26wLcpD/zUf75Mx0XHOPANfs+oCQlo6i49LST8G2/9h6/RgjgFmCLwUPudE9TdQNyxYJ+9FTkFoIOXSSvOd3Teor3V+kCgYEAwPpXVegM/vzZAmG3+yI7w/0WVZWZB5nGQVtPzZzbQCsioTYIwGAcmx80MUGnmPWC4l6mNLm45bc8LrhthrFPu99kFFeup31DjxImhAMjxnk+wIxKR51LxXa+YVPn+2OlzEDtpFTIfWSuVSbCWdmmcDyLN0FHXQyrd280BlEfM3Y=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkqOcu8YHpxbck3WGF2I5HkBC0d+HGp8KiK0QQJCkahgmw6dfopOeglQT6MbYFtElqC/nCuwgBdk6UA/b/Npr4ezriVLGldeu0KnC9vWcmCq+bpOQiMiBJ0cI9NKLsYVZIQE9BWO5ID0kpjen5DSwYcxt5XgECN/2ZkJ8Lsr5m7VfjqnwbIESS5eeWvDA9D2+VY7dsXx44T0mJ1zWzwVpR0brFpKA7kLPlb2VN6YbUtiPjcdfmxicibLOAPIR57xP+DnOqysGvoXd92zbnfK9UfY5Wmz/lRS4HsyTTGl6LrTIRmwPIFYPAINl48XuBIXLZ1dzGNOdJwNd7u3unDhu2QIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://zb87igy89mz5.hk1.xiaomiqiu123.top/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://member.gulimall.com/memberOrder.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    private String timeout = "1m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
