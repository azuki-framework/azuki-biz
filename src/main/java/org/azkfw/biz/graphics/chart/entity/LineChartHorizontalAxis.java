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
import java.awt.Font;

public class LineChartHorizontalAxis {
	private double maximumValue;
	private double minimumValue;
	private double scaleCenter;
	private double scaleInterval;

	private String labelFormat;
	private Font labelFont;
	private Color labelFontColor;

	public void setMaximumValue(final double aValue) {
		maximumValue = aValue;
	}

	public void setMinimumValue(final double aValue) {
		minimumValue = aValue;
	}

	public void setScale(final double aCenter, final double aInterval) {
		scaleCenter = aCenter;
		scaleInterval = aInterval;
	}

	public void setLabelFormat(final String aFormat) {
		labelFormat = aFormat;
	}

	public void setLabelFont(final Font aFont) {
		labelFont = aFont;
	}

	public void setLabelFontColor(final Color aColor) {
		labelFontColor = aColor;
	}

	public double getMaximumValue() {
		return maximumValue;
	}

	public double getMinimumValue() {
		return minimumValue;
	}

	public double getScaleCenter() {
		return scaleCenter;
	}

	public double getScaleInterval() {
		return scaleInterval;
	}

	public String getLabelFormat() {
		return labelFormat;
	}

	public Font getLabelFont() {
		return labelFont;
	}

	public Color getLabelFontColor() {
		return labelFontColor;
	}

}
