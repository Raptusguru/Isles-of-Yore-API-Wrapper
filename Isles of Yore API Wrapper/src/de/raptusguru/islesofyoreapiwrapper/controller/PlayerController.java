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
import de.raptusguru.islesofyoreapiwrapper.model.Alpha;
import de.raptusguru.islesofyoreapiwrapper.model.BaseColour;
import de.raptusguru.islesofyoreapiwrapper.model.Color;
import de.raptusguru.islesofyoreapiwrapper.model.CustomNameColour;
import de.raptusguru.islesofyoreapiwrapper.model.Location;
import de.raptusguru.islesofyoreapiwrapper.model.Pawn;
import de.raptusguru.islesofyoreapiwrapper.model.PersistedSkin;
import de.raptusguru.islesofyoreapiwrapper.model.Player;

/**
 * @author Raptusguru
 *
 */
public class PlayerController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public PlayerController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Get a specific Player from the Gameservers saved players
	 * 
	 * @param userID
	 * @return A Player Object / null
	 */
	public Player getPlayer(String userID) {
		String path = "/Players?userid="+userID;
		
		String player = null;
		if((player = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Saved Player successful received");
			ObjectMapper objectMapper = new ObjectMapper();
			Player savedPlayer = null;
			
			
			try {
				JsonNode rootNode = objectMapper.readTree(player);
				
				//AllKnownUsernames
				List<String> allKnownUsernames = new ArrayList<String>();
				rootNode.get("Player").get("AllKnownUsernames").forEach(username -> allKnownUsernames.add(username.asText()));
				
				//LogonTimestamps
				List<String> logonTimestamps = new ArrayList<String>();
				rootNode.get("Player").get("LogonTimestamps").forEach(timestamp -> logonTimestamps.add(timestamp.asText()));
				
				//Pawn->PersistedSkin->Alphas
				List<Alpha> alphas = new ArrayList<Alpha>();
				rootNode.get("Pawn").get("PersistedSkin").get("Alphas").forEach(
						elem -> alphas.add(new Alpha(
											elem.get("Slot").asText(),
											elem.get("ID").asText(),
											new Color(
													elem.get("Color").get("R").asDouble(),
													elem.get("Color").get("R").asDouble(),
													elem.get("Color").get("R").asDouble(),
													elem.get("Color").get("R").asInt()
													)
											))
						);
				
				savedPlayer = new Player(
						rootNode.get("Player").get("NetId").asText(),
						rootNode.get("Player").get("LastKnownUsername").asText(),
						allKnownUsernames,
						logonTimestamps,
						rootNode.get("Player").get("UseCustomNameColour").asBoolean(),
						new CustomNameColour(
							rootNode.get("Player").get("CustomNameColour").get("R").asInt(),
							rootNode.get("Player").get("CustomNameColour").get("G").asInt(),
							rootNode.get("Player").get("CustomNameColour").get("B").asInt(),
							rootNode.get("Player").get("CustomNameColour").get("A").asDouble()
						),
						new Pawn(
							rootNode.get("Pawn").get("Species").asText(),
							new PersistedSkin(
								rootNode.get("Pawn").get("PersistedSkin").get("BasePresetId").asText(),
								new BaseColour(
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("R").asDouble(),
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("G").asDouble(),
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("B").asDouble(),
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("A").asInt()
								),
								alphas								
								),
							rootNode.get("Pawn").get("OwnerNetId").asText(),
							new Location(
								rootNode.get("Pawn").get("Location").get("X").asDouble(),
								rootNode.get("Pawn").get("Location").get("Y").asDouble(),
								rootNode.get("Pawn").get("Location").get("Z").asDouble()
								),
							rootNode.get("Pawn").get("Growth").asDouble(),
							rootNode.get("Pawn").get("Health").asDouble(),
							rootNode.get("Pawn").get("Stamina").asDouble(),
							rootNode.get("Pawn").get("Thirst").asDouble(),
							rootNode.get("Pawn").get("Hunger").asDouble(),
							rootNode.get("Pawn").get("Oxygen").asDouble()
						),
						rootNode.get("HasPersistedPawn").asBoolean()
						);
				
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return savedPlayer;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive saved Player! Request failed.");
			return null;
		}	
	}

}
