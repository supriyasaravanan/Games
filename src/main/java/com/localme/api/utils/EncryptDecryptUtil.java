package com.localme.api.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.localme.api.vo.UserDetailsVO;

public class EncryptDecryptUtil {
	
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static final String ALGORITHM = "AES";
	
	public void prepareSecretKey(String sKey) {
		MessageDigest sha = null;
		
		try {
		key = sKey.getBytes(StandardCharsets.UTF_8);
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		secretKey = new SecretKeySpec(key, ALGORITHM);
		}catch(NoSuchAlgorithmException exp) {
			
		}
	}
	
	public String encrypt(UserDetailsVO userVo) {
		try {
			prepareSecretKey(userVo.getUid().substring(0, 6));
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(userVo.getPwd().getBytes("UTF-8")));
		}catch(Exception exp){
			
		}
		return null;
	}
	
	
	public String decrypt(UserDetailsVO userVo) {
		try {
			prepareSecretKey(userVo.getUid().substring(0, 6));
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(userVo.getPwd())));
		}catch(Exception exp) {
			
		}
		
		return "";
	}
	
	
	public EncryptDecryptUtil getInstance() {
		return this;
	}
	
}
