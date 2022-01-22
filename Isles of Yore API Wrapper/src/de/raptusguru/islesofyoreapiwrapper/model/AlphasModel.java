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
public class AlphasModel {
	private String slot;
	private String id;
	private ColorModel color;
	
	/**
	 * @param slot
	 * @param id
	 * @param color
	 */
	public AlphasModel(String slot, String id, ColorModel color) {
		super();
		this.slot = slot;
		this.id = id;
		this.color = color;
	}

	/**
	 * @return the slot
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the color
	 */
	public ColorModel getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(ColorModel color) {
		this.color = color;
	}
	
}
