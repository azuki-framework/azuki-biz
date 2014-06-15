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
package org.azkfw.biz.graphics.entity;

/**
 * このクラスは、Margin情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/15
 * @author Kawakicchi
 */
public class Margin {

	private double top;
	private double bottom;
	private double left;
	private double right;

	/**
	 * コンストラクタ
	 */
	public Margin() {
		top = 0;
		bottom = 0;
		left = 0;
		right = 0;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aTop top
	 * @param aBottom bottom
	 * @param aLeft left
	 * @param aRight right
	 */
	public Margin(final double aTop, final double aBottom, final double aLeft, final double aRight) {
		top = aTop;
		bottom = aBottom;
		left = aLeft;
		right = aRight;
	}

	public void setTop(final double aTop) {
		top = aTop;
	}

	public double getTop() {
		return top;
	}

	public void setBottom(final double aBottom) {
		bottom = aBottom;
	}

	public double getBottom() {
		return bottom;
	}

	public void setLeft(final double aLeft) {
		left = aLeft;
	}

	public double getLeft() {
		return left;
	}

	public void setRight(final double aRight) {
		right = aRight;
	}

	public double getRight() {
		return right;
	}

}
