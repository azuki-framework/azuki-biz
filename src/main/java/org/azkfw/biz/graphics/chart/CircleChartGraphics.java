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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.azkfw.biz.graphics.chart.entity.CircleChart;
import org.azkfw.biz.graphics.chart.entity.CircleChartAxis;
import org.azkfw.biz.graphics.chart.entity.CircleChartData;
import org.azkfw.biz.graphics.chart.entity.CircleChartDataPoint;
import org.azkfw.biz.graphics.chart.entity.CircleChartSubAxis;
import org.azkfw.biz.graphics.entity.Margin;

/**
 * このクラスは、サークルグラフを描画するグラフィクスクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/17
 * @author Kawakicchi
 */
public class CircleChartGraphics extends AbstractCircleChartGraphics {

	private CircleChart chart;

	public void setChart(final CircleChart aChart) {
		chart = aChart;
	}

	@Override
	protected void drawCircleChart(final Graphics2D g) {
		int width = (int) getWidth();
		int height = (int) getHeight();
		Margin margin = chart.getMargin();

		CircleChartAxis axis = chart.getAxis();
		CircleChartSubAxis subAxis = chart.getSubAxis();

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
		List<CircleChartData> datas = chart.getDatas();
		for (CircleChartData data : datas) {
			List<CircleChartDataPoint> points = data.getPoints();

			int[] pxs = new int[points.size() + 1];
			int[] pys = new int[points.size() + 1];

			for (int i = 0; i < points.size(); i++) {
				CircleChartDataPoint point = points.get(i);
				double x = point.getRadius() * Math.cos(RADIANS(point.getAngle()));
				double y = point.getRadius() * Math.sin(RADIANS(point.getAngle()));
				pxs[i] = (int) (middleX + (x * scaleWidth));
				pys[i] = (int) (middleY - (y * scaleHeight));
			}
			CircleChartDataPoint point = points.get(0);
			double x = point.getRadius() * Math.cos(RADIANS(point.getAngle()));
			double y = point.getRadius() * Math.sin(RADIANS(point.getAngle()));
			pxs[points.size()] = (int) (middleX + (x * scaleWidth));
			pys[points.size()] = (int) (middleY - (y * scaleHeight));

			if (null != data.getFillColor()) {
				g.setColor(data.getFillColor());
				g.fillPolygon(pxs, pys, points.size() + 1);
			}
			if (null != data.getStrokeColor()) {
				g.setColor(data.getStrokeColor());
				g.drawPolyline(pxs, pys, points.size() + 1);
			}
		}
		// //////////////////////////////////////////////////////////////////

		drawAxis2(axis, graphX, graphY, graphWidth, graphHeight, g);
	}

	public static void main(final String[] args) {

		CircleChart chart = new CircleChart();
		chart.setMargin(new Margin(25.f, 25.f, 25.f, 25.f));
		chart.setBackgroundColor(Color.white);

		CircleChartAxis axis = new CircleChartAxis();
		axis.setMaximumValue(2.0);
		axis.setScale(1.0);
		axis.setScaleStrokeColor(Color.darkGray);
		axis.setSubScale(0.5);
		//axis.setSubScaleStrokeColor(Color.lightGray);

		CircleChartSubAxis subAxis = new CircleChartSubAxis();
		subAxis.setAngle(30);

		List<CircleChartData> datas = new ArrayList<CircleChartData>();
		CircleChartData data1 = new CircleChartData();
		CircleChartData data2 = new CircleChartData();
		data1.setStrokeColor(Color.red);
		data2.setStrokeColor(Color.blue);
		data2.setFillColor(new Color(0, 0, 255, 64));
		List<CircleChartDataPoint> points1 = new ArrayList<CircleChartDataPoint>();
		List<CircleChartDataPoint> points2 = new ArrayList<CircleChartDataPoint>();
		for (int i = 0; i < 36; i++) {
			CircleChartDataPoint point1 = new CircleChartDataPoint(1.2, i * 10);
			points1.add(point1);
			CircleChartDataPoint point2 = new CircleChartDataPoint(0.05 * i, i * 10);
			points2.add(point2);
		}
		data1.setPoints(points1);
		data2.setPoints(points2);

		datas.add(data1);
		datas.add(data2);

		chart.setAxis(axis);
		chart.setSubAxis(subAxis);
		chart.setDatas(datas);

		CircleChartGraphics g = new CircleChartGraphics();
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
