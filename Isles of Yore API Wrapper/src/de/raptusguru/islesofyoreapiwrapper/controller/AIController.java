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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.raptusguru.islesofyoreapiwrapper.RequestManager;
import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;
import de.raptusguru.islesofyoreapiwrapper.model.AI;
import de.raptusguru.islesofyoreapiwrapper.model.Location;
import de.raptusguru.islesofyoreapiwrapper.model.Needs;

/**
 * @author Raptusguru
 *
 */
public class AIController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public AIController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Get the currently spawned AI from the Game
	 * 
	 * @return A List with AI Objects / null
	 */
	public List<AI> getCurrentSpawnedAIs() {
		String path = "/AI";
		
		String ais = null;
		if((ais = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "AI successful received");
			
			List<AI> aisServer = new ArrayList<AI>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(ais);
				rootNode.path("AI").elements().forEachRemaining(
						elem -> aisServer.add(
								new AI(
										elem.get("IsActive").asBoolean(),
										elem.get("Species").asText(),
										new Needs(
												elem.get("Needs").get("Eat").asDouble(),
												elem.get("Needs").get("Drink").asDouble(),
												elem.get("Needs").get("Oxygen").asDouble(),
												elem.get("Needs").get("Rest").asDouble()
												),
										elem.get("Growth").asDouble(),
										elem.get("GrowthStage").asText(),
										elem.get("Health").asDouble(),
										elem.get("IsAlive").asBoolean(),
										new Location(
												elem.get("Location").get("X").asDouble(),
												elem.get("Location").get("Y").asDouble(),
												elem.get("Location").get("Z").asDouble()
												)
										)
								));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return aisServer;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive AI! Request failed.");
			return null;
		}	
	}

}
