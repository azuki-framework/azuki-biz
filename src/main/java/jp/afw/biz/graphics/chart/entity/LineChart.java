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
package jp.afw.biz.graphics.chart.entity;

import java.awt.Color;
import java.util.List;

import jp.afw.biz.graphics.entity.Margin;

public class LineChart extends AbstractChart {

	private List<LineChartData> datas;

	private LineChartVerticalAxis verticalAxis;
	private LineChartHorizontalAxis horizontalAxis;

	private Margin margin;
	private Color backgroundColor;

	
	
	public void setDatas(final List<LineChartData> aDatas) {
		datas = aDatas;
	}

	public void setVerticalAxis(final LineChartVerticalAxis aAxis) {
		verticalAxis = aAxis;
	}

	public void setHorizontalAxis(final LineChartHorizontalAxis aAxis) {
		horizontalAxis = aAxis;
	}

	public void setMargin(final Margin aMargin) {
		margin = aMargin;
	}

	public void setBackgroundColor(final Color aColor) {
		backgroundColor = aColor;
	}

	
	
	public List<LineChartData> getDatas() {
		return datas;
	}

	public LineChartVerticalAxis getVerticalAxis() {
		return verticalAxis;
	}

	public LineChartHorizontalAxis getHorizontalAxis() {
		return horizontalAxis;
	}

	public Margin getMargin() {
		return margin;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}
}
