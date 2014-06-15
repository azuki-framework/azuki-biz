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

/**
 * このクラスは、鶏頭図の軸情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public class PolarAreaChartAxis {

	private double maximumValue;

	private double scale;
	private double subScale;

	private Color scaleStrokeColor;
	private Color subScaleStrokeColor;

	public PolarAreaChartAxis() {

	}

	public void setMaximumValue(final double aValue) {
		maximumValue = aValue;
	}

	public double getMaximumValue() {
		return maximumValue;
	}

	public void setScale(final double aScale) {
		scale = aScale;
	}

	public double getScale() {
		return scale;
	}

	public void setSubScale(final double aScale) {
		subScale = aScale;
	}

	public double getSubScale() {
		return subScale;
	}

	public void setScaleStrokeColor(final Color aColor) {
		scaleStrokeColor = aColor;
	}

	public Color getScaleStrokeColor() {
		return scaleStrokeColor;
	}

	public void setSubScaleStrokeColor(final Color aColor) {
		subScaleStrokeColor = aColor;
	}

	public Color getSubScaleStrokeColor() {
		return subScaleStrokeColor;
	}
}
