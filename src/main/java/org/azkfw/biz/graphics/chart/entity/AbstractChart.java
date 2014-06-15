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

import org.azkfw.biz.graphics.entity.Margin;

/**
 * このクラスは、チャート情報を格納した基底クラスです。
 * 
 * @since 1.1.0
 * @version 1.1.0 2014/06/13
 * @author Kawakicchi
 */
public abstract class AbstractChart {

	/** マージン情報 */
	private Margin margin;

	/** 背景色 */
	private Color backgroundColor;

	/**
	 * コンストラクタ
	 */
	public AbstractChart() {
		margin = null;
		backgroundColor = null;
	}

	/**
	 * マージン情報を設定する。
	 * 
	 * @param aMargin マージン情報
	 */
	public final void setMargin(final Margin aMargin) {
		margin = aMargin;
	}

	/**
	 * マージン情報を取得する。
	 * 
	 * @return マージン情報
	 */
	public final Margin getMargin() {
		return margin;
	}

	/**
	 * 背景色を設定する。
	 * 
	 * @param aColor 背景色
	 */
	public final void setBackgroundColor(final Color aColor) {
		backgroundColor = aColor;
	}

	/**
	 * 背景色を取得する。
	 * 
	 * @return 背景色
	 */
	public final Color getBackgroundColor() {
		return backgroundColor;
	}
}
