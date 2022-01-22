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

import java.util.List;

/**
 * @author Raptusguru
 *
 */
public class PersistedSkin {
	private String basePresetID;
	private BaseColour baseColour;
	private List<Alpha> alphas;
	
	/**
	 * @param basePresetID
	 * @param baseColour
	 * @param alphas
	 */
	public PersistedSkin(String basePresetID, BaseColour baseColour, List<Alpha> alphas) {
		this.basePresetID = basePresetID;
		this.baseColour = baseColour;
		this.alphas = alphas;
	}

	/**
	 * @return the basePresetID
	 */
	public String getBasePresetID() {
		return basePresetID;
	}

	/**
	 * @param basePresetID the basePresetID to set
	 */
	public void setBasePresetID(String basePresetID) {
		this.basePresetID = basePresetID;
	}

	/**
	 * @return the baseColour
	 */
	public BaseColour getBaseColour() {
		return baseColour;
	}

	/**
	 * @param baseColour the baseColour to set
	 */
	public void setBaseColour(BaseColour baseColour) {
		this.baseColour = baseColour;
	}

	/**
	 * @return the alphas
	 */
	public List<Alpha> getAlphas() {
		return alphas;
	}

	/**
	 * @param alphas the alphas to set
	 */
	public void setAlphas(List<Alpha> alphas) {
		this.alphas = alphas;
	}
	
	
}
