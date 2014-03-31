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
