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
package jp.afw.biz.graphics.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.List;

import jp.afw.biz.graphics.chart.entity.LineChart;
import jp.afw.biz.graphics.chart.entity.LineChartData;
import jp.afw.biz.graphics.chart.entity.LineChartDataPoint;
import jp.afw.biz.graphics.chart.entity.LineChartHorizontalAxis;
import jp.afw.biz.graphics.chart.entity.LineChartVerticalAxis;
import jp.afw.biz.graphics.entity.Rect;
import jp.afw.core.util.ListUtility;
import jp.afw.core.util.StringUtility;

public class LineChartGraphics extends AbstractChartGraphics {

	private LineChart chart;

	private double width;
	private double height;

	public void setChart(final LineChart aChart) {
		chart = aChart;
	}

	public void setSize(final double aWidth, final double aheight) {
		width = aWidth;
		height = aheight;
	}

	@Override
	public BufferedImage drawChart() {
		BufferedImage image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = image.createGraphics();

		LineChartVerticalAxis vAxis = chart.getVerticalAxis();
		LineChartHorizontalAxis hAxis = chart.getHorizontalAxis();

		// 縦の目盛の一番小さい番号と一番大きい番号を取得
		boolean vScaleFlag = false;
		int vMinCnt;
		if (vAxis.getScaleCenter() >= vAxis.getMinimumValue()) {
			double dif = vAxis.getScaleCenter() - vAxis.getMinimumValue() + vAxis.getScaleInterval();
			double div = ((int) (dif / vAxis.getScaleInterval())) * vAxis.getScaleInterval();
			double min = vAxis.getScaleCenter() - div;
			while (min < vAxis.getMinimumValue()) {
				min += vAxis.getScaleInterval();
			}
			vMinCnt = (int) ((min - vAxis.getScaleCenter()) / vAxis.getScaleInterval());
		} else {
			double dif = vAxis.getMinimumValue() - vAxis.getScaleCenter() - vAxis.getScaleInterval();
			double div = ((int) (dif / vAxis.getScaleInterval())) * vAxis.getScaleInterval();
			double min = vAxis.getScaleCenter() + div;
			while (min < vAxis.getMinimumValue()) {
				min += vAxis.getScaleInterval();
			}
			vMinCnt = (int) ((min - vAxis.getScaleCenter()) / vAxis.getScaleInterval());
		}
		int vMaxCnt = vMinCnt;
		if (vAxis.getMaximumValue() >= (vAxis.getScaleCenter() + vAxis.getScaleInterval() * vMaxCnt)) {
			vScaleFlag = true;
			while (vAxis.getMaximumValue() >= (vAxis.getScaleCenter() + vAxis.getScaleInterval() * (vMaxCnt + 1))) {
				vMaxCnt++;
			}
		}
		// System.out.println("vertical scale " + vMinCnt + ":" + vMaxCnt);

		// 横の目盛の一番小さい番号と一番大きい番号を取得
		boolean hScaleFlag = false;
		int hMinCnt;
		if (hAxis.getScaleCenter() >= hAxis.getMinimumValue()) {
			double dif = hAxis.getScaleCenter() - hAxis.getMinimumValue() + hAxis.getScaleInterval();
			double div = ((int) (dif / hAxis.getScaleInterval())) * hAxis.getScaleInterval();
			double min = hAxis.getScaleCenter() - div;
			while (min < hAxis.getMinimumValue()) {
				min += hAxis.getScaleInterval();
			}
			hMinCnt = (int) ((min - hAxis.getScaleCenter()) / hAxis.getScaleInterval());
		} else {
			double dif = hAxis.getMinimumValue() - hAxis.getScaleCenter() - hAxis.getScaleInterval();
			double div = ((int) (dif / hAxis.getScaleInterval())) * hAxis.getScaleInterval();
			double min = hAxis.getScaleCenter() + div;
			while (min < hAxis.getMinimumValue()) {
				min += hAxis.getScaleInterval();
			}
			hMinCnt = (int) ((min - hAxis.getScaleCenter()) / hAxis.getScaleInterval());
		}
		int hMaxCnt = hMinCnt;
		if (hAxis.getMaximumValue() >= (hAxis.getScaleCenter() + hAxis.getScaleInterval() * hMaxCnt)) {
			hScaleFlag = true;
			while (hAxis.getMaximumValue() >= (hAxis.getScaleCenter() + hAxis.getScaleInterval() * (hMaxCnt + 1))) {
				hMaxCnt++;
			}
		}
		// System.out.println("horizontal scale " + hMinCnt + ":" + hMaxCnt);

		int vScaleMaxWidth = 0;
		if (vScaleFlag) {
			g.setFont(vAxis.getLabelFont());
			for (int i = vMinCnt; i <= vMaxCnt; i++) {
				double value = vAxis.getScaleCenter() + vAxis.getScaleInterval() * i;
				String string = String.format(vAxis.getLabelFormat(), value);
				// System.out.println(string);

				FontMetrics fm = g.getFontMetrics();
				int fontWidth = fm.stringWidth(string);
				if (vScaleMaxWidth < fontWidth) {
					vScaleMaxWidth = fontWidth;
				}
			}
		}
		int hScaleMaxHeight = 0;
		if (hScaleFlag) {
			g.setFont(hAxis.getLabelFont());
			for (int i = hMinCnt; i <= hMaxCnt; i++) {
				double value = hAxis.getScaleCenter() + hAxis.getScaleInterval() * i;
				String string = String.format(hAxis.getLabelFormat(), value);
				// System.out.println(string);

				if (string.length() > 0) {
					hScaleMaxHeight = hAxis.getLabelFont().getSize();
				}
			}
		}

		Rect rtChart = new Rect();
		double labelMargin = 4;
		if (0 == vScaleMaxWidth) {
			rtChart.setPosition(chart.getMargin().getLeft(), chart.getMargin().getTop());
		} else {
			rtChart.setPosition(chart.getMargin().getLeft() + vScaleMaxWidth + labelMargin, chart.getMargin().getTop());
		}
		if (0 == hScaleMaxHeight) {
			rtChart.setSize(width - (rtChart.getX() + chart.getMargin().getRight()), height - (rtChart.getY() + chart.getMargin().getBottom()));
		} else {
			rtChart.setSize(width - (rtChart.getX() + chart.getMargin().getRight()), height
					- (rtChart.getY() + labelMargin + hScaleMaxHeight + chart.getMargin().getBottom()));
		}
		double vInterval = rtChart.getHeight() / (vAxis.getMaximumValue() - vAxis.getMinimumValue());
		double hInterval = rtChart.getWidth() / (hAxis.getMaximumValue() - hAxis.getMinimumValue());
		if (vInterval < 0)
			vInterval *= -1;
		if (hInterval < 0)
			hInterval *= -1;
		// System.out.println("interval " + hInterval + ":" + vInterval);

		g.setColor((null != chart.getBackgroundColor()) ? chart.getBackgroundColor() : Color.WHITE);
		g.fillRect((int) 0, (int) 0, (int) width, (int) height);
		g.setColor(new Color(200, 200, 200, 255));
		g.drawRect((int) rtChart.getX(), (int) rtChart.getY(), (int) rtChart.getWidth(), (int) rtChart.getHeight());

		// 縦目盛
		if (vScaleFlag) {
			g.setFont(vAxis.getLabelFont());
			for (int i = vMinCnt; i <= vMaxCnt; i++) {
				double value = vAxis.getScaleCenter() + vAxis.getScaleInterval() * i;
				String string = String.format(vAxis.getLabelFormat(), value);
				FontMetrics fm = g.getFontMetrics();
				int fontWidth = fm.stringWidth(string);

				double x = rtChart.getX();
				double y = (rtChart.getY() + rtChart.getHeight()) - (value - vAxis.getMinimumValue()) * vInterval;

				g.setColor(vAxis.getLabelFontColor());
				if (0 == vScaleMaxWidth) {
					g.drawString(string, (int) (x - fontWidth), (int) (y + vAxis.getLabelFont().getSize() / 2));
				} else {
					g.drawString(string, (int) (x - fontWidth - labelMargin), (int) (y + vAxis.getLabelFont().getSize() / 2));
				}

				g.setColor(new Color(200, 200, 200, 255));
				g.drawLine((int) x, (int) y, (int) (x + rtChart.getWidth()), (int) y);
			}
		}

		// 横目盛
		if (hScaleFlag) {
			g.setFont(hAxis.getLabelFont());
			for (int i = hMinCnt; i <= hMaxCnt; i++) {
				double value = hAxis.getScaleCenter() + hAxis.getScaleInterval() * i;
				String string = String.format(hAxis.getLabelFormat(), value);
				FontMetrics fm = g.getFontMetrics();
				int fontWidth = fm.stringWidth(string);

				double y = rtChart.getY();
				double x = (rtChart.getX()) + (value - hAxis.getMinimumValue()) * hInterval;

				g.setColor(hAxis.getLabelFontColor());
				if (0 == hScaleMaxHeight) {
					g.drawString(string, (int) (x - fontWidth / 2), (int) (y + rtChart.getHeight() + vAxis.getLabelFont().getSize()));
				} else {
					g.drawString(string, (int) (x - fontWidth / 2), (int) (y + rtChart.getHeight() + labelMargin + vAxis.getLabelFont().getSize()));
				}

				g.setColor(new Color(200, 200, 200, 255));
				g.drawLine((int) x, (int) y, (int) (x), (int) (y + rtChart.getHeight()));
			}
		}

		Shape clip = g.getClip();
		g.setClip((int) rtChart.getX(), (int) rtChart.getY(), (int) rtChart.getWidth(), (int) rtChart.getHeight());

		List<LineChartData> datas = chart.getDatas();

		// draw line
		if (ListUtility.isNotEmpty(datas)) {
			for (LineChartData data : datas) {
				if (null != data) {
					LineChartDataPoint beforePoint = null;
					Color strokeColor = (null != data.getLineStrokeColor()) ? data.getLineStrokeColor() : Color.black;
					List<LineChartDataPoint> points = data.getPoints();

					g.setColor(strokeColor);
					if (ListUtility.isNotEmpty(points)) {
						for (LineChartDataPoint point : points) {
							if (null != point) {
								if (null != beforePoint) {
									double x1 = rtChart.getX() + (beforePoint.getHorizontalValue() - hAxis.getMinimumValue()) * hInterval;
									double y1 = (rtChart.getY() + rtChart.getHeight()) - (beforePoint.getVerticalValue() - vAxis.getMinimumValue())
											* vInterval;
									double x2 = rtChart.getX() + (point.getHorizontalValue() - hAxis.getMinimumValue()) * hInterval;
									double y2 = (rtChart.getY() + rtChart.getHeight()) - (point.getVerticalValue() - vAxis.getMinimumValue())
											* vInterval;

									g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
								}
								beforePoint = point;
							}
						}
					}
				}
			}
		}
		g.setClip(clip);

		// draw point
		if (ListUtility.isNotEmpty(datas)) {
			for (LineChartData data : datas) {
				if (null != data) {
					List<LineChartDataPoint> points = data.getPoints();
					if (ListUtility.isNotEmpty(points)) {
						for (LineChartDataPoint point : points) {
							if (null != point) {
								if (vAxis.getMinimumValue() <= point.getVerticalValue() && vAxis.getMaximumValue() >= point.getVerticalValue()
										&& hAxis.getMinimumValue() <= point.getHorizontalValue()
										&& hAxis.getMaximumValue() >= point.getHorizontalValue()) {

									double x = rtChart.getX() + (point.getHorizontalValue() - hAxis.getMinimumValue()) * hInterval;
									double y = (rtChart.getY() + rtChart.getHeight()) - (point.getVerticalValue() - vAxis.getMinimumValue())
											* vInterval;

									Color strokeColor = (null != point.getPointStrokeColor()) ? point.getPointStrokeColor() : data
											.getPointStrokeColor();
									if (null == strokeColor) {
										strokeColor = Color.black;
									}
									Color fillColor = (null != point.getPointFillColor()) ? point.getPointFillColor() : data.getPointFillColor();
									if (null == fillColor) {
										fillColor = Color.black;
									}
									double radius = (0 <= point.getPointRadius()) ? point.getPointRadius() : data.getPointRadius();
									if (0 > radius) {
										radius = 0;
									}

									if (0 < radius) {
										g.setColor(fillColor);
										g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));

										g.setColor(strokeColor);
										g.drawOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
									}
								}
							}
						}
					}
				}
			}
		}

		// draw comment
		if (ListUtility.isNotEmpty(datas)) {
			for (LineChartData data : datas) {
				if (null != data) {
					List<LineChartDataPoint> points = data.getPoints();
					if (ListUtility.isNotEmpty(points)) {
						for (LineChartDataPoint point : points) {
							if (null != point) {
								if (vAxis.getMinimumValue() <= point.getVerticalValue() && vAxis.getMaximumValue() >= point.getVerticalValue()
										&& hAxis.getMinimumValue() <= point.getHorizontalValue()
										&& hAxis.getMaximumValue() >= point.getHorizontalValue()) {
									if (StringUtility.isNotEmpty(point.getComment())) {

										double x = rtChart.getX() + (point.getHorizontalValue() - hAxis.getMinimumValue()) * hInterval;
										double y = (rtChart.getY() + rtChart.getHeight()) - (point.getVerticalValue() - vAxis.getMinimumValue())
												* vInterval;

										Font font = (null != point.getCommentFont()) ? point.getCommentFont() : data.getCommentFont();
										if (null == font) {
											font = new Font(Font.SERIF, Font.PLAIN, 12);
										}
										Color commentColor = (null != point.getCommentColor()) ? point.getCommentColor() : data.getCommentColor();
										if (null == commentColor) {
											commentColor = Color.black;
										}
										Color commentStrokeColor = (null != point.getCommentStrokeColor()) ? point.getCommentStrokeColor() : data
												.getCommentStrokeColor();
										if (null == commentStrokeColor) {
											commentStrokeColor = Color.black;
										}
										Color commentFillColor = (null != point.getCommentFillColor()) ? point.getCommentFillColor() : data
												.getCommentFillColor();
										if (null == commentFillColor) {
											commentFillColor = Color.white;
										}

										g.setFont(font);
										FontMetrics fm = g.getFontMetrics();
										int fontWidth = fm.stringWidth(point.getComment());
										int fontHeight = font.getSize();

										boolean leftFlag = true;
										boolean topFlag = true;
										double x2 = x - 20 - 2 * 2 - fontWidth;
										double y2 = y - 20 - 2 - (fontHeight / 2);

										if (x2 < rtChart.getX()) {
											leftFlag = false;
											x2 = x + 20;
										}
										if (y2 < rtChart.getY()) {
											topFlag = false;
											y2 = y + 20 - 2 - (fontHeight / 2);
										}

										g.setColor(commentStrokeColor);
										if (leftFlag) {
											g.drawLine((int) (x2 + fontWidth + 2 * 2), (int) (y2 + (fontHeight) / 2 + 2), (int) x, (int) y);
										} else {
											g.drawLine((int) (x2), (int) (y2 + (fontHeight) / 2 + 2), (int) x, (int) y);
										}

										g.setColor(commentFillColor);
										g.fillRect((int) x2, (int) y2, fontWidth + 2 * 2, fontHeight + 2 * 2);

										g.setColor(commentStrokeColor);
										g.drawRect((int) x2, (int) y2, fontWidth + 2 * 2, fontHeight + 2 * 2);

										g.setColor(commentColor);
										g.drawString(point.getComment(), (int) (x2 + 3), (int) (y2 + 1 + fontHeight));

									}
								}
							}
						}
					}
				}
			}
		}

		return image;
	}

}
