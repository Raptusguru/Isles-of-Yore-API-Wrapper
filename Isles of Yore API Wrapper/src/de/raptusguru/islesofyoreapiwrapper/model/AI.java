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
public class AI {
	
	private boolean isActive;
	private String species;
	private Needs needs;
	private double growth;
	private String growthStage;
	private double health;
	private boolean isAlive;
	private Location location;
	
	/**
	 * @param isActive
	 * @param species
	 * @param needs
	 * @param growth
	 * @param growthStage
	 * @param health
	 * @param isAlive
	 * @param location
	 */
	public AI(boolean isActive, String species, Needs needs, double growth, String growthStage, double health,
			boolean isAlive, Location location) {
		this.isActive = isActive;
		this.species = species;
		this.needs = needs;
		this.growth = growth;
		this.growthStage = growthStage;
		this.health = health;
		this.isAlive = isAlive;
		this.location = location;
	}
	
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the species
	 */
	public String getSpecies() {
		return species;
	}
	/**
	 * @param species the species to set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}
	/**
	 * @return the needs
	 */
	public Needs getNeeds() {
		return needs;
	}
	/**
	 * @param needs the needs to set
	 */
	public void setNeeds(Needs needs) {
		this.needs = needs;
	}
	/**
	 * @return the growth
	 */
	public double getGrowth() {
		return growth;
	}
	/**
	 * @param growth the growth to set
	 */
	public void setGrowth(double growth) {
		this.growth = growth;
	}
	/**
	 * @return the growthStage
	 */
	public String getGrowthStage() {
		return growthStage;
	}
	/**
	 * @param growthStage the growthStage to set
	 */
	public void setGrowthStage(String growthStage) {
		this.growthStage = growthStage;
	}
	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * @param health the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
