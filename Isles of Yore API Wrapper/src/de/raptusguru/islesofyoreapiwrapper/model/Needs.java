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
public class Needs {
	private double eat;
	private double drink;
	private double oxygen;
	private double rest;
	/**
	 * @param eat
	 * @param drink
	 * @param oxygen
	 * @param rest
	 */
	public Needs(double eat, double drink, double oxygen, double rest) {
		super();
		this.eat = eat;
		this.drink = drink;
		this.oxygen = oxygen;
		this.rest = rest;
	}
	/**
	 * @return the eat
	 */
	public double getEat() {
		return eat;
	}
	/**
	 * @param eat the eat to set
	 */
	public void setEat(double eat) {
		this.eat = eat;
	}
	/**
	 * @return the drink
	 */
	public double getDrink() {
		return drink;
	}
	/**
	 * @param drink the drink to set
	 */
	public void setDrink(double drink) {
		this.drink = drink;
	}
	/**
	 * @return the oxygen
	 */
	public double getOxygen() {
		return oxygen;
	}
	/**
	 * @param oxygen the oxygen to set
	 */
	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}
	/**
	 * @return the rest
	 */
	public double getRest() {
		return rest;
	}
	/**
	 * @param rest the rest to set
	 */
	public void setRest(double rest) {
		this.rest = rest;
	}
	
	
}
