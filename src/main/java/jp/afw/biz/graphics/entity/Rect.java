package jp.afw.biz.graphics.entity;

public class Rect {

	private double x;
	private double y;
	private double width;
	private double height;

	public Rect() {

	}

	public void setPosition(final double aX, final double aY) {
		x = aX;
		y = aY;
	}

	public void setSize(final double aWidth, final double aHeight) {
		width = aWidth;
		height = aHeight;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
