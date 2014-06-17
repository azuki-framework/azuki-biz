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

import java.util.List;

/**
 * このクラスは、サークルグラフ情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/17
 * @author Kawakicchi
 */
public final class CircleChart extends AbstractChart {

	// data
	private List<CircleChartData> datas;
	private CircleChartAxis axis;
	private CircleChartSubAxis subAxis;

	public void setDatas(final List<CircleChartData> aDatas) {
		datas = aDatas;
	}

	public List<CircleChartData> getDatas() {
		return datas;
	}

	public void setAxis(final CircleChartAxis aAxis) {
		axis = aAxis;
	}

	public CircleChartAxis getAxis() {
		return axis;
	}

	public void setSubAxis(final CircleChartSubAxis aAxis) {
		subAxis = aAxis;
	}

	public CircleChartSubAxis getSubAxis() {
		return subAxis;
	}
}
