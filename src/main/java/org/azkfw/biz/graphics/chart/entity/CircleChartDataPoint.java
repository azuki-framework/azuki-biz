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
package org.azkfw.biz.graphics.chart.entity;

/**
 * このクラスは、サークルグラフのデータポイント情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/17
 * @author Kawakicchi
 */
public class CircleChartDataPoint {

	private double radius;
	private double angle;

	/**
	 * コンストラクタ
	 */
	public CircleChartDataPoint() {
		radius = 0.0f;
		angle = 0.0f;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aRadius 半径
	 * @param aAngle 角度
	 */
	public CircleChartDataPoint(final double aRadius, final double aAngle) {
		radius = aRadius;
		angle = aAngle;
	}

	public void setRadius(final double aRadius) {
		radius = aRadius;
	}

	public double getRadius() {
		return radius;
	}

	public void setAngle(final double aAngle) {
		angle = aAngle;
	}

	public double getAngle() {
		return angle;
	}
}
