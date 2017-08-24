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
	 * 参数 appKey:账号appKey content:内容 phone：手机号码 timestamp:时间戳
	 * sign:签名(账号appKey，内容，手机号码，时间戳) 请求类型:get或post
	 */

	/**
	 * 发送短信接口
	 */
	private static final String sendSmsApiUrl = "http://api.bxwyx.com.cn/api/sendSms";

	/**
	 * 主账号代发子账号短信接口
	 */
	private static final String childSendSmsApiUrl = "http://api.bxwyx.com.cn/api/childSendSms";

	/**
	 * 创建子账号接口(非主账号不能使用)
	 */
	private static final String createMerchantApiUrl = "http://api.bxwyx.com.cn/api/createMerchant";

	/**
	 * 获取子账号信息接口
	 */
	private static final String getBypassAccountApiUrl = "http://api.bxwyx.com.cn/api/getBypassAccount";

	/**
	 * 获取短信数量接口
	 */
	private static final String getSmsCountApiUrl = "http://api.bxwyx.com.cn/api/getSmsCount";
	
	/**
	 *  校验签名接口
	 */
	private static final String checkSignatureApiUrl = "http://api.bxwyx.com.cn/api/checkSignature";
	
	/**
	 * RSA私钥
	 */
	String privateKey = "MIICdQIBADANaakqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALhRbq0lOWzRuda9Z28yfkb/6uyeHwUmtY2FFPX8dRFZ16gYDtb4PZhD21uFmu7aIidKw+/7/Ct8TQsu31dteBA0ouZTPrShJg6GpjLJHiz116wehXj53od4/glAqMwxl6IlNNfesojxDhpP8xrXM58BoT9/ntXAlv+ZXPe7elrPAgMBAAECgYBIrcOIfq8gG6q95D2O+UgV0v/dgZQVwefBAAL95Ch9SQHihGoFlikji05JzmsDV/wzpiWQ0bnjo8X1bOwgW0gF4IxfKGzIYbolzVB6t6hB4FMsBG4Bj3061yTrepAclUF8YtEhTXHEC1PY4YWhfYFco/58G+/d9b7UghomUIAcQQJBANmEASIDe5vP+976kbDjiVX4G6ci2nvZTREhL4WFFj0hLEEGeYOPzwMV0ArI4hGAV6Sevgzxtj1a9/9eGZ1PinECQQDY7c1jrNIg3uJgcOJgTEwBxv5ApHoFpoAVU0jiq37m1LaOmFNk1VK23y9zNGArIgfFWifzJGB2aGhbihZhJlk/AkBDuhx4MMluLIZqWW6/uzlp8UT695k78t9SNBkN4/CWvBuBa7iuRDra5RHQKdhqRqIDegIHGcP6urZ5Zx1mo/uhAkBjTqoCAXHInoBfyg24K/V3stNeYG2D/K8SFCh/rCbSIXrZWJ6EwKu3EAE26jlERbXtANLdGkAWtMFhdpu8KcQdAkB0QmpaYnrDvtWsKK1DiU5Mz80PQHs87LDScZJCx1JhMFzG8CiPYge5yldZb1oSjZvFFRjxcXVkFY+YkvNYz1JJ";
	
	/**
	 * 
	 * @Title: sendSmsTest 
	 * @Description: 发送短信 
	 * @throws UnsupportedEncodingException
	 * void
	 * @throws
	 */
	@Test
	public void sendSmsTest() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "343b9ff1-2f66-482e-8829-6bb7365375be");
		params.put("content", "666666");
		params.put("phone", "187****0357");
		params.put("timestamp", System.currentTimeMillis()+"");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(sendSmsApiUrl, params);
		System.out.println(message);
	}
	
	/**
	 * 
	 * @Title: childSendTest 
	 * @Description: 主账号代发子账号短信 
	 * @throws UnsupportedEncodingException
	 * void
	 * @throws
	 */
	@Test
	public void childSendTest() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "343b9ff1-2f66-482e-8829-6bb7365375be");
		params.put("content", "保险屋代替发送短信");
		params.put("phone", "187****0357");
		params.put("childAppKey", "362a63be-3b72-47aa-a2f5-722ead68a557");
		params.put("timestamp", System.currentTimeMillis()+"");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(childSendSmsApiUrl, params);
		System.out.println(message);
	}
	
	/**
	 * 
	 * @Title: createMerchant 
	 * @Description: 创建子账号(非主账号不能使用) 
	 * @throws UnsupportedEncodingException
	 * void
	 * @throws
	 */
	@Test
	public void createMerchant() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "343b9ff1-2f66-482e-8829-6bb7365375be");
		params.put("name", "李保险屋子账号");
		params.put("phone", "187****0357");
		params.put("password", "tang123456");
		params.put("timestamp", System.currentTimeMillis()+"");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(createMerchantApiUrl, params);
		System.out.println(message);
	}
	
	/**
	 * 
	 * @Title: getBypassAccount 
	 * @Description: 获取子账号信息 
	 * @throws UnsupportedEncodingException
	 * void
	 * @throws
	 */
	@Test
	public void getBypassAccount() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "343b9ff1-2f66-482e-8829-6bb7365375be");
		params.put("timestamp", System.currentTimeMillis()+"");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(getBypassAccountApiUrl, params);
		System.out.println(message);
	}
	
	/**
	 * 
	 * @Title: getSmsCount 
	 * @Description: 获取短信数量 
	 * @throws UnsupportedEncodingException
	 * void
	 * @throws
	 */
	@Test
	public void getSmsCount() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "343b9ff1-2f66-482e-8829-6bb7365375be");
		params.put("timestamp", System.currentTimeMillis()+"");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(getSmsCountApiUrl, params);
		System.out.println(message);
	}
	
	/**
	 * 
	 * @Title: createMerchant 
	 * @Description: 校验签名
	 * @throws UnsupportedEncodingException
	 * void
	 * @throws
	 */
	@Test
	public void checkSignature() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "fbe55cca-d839-4acf-8725-dbb82485ed69");
		params.put("signature", "保险屋云信");
		params.put("timestamp", System.currentTimeMillis()+"");
		String sign = RSASign.sign(UrlAscllSort.formatUrlMap(params, false), privateKey);
		params.put("sign", sign);
		String message = HttpClientUtil.sendHttpPost(checkSignatureApiUrl, params);
		System.out.println(message);
	}
}
