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
 * このクラスは、Point情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/15
 * @author Kawakicchi
 */
public final class Point {

	private double x;
	private double y;

	/**
	 * コンストラクタ
	 */
	public Point() {

	}

	/**
	 * コンストラクタ
	 * 
	 * @param aX x
	 * @param aY y
	 */
	public Point(final double aX, final double aY) {
		x = aX;
		y = aY;
	}

	public void setX(final double aX) {
		x = aX;
	}

	public double getX() {
		return x;
	}

	public void setY(final double aY) {
		y = aY;
	}

	public double getY() {
		return y;
	}

}
