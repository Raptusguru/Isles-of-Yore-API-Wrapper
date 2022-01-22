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
public class Pawn {
	private String species;
	private PersistedSkin persistedSkin;
	private String ownerUserID;
	private Location location;
	private double growth;
	private double health;
	private double stamina;
	private double thirst;
	private double hunger;
	private double oxygen;
	/**
	 * @param species
	 * @param persistedSkin
	 * @param ownerUserID
	 * @param location
	 * @param growth
	 * @param health
	 * @param stamina
	 * @param thirst
	 * @param hunger
	 * @param oxygen
	 */
	public Pawn(String species, PersistedSkin persistedSkin, String ownerUserID, Location location,
			double growth, double health, double stamina, double thirst, double hunger, double oxygen) {
		super();
		this.species = species;
		this.persistedSkin = persistedSkin;
		this.ownerUserID = ownerUserID;
		this.location = location;
		this.growth = growth;
		this.health = health;
		this.stamina = stamina;
		this.thirst = thirst;
		this.hunger = hunger;
		this.oxygen = oxygen;
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
	 * @return the persistedSkin
	 */
	public PersistedSkin getPersistedSkin() {
		return persistedSkin;
	}
	/**
	 * @param persistedSkin the persistedSkin to set
	 */
	public void setPersistedSkin(PersistedSkin persistedSkin) {
		this.persistedSkin = persistedSkin;
	}
	/**
	 * @return the ownerUserID
	 */
	public String getOwnerUserID() {
		return ownerUserID;
	}
	/**
	 * @param ownerUserID the ownerUserID to set
	 */
	public void setOwnerUserID(String ownerUserID) {
		this.ownerUserID = ownerUserID;
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
	 * @return the stamina
	 */
	public double getStamina() {
		return stamina;
	}
	/**
	 * @param stamina the stamina to set
	 */
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	/**
	 * @return the thirst
	 */
	public double getThirst() {
		return thirst;
	}
	/**
	 * @param thirst the thirst to set
	 */
	public void setThirst(double thirst) {
		this.thirst = thirst;
	}
	/**
	 * @return the hunger
	 */
	public double getHunger() {
		return hunger;
	}
	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(double hunger) {
		this.hunger = hunger;
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
	
	
	
	
}
