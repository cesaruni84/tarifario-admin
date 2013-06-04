package org.srluren.admin.util;

import java.security.MessageDigest;

public class Utilitario {
	
	//Encriptacion  MD5
	public static String encriptarMD5(String valorEncriptar) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(valorEncriptar.getBytes());

		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}

}
