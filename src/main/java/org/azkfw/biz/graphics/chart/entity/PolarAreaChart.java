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
 * このクラスは、鶏頭図情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public final class PolarAreaChart extends AbstractChart {

	// data
	private List<PolarAreaChartData> datas;
	private PolarAreaChartAxis axis;
	private PolarAreaChartSubAxis subAxis;

	public void setDatas(final List<PolarAreaChartData> aDatas) {
		datas = aDatas;
	}

	public List<PolarAreaChartData> getDatas() {
		return datas;
	}

	public void setAxis(final PolarAreaChartAxis aAxis) {
		axis = aAxis;
	}

	public PolarAreaChartAxis getAxis() {
		return axis;
	}

	public void setSubAxis(final PolarAreaChartSubAxis aAxis) {
		subAxis = aAxis;
	}

	public PolarAreaChartSubAxis getSubAxis() {
		return subAxis;
	}
}
