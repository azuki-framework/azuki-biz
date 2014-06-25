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
 * このインターフェースは、暗号機能を表現するインターフェースです。
 * 
 * @since 1.1.1
 * @version 1.1.1 2014/06/25
 * @author Kawakicchi
 */
public interface Crypto {

	/**
	 * 暗号化を行う。
	 * 
	 * @param aString 文字列
	 * @return 暗号化された文字列
	 */
	public String encrypt(final String aString);

	/**
	 * 暗号化を行う。
	 * 
	 * @param aBytes バイト列
	 * @return 暗号化されたバイト列
	 */
	public byte[] encrypt(final byte[] aBytes);

	/**
	 * 複合化を行う。
	 * 
	 * @param aString 暗号かされた文字列
	 * @return 複合化された文字列
	 */
	public String decrypt(final String aString);

	/**
	 * 複合化を行う。
	 * 
	 * @param aBytes 暗号化されたバイト列
	 * @return 複合化されたバイト列
	 */
	public byte[] decrypt(final byte[] aBytes);
}
