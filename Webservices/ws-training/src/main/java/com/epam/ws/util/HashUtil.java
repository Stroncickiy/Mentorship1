package com.epam.ws.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static String getHashFromString(String string){
		byte[] digest = new byte[0];
		try {
			digest = MessageDigest.getInstance("MD5").digest(string.getBytes(Charset.forName("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new String(digest,Charset.forName("UTF-8"));
	}
	
}
