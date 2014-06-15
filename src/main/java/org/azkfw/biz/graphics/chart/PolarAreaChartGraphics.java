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
import java.awt.Font;
import java.awt.FontMetrics;
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
public class PolarAreaChartGraphics extends AbstractChartGraphics {

	private PolarAreaChart chart;

	public void setChart(final PolarAreaChart aChart) {
		chart = aChart;
	}

	@Override
	protected void drawChart(final Graphics2D g) {
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

		// /////////////////////////////////////// 
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

		drawAxis2(axis, graphX, graphY, graphWidth, graphHeight, g);
	}

	private void drawSubAxis(final PolarAreaChartAxis aAxis, final PolarAreaChartSubAxis aSubAxis, final double aGraphX, final double aGraphY,
			final double aGraphWidth, final double aGraphHeight, final Graphics2D g) {
		double maximumValue = aAxis.getMaximumValue();

		double graphX = aGraphX + (aGraphWidth / 2.f); // グラフ中心X
		double graphY = aGraphY + (aGraphHeight / 2.f); // グラフ中心X
		double scaleX = (aGraphWidth / 2) / maximumValue;
		double scaleY = (aGraphHeight / 2) / maximumValue;

		g.setColor(Color.lightGray);
		for (int i = 0; i < 360; i += aSubAxis.getAngle()) {
			if (0 == i % 90) {
				continue;
			}
			double x = maximumValue * Math.cos(RADIANS(i));
			double y = maximumValue * Math.sin(RADIANS(i));

			double sWidth = x * scaleX;
			double sHeight = y * scaleY;

			g.drawLine((int) graphX, (int) graphY, (int) (graphX + sWidth), (int) (graphY - sHeight));
		}
	}

	private void drawAxis1(final PolarAreaChartAxis aAxis, final double aGraphX, final double aGraphY, final double aGraphWidth,
			final double aGraphHeight, final Graphics2D g) {
		double maximumValue = aAxis.getMaximumValue();
		double scale = aAxis.getScale();
		double subScale = aAxis.getSubScale();

		int memoriWidth = 5; // 目盛の線長さ

		double graphX = aGraphX + (aGraphWidth / 2.f); // グラフ中心X
		double graphY = aGraphY + (aGraphHeight / 2.f); // グラフ中心X
		double scaleWidth = (aGraphWidth / 2) / maximumValue;
		double scaleHeight = (aGraphHeight / 2) / maximumValue;

		// 補助サークル
		if (null != aAxis.getSubScaleStrokeColor()) {
			g.setColor(aAxis.getSubScaleStrokeColor());
			g.setStroke(new BasicStroke(0.5f));
			for (double rang = subScale; rang <= maximumValue; rang += subScale) {
				double sWidth = rang * scaleWidth;
				double sHeight = rang * scaleHeight;
				g.drawArc((int) (graphX - sWidth), (int) (graphY - sHeight), (int) (sWidth * 2.f), (int) (sHeight * 2), 0, 360);
			}
		}
		// サークル
		if (null != aAxis.getScaleStrokeColor()) {
			g.setColor(aAxis.getScaleStrokeColor());
			g.setStroke(new BasicStroke(0.5f));
			for (double rang = scale; rang <= maximumValue; rang += scale) {
				double sWidth = rang * scaleWidth;
				double sHeight = rang * scaleHeight;
				g.drawArc((int) (graphX - sWidth), (int) (graphY - sHeight), (int) (sWidth * 2.f), (int) (sHeight * 2), 0, 360);
			}
		}

		// 軸を描画
		g.setColor(Color.black);

		double scaleX = maximumValue * scaleWidth;
		double scaleY = maximumValue * scaleHeight;

		// 0°
		g.drawLine((int) graphX, (int) graphY, (int) (graphX + scaleX), (int) (graphY));
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleX2 = (v * scaleWidth);
			g.drawLine((int) (graphX + scaleX2), (int) graphY, (int) (graphX + scaleX2), (int) (graphY + memoriWidth));
		}

		// 90°
		g.drawLine((int) graphX, (int) graphY, (int) (graphX), (int) (graphY - scaleY));
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleY2 = (v * scaleHeight);
			g.drawLine((int) (graphX), (int) (graphY - scaleY2), (int) (graphX - memoriWidth), (int) (graphY - scaleY2));
		}

		// 180°
		g.drawLine((int) graphX, (int) graphY, (int) (graphX - scaleX), (int) (graphY));
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleX2 = (v * scaleWidth);
			g.drawLine((int) (graphX - scaleX2), (int) graphY, (int) (graphX - scaleX2), (int) (graphY + memoriWidth));
		}

		// 270°
		g.drawLine((int) graphX, (int) graphY, (int) (graphX), (int) (graphY + scaleY));
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleY2 = (v * scaleHeight);
			g.drawLine((int) (graphX), (int) (graphY + scaleY2), (int) (graphX - memoriWidth), (int) (graphY + scaleY2));
		}
	}

	private void drawAxis2(final PolarAreaChartAxis aAxis, final double aGraphX, final double aGraphY, final double aGraphWidth,
			final double aGraphHeight, final Graphics2D g) {
		double maximumValue = aAxis.getMaximumValue();
		double scale = aAxis.getScale();

		int memoriWidth = 5; // 目盛の線長さ
		int memoriFontSize = 16; // 目盛の文字サイズ

		double graphX = aGraphX + (aGraphWidth / 2.f); // グラフ中心X
		double graphY = aGraphY + (aGraphHeight / 2.f); // グラフ中心X
		double scaleWidth = (aGraphWidth / 2) / maximumValue;
		double scaleHeight = (aGraphHeight / 2) / maximumValue;

		// 軸を描画
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, memoriFontSize));
		FontMetrics fm = g.getFontMetrics();

		// 0°
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleX2 = (v * scaleWidth);

			String s = String.format("%.1f", v);
			int stringWidth = fm.stringWidth(s);
			g.drawString(s, (int) (graphX + scaleX2 - (stringWidth / 2)), (int) (graphY + memoriWidth + memoriFontSize));
		}

		// 90°
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleY2 = (v * scaleHeight);

			String s = String.format("%.1f", v);
			int stringWidth = fm.stringWidth(s);
			g.drawString(s, (int) (graphX - memoriWidth - stringWidth), (int) (graphY - scaleY2 + (memoriFontSize / 2)));
		}

		// 180°
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleX2 = (v * scaleWidth);

			String s = String.format("%.1f", -1 * v);
			int stringWidth = fm.stringWidth(s);
			g.drawString(s, (int) (graphX - scaleX2 - (stringWidth / 2)), (int) (graphY + memoriWidth + memoriFontSize));
		}

		// 270°
		for (double v = scale; v <= maximumValue; v += scale) {
			double scaleY2 = (v * scaleHeight);

			String s = String.format("%.1f", -1 * v);
			int stringWidth = fm.stringWidth(s);
			g.drawString(s, (int) (graphX - memoriWidth - stringWidth), (int) (graphY + scaleY2 + (memoriFontSize / 2)));
		}
	}

	private static double RADIANS(double deg) {
		return deg * Math.PI / 180.0;
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
				point2.setFillColor(new Color(0,255,0,64));
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
