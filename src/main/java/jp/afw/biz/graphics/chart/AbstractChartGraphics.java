package jp.afw.biz.graphics.chart;

import java.awt.image.BufferedImage;

import jp.afw.biz.graphics.AbstractGraphics;

public abstract class AbstractChartGraphics extends AbstractGraphics {

	@Override
	public BufferedImage draw() {
		return drawChart();
	}

	protected abstract BufferedImage drawChart();
}
