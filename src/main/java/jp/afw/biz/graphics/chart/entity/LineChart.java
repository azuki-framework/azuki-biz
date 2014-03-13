package jp.afw.biz.graphics.chart.entity;

import java.awt.Color;
import java.util.List;

import jp.afw.biz.graphics.entity.Margin;

public class LineChart extends AbstractChart {

	private List<LineChartData> datas;

	private LineChartVerticalAxis verticalAxis;
	private LineChartHorizontalAxis horizontalAxis;

	private Margin margin;
	private Color backgroundColor;

	
	
	public void setDatas(final List<LineChartData> aDatas) {
		datas = aDatas;
	}

	public void setVerticalAxis(final LineChartVerticalAxis aAxis) {
		verticalAxis = aAxis;
	}

	public void setHorizontalAxis(final LineChartHorizontalAxis aAxis) {
		horizontalAxis = aAxis;
	}

	public void setMargin(final Margin aMargin) {
		margin = aMargin;
	}

	public void setBackgroundColor(final Color aColor) {
		backgroundColor = aColor;
	}

	
	
	public List<LineChartData> getDatas() {
		return datas;
	}

	public LineChartVerticalAxis getVerticalAxis() {
		return verticalAxis;
	}

	public LineChartHorizontalAxis getHorizontalAxis() {
		return horizontalAxis;
	}

	public Margin getMargin() {
		return margin;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}
}
