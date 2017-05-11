package com.bxw.sms_sdk;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @ClassName: ApiTest 
 * @Description: 接口测试 
 * @author tangzhilong 
 * @date 2017年5月10日 下午8:26:06 
 *
 */
public class ApiTest {
	
	/**
	 * 发送短信接口
	 * 
	 * 参数 
	 * 	   appKey:账号appKey
	 * 	   content:内容
	 * 	   phone：手机号码
	 * 	   timestamp:时间戳
	 * 	   sign:签名(账号appKey，内容，手机号码，时间戳)
	 * 请求类型:get或post
	 */
	private static final String apiUrl ="http://localhost:8080/api/sendSms";
	
	/**
	 * RSA私钥
	 */
	String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM3BoAr5xeMBuKSNCmGzfVA9jpCBCt7nn8tq1NEM5Mhfrat1ubuiAkcarVtDbLl0t0RsMJHzY9yxxF2ghoPXAMw/HcC6q1573p8EEnIiKiAJ87/20tgyG0/JERnuaEoZy8tBqL/yB+yzH3LavZ+2QWOm2A3ng8nn765g1MuHJgqZAgMBAAECgYAZwo0T8rN4uUVxrIrJMpyUyDAqHU3MHHmgEQ714URfZcMrfUC0ohLg7hEYJPfevCMZuOXXz/4HqmXoRumwZeGedw8pnR8pHkStlKgJD3/SHPVjNJJ2rXVD50g/0HU9SUCcX7JkUAJfTkdgehsqhQsg8IFYsApHG4B5DTZItTSpJQJBAPSDbIFKg+2eR4M0sb5sdye5SNIC+Yadyz+9gsqVvBtJyoJUWqBiD2dnDoIjwDYLfrsryd9uQGGxZ97T6G68Cm8CQQDXbBme6Wko4yaSEdV+45SUPiRWxjiXlg+sbBxoBmr4zSU2DgnTm5bzFu/kDBQZKpGobUwRueWSnJvB5D9fAl93AkEAs36e8rKu8oqU2L6gFLgvKJwd1v2UC7xebiZCk1EkS6v5237tIHub7z56KhLwkTwTBnf4s+qNnKnNJIjPQisMRQJBAM9W5loDQgnuh6Mzsw9MMCd2oDc81h9geXl1HOoqsy8BFH2wiVi4elNCkmYmEfoe9M7tU7gD+qhgP7Ig47WobQUCQQDTGE8jKqPqNJp8BC2aiT/6c4jasM6ush+SgYuuX/jBiLGwMPiDjl1pwqDYOaoLvV3YkqHVwNrAprMeOYyl8F2l";
	
	@Test
	public void apiTest() throws UnsupportedEncodingException{
		Map<String,String> params =new HashMap<String,String>();
        params.put("appKey", "8662b707-89fd-43ec-b316-01cc8c047c38");
        params.put("content", "保险屋");
        params.put("phone", "13124075159");
        params.put("timestamp", "1494473416");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(apiUrl, params);
		System.out.println(message);
	}
}
