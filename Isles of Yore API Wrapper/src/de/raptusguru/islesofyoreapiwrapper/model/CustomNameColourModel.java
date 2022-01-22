/*
 * Copyright 2022 Patrick Jack Schreyl (Raptusguru)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.raptusguru.islesofyoreapiwrapper.model;

/**
 * @author Raptusguru
 *
 */
public class CustomNameColourModel {

	private int r;
	private int g;
	private int b;
	private double a;
	/**
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public CustomNameColourModel(int r, int g, int b, double a) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	/**
	 * @return the r
	 */
	public int getR() {
		return r;
	}
	/**
	 * @param r the r to set
	 */
	public void setR(int r) {
		this.r = r;
	}
	/**
	 * @return the g
	 */
	public int getG() {
		return g;
	}
	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		this.g = g;
	}
	/**
	 * @return the b
	 */
	public int getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(int b) {
		this.b = b;
	}
	/**
	 * @return the a
	 */
	public double getA() {
		return a;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(double a) {
		this.a = a;
	}
	
	
}
