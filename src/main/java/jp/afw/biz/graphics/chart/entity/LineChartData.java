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
