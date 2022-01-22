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
package de.raptusguru.islesofyoreapiwrapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.raptusguru.islesofyoreapiwrapper.RequestManager;
import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;
import de.raptusguru.islesofyoreapiwrapper.model.World;

/**
 * @author Raptusguru
 *
 */
public class WorldController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public WorldController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Get the actual World Data from the Gameserver
	 * 
	 * @return A World Object / null
	 */
	public World getWorld() {
		String path = "/World";
		String world = null;
		if((world = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Saved Player successful received");
			ObjectMapper objectMapper = new ObjectMapper();
			World worldServer = null;

			try {
				JsonNode rootNode = objectMapper.readTree(world);
				worldServer = new World(
						rootNode.get("Time").asDouble(),
						rootNode.get("Weather").asText(),
						rootNode.get("Season").asText(),
						rootNode.get("Day").asInt(),
						rootNode.get("Month").asInt(),
						rootNode.get("Year").asInt(),
						rootNode.get("Lat").asDouble(),
						rootNode.get("Long").asDouble()
						);

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return worldServer;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive saved Player! Request failed.");
			return null;
		}	
	}

}
