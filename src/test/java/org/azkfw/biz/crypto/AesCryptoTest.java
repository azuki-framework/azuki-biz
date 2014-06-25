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

import junit.framework.TestCase;

import org.junit.Test;

public class AesCryptoTest extends TestCase {

	@Test
	public void testNormal1() {
		AesCrypto crypto = new AesCrypto();

		String src = "password";
		String enc = crypto.encrypt(src);
		String dec = crypto.decrypt(enc);

		assertEquals(src, dec);
		assertEquals("YZq5EY13ngdmeZX0JS7qWg==", enc);
	}

	@Test
	public void testNormal2() {
		AesCrypto crypto = new AesCrypto("1234567890123456", "abcdefghijklmnop");

		String src = "password";
		String enc = crypto.encrypt(src);
		String dec = crypto.decrypt(enc);

		assertEquals(src, dec);
		assertEquals("JhThlq9vnQLGCr8VSPtSOA==", enc);
	}
}
