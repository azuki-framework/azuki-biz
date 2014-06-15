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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * このクラスは、鶏頭図のデータ情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public class PolarAreaChartData {

	// Data
	private List<PolarAreaChartDataPoint> points;

	// Layout
	private Color strokeColor;
	private Color fillColor;

	/**
	 * コンストラクタ
	 */
	public PolarAreaChartData() {
		points = new ArrayList<PolarAreaChartDataPoint>();
		strokeColor = null;
		fillColor = null;
	}

	/**
	 * コンストラクタ
	 * @param aPoints ポイント情報
	 */
	public PolarAreaChartData(final List<PolarAreaChartDataPoint> aPoints) {
		points = aPoints;
		strokeColor = null;
		fillColor = null;
	}

	public void setPoints(final List<PolarAreaChartDataPoint> aPoints) {
		points = aPoints;
	}

	public List<PolarAreaChartDataPoint> getPoints() {
		return points;
	}

	public void setStrokeColor(final Color aColor) {
		strokeColor = aColor;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setFillColor(final Color aColor) {
		fillColor = aColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
}
