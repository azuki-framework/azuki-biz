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
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class LineChartData {

	private List<LineChartDataPoint> points;

	private Color lineStrokeColor;

	private double pointRadius;
	private Color pointStrokeColor;
	private Color pointFillColor;

	private Font commentFont;
	private Color commentColor;
	private Color commentStrokeColor;
	private Color commentFillColor;

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

	public void setLineStrokeColor(final Color aColor) {
		lineStrokeColor = aColor;
	}

	public void setPointRadius(final double aRadius) {
		pointRadius = aRadius;
	}

	public void setPointStrokeColor(final Color aColor) {
		pointStrokeColor = aColor;
	}

	public void setPointFillColor(final Color aColor) {
		pointFillColor = aColor;
	}

	public void setCommentFont(final Font aFont) {
		commentFont = aFont;
	}

	public void setCommentColor(final Color aColor) {
		commentColor = aColor;
	}

	public void setCommentStrokeColor(final Color aColor) {
		commentStrokeColor = aColor;
	}

	public void setCommentFillColor(final Color aColor) {
		commentFillColor = aColor;
	}

	public List<LineChartDataPoint> getPoints() {
		return points;
	}

	public Color getLineStrokeColor() {
		return lineStrokeColor;
	}

	public double getPointRadius() {
		return pointRadius;
	}

	public Color getPointStrokeColor() {
		return pointStrokeColor;
	}

	public Color getPointFillColor() {
		return pointFillColor;
	}

	public Font getCommentFont() {
		return commentFont;
	}

	public Color getCommentColor() {
		return commentColor;
	}

	public Color getCommentStrokeColor() {
		return commentStrokeColor;
	}

	public Color getCommentFillColor() {
		return commentFillColor;
	}

}
