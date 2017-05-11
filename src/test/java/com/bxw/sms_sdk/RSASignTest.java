package com.bxw.sms_sdk;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @ClassName: RSASignTest 
 * @Description: 签名测试 
 * @author tangzhilong 
 * @date 2017年5月10日 下午8:25:53 
 *
 */
public class RSASignTest {
	
	/**
	 * RSA公钥
	 */
	String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNwaAK+cXjAbikjQphs31QPY6QgQre55/LatTRDOTIX62rdbm7ogJHGq1bQ2y5dLdEbDCR82PcscRdoIaD1wDMPx3Auqtee96fBBJyIiogCfO/9tLYMhtPyREZ7mhKGcvLQai/8gfssx9y2r2ftkFjptgN54PJ5++uYNTLhyYKmQIDAQAB";
	
	/**
	 * RSA私钥
	 */
	String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM3BoAr5xeMBuKSNCmGzfVA9jpCBCt7nn8tq1NEM5Mhfrat1ubuiAkcarVtDbLl0t0RsMJHzY9yxxF2ghoPXAMw/HcC6q1573p8EEnIiKiAJ87/20tgyG0/JERnuaEoZy8tBqL/yB+yzH3LavZ+2QWOm2A3ng8nn765g1MuHJgqZAgMBAAECgYAZwo0T8rN4uUVxrIrJMpyUyDAqHU3MHHmgEQ714URfZcMrfUC0ohLg7hEYJPfevCMZuOXXz/4HqmXoRumwZeGedw8pnR8pHkStlKgJD3/SHPVjNJJ2rXVD50g/0HU9SUCcX7JkUAJfTkdgehsqhQsg8IFYsApHG4B5DTZItTSpJQJBAPSDbIFKg+2eR4M0sb5sdye5SNIC+Yadyz+9gsqVvBtJyoJUWqBiD2dnDoIjwDYLfrsryd9uQGGxZ97T6G68Cm8CQQDXbBme6Wko4yaSEdV+45SUPiRWxjiXlg+sbBxoBmr4zSU2DgnTm5bzFu/kDBQZKpGobUwRueWSnJvB5D9fAl93AkEAs36e8rKu8oqU2L6gFLgvKJwd1v2UC7xebiZCk1EkS6v5237tIHub7z56KhLwkTwTBnf4s+qNnKnNJIjPQisMRQJBAM9W5loDQgnuh6Mzsw9MMCd2oDc81h9geXl1HOoqsy8BFH2wiVi4elNCkmYmEfoe9M7tU7gD+qhgP7Ig47WobQUCQQDTGE8jKqPqNJp8BC2aiT/6c4jasM6ush+SgYuuX/jBiLGwMPiDjl1pwqDYOaoLvV3YkqHVwNrAprMeOYyl8F2l";
    
	@Test
	public void produceSign(){
		Map<String,String> params =new HashMap<String,String>();
        params.put("appKey", "8662b707-89fd-43ec-b316-01cc8c047c38");
        params.put("content", "保险屋");
        params.put("phone", "15985850548");
        params.put("timestamp", "1494421195705");
		String paramsStr = UrlAscllSort.formatUrlMap(params, false);
		String sign = RSASign.sign(paramsStr, privateKey);
		System.out.println("---------------------URLEncode之后的签名------------------------------------");
		System.out.println("签名:"+sign);
		System.out.println("---------------------校验签名------------------------------------");
		boolean check = RSASign.doCheck(paramsStr,sign, publicKey);
		System.out.println("校验签名结果:"+check);
	}
}
