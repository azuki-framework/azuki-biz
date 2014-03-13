package jp.afw.biz.graphics.entity;

public class Margin {

	private double top;
	private double bottom;
	private double left;
	private double right;
	
	public Margin() {
		top = 0;
		bottom = 0;
		left = 0;
		right = 0;
	}
	
	public Margin(final double aTop, final double aBottom, final double aLeft, final double aRight) {
		top = aTop;
		bottom = aBottom;
		left = aLeft;
		right = aRight;
	}
	
	public double getTop() {
		return top;
	}
	public double getBottom() {
		return bottom;
	}
	public double getLeft() {
		return left;
	}
	public double getRight() {
		return right;
	}
	
}
