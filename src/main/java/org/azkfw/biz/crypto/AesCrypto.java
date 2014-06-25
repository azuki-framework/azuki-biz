/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.azkfw.biz.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * このクラスは、AES暗号機能を実装するクラスです。
 * 
 * @since 1.1.1
 * @version 1.1.1 2014/06/25
 * @author Kawakicchi
 */
public final class AesCrypto implements Crypto {

	private static final String DEFAULT_ENCRYPT_KEY = "-AzukiFramework-";
	private static final String DEFAULT_ENCRYPT_IV = "=AzukiFramework=";

	private String encryptoKey;
	private String encryptoIv;

	public AesCrypto() {
		encryptoKey = DEFAULT_ENCRYPT_KEY;
		encryptoIv = DEFAULT_ENCRYPT_IV;
	}

	public AesCrypto(final String aKey, final String aIv) {
		encryptoKey = aKey;
		encryptoIv = aIv;
	}

	@Override
	public String encrypt(final String aString) {
		String result = null;
		if (null != aString) {
			try {
				byte[] bytes = aString.getBytes("UTF-8");
				bytes = encrypt(bytes);
				result = Base64.encodeBase64String(bytes);
			} catch (UnsupportedEncodingException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public byte[] encrypt(final byte[] aBytes) {
		byte[] result = null;
		try {
			byte[] byteKey = encryptoKey.getBytes("UTF-8");
			byte[] byteIv = encryptoIv.getBytes("UTF-8");
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(byteIv);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			result = cipher.doFinal(aBytes);

		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (NoSuchPaddingException ex) {
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (InvalidAlgorithmParameterException ex) {
			ex.printStackTrace();
		} catch (InvalidKeyException ex) {
			ex.printStackTrace();
		} catch (BadPaddingException ex) {
			ex.printStackTrace();
		} catch (IllegalBlockSizeException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public String decrypt(final String aString) {
		String result = null;
		if (null != aString) {
			try {
				byte[] bytes = Base64.decodeBase64(aString);
				bytes = decrypt(bytes);
				result = new String(bytes, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public byte[] decrypt(byte[] aBytes) {
		byte[] result = null;
		try {
			byte[] byteKey = encryptoKey.getBytes("UTF-8");
			byte[] byteIv = encryptoIv.getBytes("UTF-8");
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(byteIv);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			result = cipher.doFinal(aBytes);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return result;
	}

}
