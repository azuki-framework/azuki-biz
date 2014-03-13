package jp.afw.biz.graphics.chart.entity;

import java.awt.Color;
import java.awt.Font;

public class LineChartHorizontalAxis {
	private double maximumValue;
	private double minimumValue;
	private double scaleCenter;
	private double scaleInterval;

	private String labelFormat;
	private Font labelFont;
	private Color labelFontColor;

	public void setMaximumValue(final double aValue) {
		maximumValue = aValue;
	}

	public void setMinimumValue(final double aValue) {
		minimumValue = aValue;
	}

	public void setScale(final double aCenter, final double aInterval) {
		scaleCenter = aCenter;
		scaleInterval = aInterval;
	}

	public void setLabelFormat(final String aFormat) {
		labelFormat = aFormat;
	}

	public void setLabelFont(final Font aFont) {
		labelFont = aFont;
	}

	public void setLabelFontColor(final Color aColor) {
		labelFontColor = aColor;
	}

	public double getMaximumValue() {
		return maximumValue;
	}

	public double getMinimumValue() {
		return minimumValue;
	}

	public double getScaleCenter() {
		return scaleCenter;
	}

	public double getScaleInterval() {
		return scaleInterval;
	}

	public String getLabelFormat() {
		return labelFormat;
	}

	public Font getLabelFont() {
		return labelFont;
	}

	public Color getLabelFontColor() {
		return labelFontColor;
	}

}
