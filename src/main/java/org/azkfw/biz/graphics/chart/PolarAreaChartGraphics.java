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
package org.azkfw.biz.graphics.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.azkfw.biz.graphics.chart.entity.PolarAreaChart;
import org.azkfw.biz.graphics.chart.entity.PolarAreaChartAxis;
import org.azkfw.biz.graphics.chart.entity.PolarAreaChartData;
import org.azkfw.biz.graphics.chart.entity.PolarAreaChartDataPoint;
import org.azkfw.biz.graphics.chart.entity.PolarAreaChartSubAxis;
import org.azkfw.biz.graphics.entity.Margin;

/**
 * このクラスは、鶏頭図を描画するグラフィクスクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public class PolarAreaChartGraphics extends AbstractCircleChartGraphics {

	private PolarAreaChart chart;

	public void setChart(final PolarAreaChart aChart) {
		chart = aChart;
	}

	@Override
	protected void drawCircleChart(final Graphics2D g) {
		int width = (int) getWidth();
		int height = (int) getHeight();
		Margin margin = chart.getMargin();

		PolarAreaChartAxis axis = chart.getAxis();
		PolarAreaChartSubAxis subAxis = chart.getSubAxis();

		// //////////////////////////////////////////////////
		double middleX = getWidth() / 2.f;
		double middleY = getHeight() / 2.f;

		double graphX = margin.getLeft();
		double graphY = margin.getTop();
		double graphWidth = getWidth() - (margin.getLeft() + margin.getRight());
		double graphHeight = getHeight() - (margin.getTop() + margin.getBottom());

		double scaleWidth = (graphWidth / 2.f) / axis.getMaximumValue();
		double scaleHeight = (graphHeight / 2.f) / axis.getMaximumValue();

		if (null != chart.getBackgroundColor()) {
			g.setColor(chart.getBackgroundColor());
			g.fillRect((int) 0, (int) 0, (int) width, (int) height);
		}

		if (null != subAxis) {
			drawSubAxis(axis, subAxis, graphX, graphY, graphWidth, graphHeight, g);
		}
		drawAxis1(axis, graphX, graphY, graphWidth, graphHeight, g);

		// //////////////////////////////////////////////////////////////////
		List<PolarAreaChartData> datas = chart.getDatas();
		for (PolarAreaChartData data : datas) {
			List<PolarAreaChartDataPoint> points = data.getPoints();

			int angle = 360 / points.size();

			g.setStroke(new BasicStroke(1.5f));
			for (int i = 0; i < points.size(); i++) {
				PolarAreaChartDataPoint point = points.get(i);

				double value = point.getValue();
				if (0 == value) {
					continue;
				}

				double sWidth = value * scaleWidth;
				double sHeight = value * scaleHeight;

				Color fillColor = (null != point.getFillColor()) ? point.getFillColor() : data.getFillColor();
				Color strokeColor = (null != point.getStrokeColor()) ? point.getStrokeColor() : data.getStrokeColor();
				if (null != fillColor) {
					g.setColor(fillColor);
					g.fillArc((int) (middleX - sWidth) + 1, (int) (middleY - sHeight) + 1, (int) (sWidth * 2.f), (int) (sHeight * 2), i * angle,
							angle);
				}
				if (null != strokeColor) {
					g.setColor(strokeColor);
					g.drawArc((int) (middleX - sWidth) + 1, (int) (middleY - sHeight) + 1, (int) (sWidth * 2.f), (int) (sHeight * 2), i * angle,
							angle);
				}
			}
		}
		// //////////////////////////////////////////////////////////////////

		drawAxis2(axis, graphX, graphY, graphWidth, graphHeight, g);
	}

	public static void main(final String[] args) {

		PolarAreaChart chart = new PolarAreaChart();
		chart.setMargin(new Margin(25.f, 25.f, 25.f, 25.f));
		chart.setBackgroundColor(Color.white);

		PolarAreaChartAxis axis = new PolarAreaChartAxis();
		axis.setMaximumValue(2.0);
		axis.setScale(1.0);
		axis.setScaleStrokeColor(Color.darkGray);
		axis.setSubScale(0.5);
		//axis.setSubScaleStrokeColor(Color.lightGray);

		PolarAreaChartSubAxis subAxis = new PolarAreaChartSubAxis();
		subAxis.setAngle(30);

		List<PolarAreaChartData> datas = new ArrayList<PolarAreaChartData>();
		PolarAreaChartData data1 = new PolarAreaChartData();
		PolarAreaChartData data2 = new PolarAreaChartData();
		data1.setStrokeColor(Color.red);
		data2.setStrokeColor(Color.blue);
		data2.setFillColor(new Color(0, 0, 255, 64));
		List<PolarAreaChartDataPoint> points1 = new ArrayList<PolarAreaChartDataPoint>();
		List<PolarAreaChartDataPoint> points2 = new ArrayList<PolarAreaChartDataPoint>();
		for (int i = 0; i < 12; i++) {
			PolarAreaChartDataPoint point1 = new PolarAreaChartDataPoint(0.1 * i);
			points1.add(point1);
			PolarAreaChartDataPoint point2 = new PolarAreaChartDataPoint(1.5 - 0.1 * i);
			points2.add(point2);
			if (i == 5) {
				point2.setFillColor(new Color(0, 255, 0, 64));
				point2.setStrokeColor(new Color(0, 255, 0, 200));
			}
		}
		data1.setPoints(points1);
		data2.setPoints(points2);

		datas.add(data1);
		datas.add(data2);

		chart.setAxis(axis);
		chart.setSubAxis(subAxis);
		chart.setDatas(datas);

		PolarAreaChartGraphics g = new PolarAreaChartGraphics();
		g.setSize(800, 800);
		;
		g.setChart(chart);

		BufferedImage image = g.draw();

		if (1 <= args.length) {
			try {
				File file = new File(args[0]);
				ImageIO.write(image, "png", file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
