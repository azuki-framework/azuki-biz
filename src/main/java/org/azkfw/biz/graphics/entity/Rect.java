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
 * このクラスは、Rect情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/15
 * @author Kawakicchi
 */
public final class Rect {

	private double x;
	private double y;
	private double width;
	private double height;

	/**
	 * コンストラクタ
	 */
	public Rect() {

	}

	/**
	 * コンストラクタ
	 * 
	 * @param aX x
	 * @param aY y
	 * @param aWidth width
	 * @param aHeight height
	 */
	public Rect(final double aX, final double aY, final double aWidth, final double aHeight) {
		x = aX;
		y = aY;
		width = aWidth;
		height = aHeight;
	}

	public void setPosition(final double aX, final double aY) {
		x = aX;
		y = aY;
	}

	public void setSize(final double aWidth, final double aHeight) {
		width = aWidth;
		height = aHeight;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
