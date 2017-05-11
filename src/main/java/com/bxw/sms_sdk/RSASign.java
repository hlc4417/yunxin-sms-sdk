package com.bxw.sms_sdk;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: RSASign 
 * @Description: RSA签名 
 * @author tangzhilong 
 * @date 2017年5月10日 下午7:12:03 
 *
 */
public class RSASign {
	
	private static final Logger logger = LoggerFactory.getLogger(RSASign.class);

	/**
	 * 签名算法
	 */
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	
	/**
	 * 签名编码格式
	 */
	private static final String SIGN_ENCODE = "utf-8";

	/**
	 * 
	 * @Title: sign 
	 * @Description: RSA签名 
	 * @param content 待签名数据
	 * @param privateKey 商户私钥
	 * @param encode 字符集编码
	 * @return 签名值
	 * String
	 * @throws
	 */
	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes(SIGN_ENCODE));
			byte[] signed = signature.sign();
			return URLEncoder.encode(Base64.encodeBase64String(signed), SIGN_ENCODE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Title: doCheck 
	 * @Description: RSA验签名检查 
	 * @param content 待签名数据
	 * @param sign 签名值
	 * @param publicKey 分配给开发商公钥
	 * @param encode 字符集编码
	 * @return 布尔值
	 * boolean
	 * @throws
	 */
	public static boolean doCheck(String content, String sign, String publicKey) {
		try {
			String dSign = URLDecoder.decode(sign,SIGN_ENCODE);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initVerify(pubKey);
			signature.update(content.getBytes(SIGN_ENCODE));
			boolean bverify = signature.verify(Base64.decodeBase64(dSign));
			return bverify;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("签名错误");
		}

		return false;
	}
	
}
