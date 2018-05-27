package com.zt.util;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SendSms {

    public static void sendSms(String phone, HttpServletRequest request) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        String code =RandomStringUtils.randomNumeric(6);
        NameValuePair[] data = { new NameValuePair("Uid", "ZT96"), // 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", phone), // 手机号码
                new NameValuePair("smsText", "【校园二手物品交易平台】找回密码服务，您的验证码为"+code+",如非本人操作，请忽略本信息") };//设置短信内容
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);
        post.releaseConnection();
        request.getSession().setAttribute("code", code);
    }

}
