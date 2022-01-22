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
import de.raptusguru.islesofyoreapiwrapper.model.Location;
import de.raptusguru.islesofyoreapiwrapper.model.Needs;
import de.raptusguru.islesofyoreapiwrapper.model.OnlinePlayer;

/**
 * @author Raptusguru
 *
 */
public class OnlinePlayersController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public OnlinePlayersController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Get the current online players from the Gameserver
	 * 
	 * @return A List with OnlinePlayer Object / null
	 */
	public List<OnlinePlayer> getCurrentOnlinePlayers() {
		String path = "/Players";
		String players = null;
		if((players = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Online Players successful received");
			
			List<OnlinePlayer> onlinePlayers = new ArrayList<OnlinePlayer>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(players);
				rootNode.path("Players").elements().forEachRemaining(
						elem -> onlinePlayers.add(
								new OnlinePlayer(
										elem.get("UserName").asText(),
										elem.get("UserID").asText(),
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
										),
										elem.get("LoginUnixUTCTimestamp").asText()
										)));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return onlinePlayers;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive online Players! Request failed.");
			return null;
		}	
	}

}
