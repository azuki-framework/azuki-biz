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

/**
 * このクラスは、暗号機能を生成するファクトリクラスです。
 * 
 * @since 1.1.1
 * @version 1.1.1 2014/06/25
 * @author Kawakicchi
 */
public class CryptoFactory {

	/**
	 * AES暗号を作成する。
	 * 
	 * @return AES
	 */
	public static Crypto createAES() {
		AesCrypto crypto = new AesCrypto();
		return crypto;
	}

	/**
	 * AES暗号を作成する。
	 * 
	 * @param aKey Key
	 * @param aIv IV
	 * @return AES暗号
	 */
	public static Crypto createAES(final String aKey, final String aIv) {
		AesCrypto crypto = new AesCrypto(aKey, aIv);
		return crypto;
	}

}
