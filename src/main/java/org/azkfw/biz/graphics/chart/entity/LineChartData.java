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
import java.util.ArrayList;
import java.util.List;

/**
 * このクラスは、折れ線グラフのデータ情報を格納したクラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public class LineChartData {

	// Data
	private List<LineChartDataPoint> points;

	//Layout
	private Color lineStrokeColor;

	private double pointRadius;
	private Color pointStrokeColor;
	private Color pointFillColor;

	private Font commentFont;
	private Color commentColor;
	private Color commentStrokeColor;
	private Color commentFillColor;

	/**
	 * コンストラクタ
	 */
	public LineChartData() {
		points = new ArrayList<LineChartDataPoint>();

		lineStrokeColor = null;
		pointRadius = -1.f;
		pointStrokeColor = null;
		pointFillColor = null;

		commentFont = null;
		commentColor = null;
		commentStrokeColor = null;
		commentFillColor = null;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aPoints ポイント情報
	 */
	public LineChartData(final List<LineChartDataPoint> aPoints) {
		points = aPoints;

		lineStrokeColor = null;
		pointRadius = -1.f;
		pointStrokeColor = null;
		pointFillColor = null;

		commentFont = null;
		commentColor = null;
		commentStrokeColor = null;
		commentFillColor = null;
	}

	public void setPoints(final List<LineChartDataPoint> aPoints) {
		points = aPoints;
	}

	public List<LineChartDataPoint> getPoints() {
		return points;
	}

	public void setLineStrokeColor(final Color aColor) {
		lineStrokeColor = aColor;
	}

	public Color getLineStrokeColor() {
		return lineStrokeColor;
	}

	public void setPointRadius(final double aRadius) {
		pointRadius = aRadius;
	}

	public double getPointRadius() {
		return pointRadius;
	}

	public void setPointStrokeColor(final Color aColor) {
		pointStrokeColor = aColor;
	}

	public Color getPointStrokeColor() {
		return pointStrokeColor;
	}

	public void setPointFillColor(final Color aColor) {
		pointFillColor = aColor;
	}

	public Color getPointFillColor() {
		return pointFillColor;
	}

	public void setCommentFont(final Font aFont) {
		commentFont = aFont;
	}

	public Font getCommentFont() {
		return commentFont;
	}

	public void setCommentColor(final Color aColor) {
		commentColor = aColor;
	}

	public Color getCommentColor() {
		return commentColor;
	}

	public void setCommentStrokeColor(final Color aColor) {
		commentStrokeColor = aColor;
	}

	public Color getCommentStrokeColor() {
		return commentStrokeColor;
	}

	public void setCommentFillColor(final Color aColor) {
		commentFillColor = aColor;
	}

	public Color getCommentFillColor() {
		return commentFillColor;
	}

}
