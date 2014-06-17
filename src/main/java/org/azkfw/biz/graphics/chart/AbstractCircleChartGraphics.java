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

import org.azkfw.biz.graphics.chart.entity.CircleChartAxis;
import org.azkfw.biz.graphics.chart.entity.CircleChartSubAxis;

/**
 * このクラスは、サークルグラフを描画するための基底グラフィクスクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/17
 * @author Kawakicchi
 */
public abstract class AbstractCircleChartGraphics extends AbstractChartGraphics {

	@Override
	protected void drawChart(final Graphics2D g) {
		drawCircleChart(g);
	}

	protected abstract void drawCircleChart(final Graphics2D g);

	protected void drawSubAxis(final CircleChartAxis aAxis, final CircleChartSubAxis aSubAxis, final double aGraphX, final double aGraphY,
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

	protected void drawAxis1(final CircleChartAxis aAxis, final double aGraphX, final double aGraphY, final double aGraphWidth,
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

	protected void drawAxis2(final CircleChartAxis aAxis, final double aGraphX, final double aGraphY, final double aGraphWidth,
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

	protected static double RADIANS(double aAngle) {
		return aAngle * Math.PI / 180.0;
	}

}
