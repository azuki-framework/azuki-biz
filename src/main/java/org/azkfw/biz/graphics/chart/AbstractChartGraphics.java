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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.azkfw.biz.graphics.AbstractGraphics;

/**
 * このクラスは、チャートを描画する為の基底グラフィクスクラスです。
 * 
 * @since 1.0.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public abstract class AbstractChartGraphics extends AbstractGraphics {

	/**
	 * width
	 */
	private double width;

	/**
	 * height
	 */
	private double height;

	/**
	 * チャートサイズを設定する。
	 * 
	 * @param aWidth width
	 * @param aheight height
	 */
	public final void setSize(final double aWidth, final double aheight) {
		width = aWidth;
		height = aheight;
	}

	protected final double getWidth() {
		return width;
	}

	protected final double getHeight() {
		return height;
	}

	@Override
	public BufferedImage draw() {
		BufferedImage image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawChart(g);

		return image;
	}

	protected abstract void drawChart(final Graphics2D g);
}
