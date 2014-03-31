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
package jp.afw.biz.timer;

/**
 * このクラスは、タイマークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/16
 * @author Kawakicchi
 */
public final class Timer {

	/**
	 * 開始時間
	 */
	private long start;

	/**
	 * 終了時間
	 */
	private long stop;

	/**
	 * コンストラクタ
	 */
	public Timer() {
		start = 0;
		stop = 0;
	}

	/**
	 * 計測を開始します。
	 */
	public void start() {
		start = System.currentTimeMillis();
		stop = start;
	}

	/**
	 * 計測を終了します。
	 */
	public void stop() {
		stop = System.currentTimeMillis();
	}

	/**
	 * 計測時間を取得します。
	 * 
	 * @return 時間(ms)
	 */
	public long getTime() {
		return stop - start;
	}
}
