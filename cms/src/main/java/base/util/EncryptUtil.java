package base.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtil {
	
	//private static final String PASSWORD_CRYPT_KEY = "88444488";
	private static final String PASSWORD_CRYPT_KEY="0123456789ABCDEF";

	private final static String DES = "DES";
	
	private static final Integer SALT_LENGTH = 12;
	

	/**
	 * ���μ��� ��sha-1��������MD5����
	 * 
	 * @param src
	 * @return
	 */
	public final static String md5AndSha(String src) {
		return md5(sha(src));
	}

	/**
	 * ���μ��� ��MD5��������sha-1����
	 * 
	 * @param src
	 * @return
	 */
	public final static String shaAndMd5(String src) {
		return sha(md5(src));
	}

	/**
	 * md5����
	 * 
	 * @param src
	 * @return
	 */
	public final static String md5(String src) {
		return encrypt(src, "md5");
	}

	/**
	 * sha-1����
	 * 
	 * @param src
	 * @return
	 */
	public final static String sha(String src) {
		return encrypt(src, "sha-1");
	}

	/**
	 * md5����sha-1����
	 * 
	 * @param src           Ҫ���ܵ�����
	 * @param algorithmName �����㷨���ƣ�md5����sha-1�������ִ�Сд
	 * @return
	 */
	private final static String encrypt(String src, String algorithmName) {
		if (src == null || "".equals(src.trim())) {
			throw new IllegalArgumentException("������Ҫ���ܵ�����");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(src.getBytes("UTF8"));
			byte s[] = m.digest();
			// m.digest(src.getBytes("UTF8"));
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	/**
	 * �������
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String src) {
		try {
			return new String(decrypt(hex2byte(src.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * �������
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String src) {
		try {
			return byte2hex(encrypt(src.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param src ����Դ
	 * @param key ��Կ�����ȱ�����8�ı���
	 * @return ���ؼ��ܺ������
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();
		// ��ԭʼ�ܳ����ݴ���DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);
		// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher����ʵ����ɼ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// ���ڣ���ȡ���ݲ�������ʽִ�м��ܲ���
		return cipher.doFinal(src);
	}

	/**
	 * ����
	 * 
	 * @param src ����Դ
	 * @param key ��Կ�����ȱ�����8�ı���
	 * @return ���ؽ��ܺ��ԭʼ����
	 * 
	 * @throws Exception
	 */
	private final static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();
		// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);
		// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// ���ڣ���ȡ���ݲ�������ʽִ�н��ܲ���
		return cipher.doFinal(src);
	}

	private final static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("���Ȳ���ż��");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * ������ת�ַ���
	 * 
	 * @param b
	 * @return
	 */
	private final static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * ����ʮ�������ַ���
	 * 
	 * @param arr
	 * @return
	 */
	private final static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

}
