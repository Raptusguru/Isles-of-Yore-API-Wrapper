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
import de.raptusguru.islesofyoreapiwrapper.model.Ban;

/**
 * @author Raptusguru
 *
 */
public class BanController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public BanController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Add a Ban to the Gameservers File
	 * 
	 * @param userID
	 * @param bannedByUserID
	 * @param banReason
	 * @return boolean
	 */
	public boolean addBan(String userID, String bannedByUserID, String banReason) {
		String path = "/Bans";
		String json = ""
	    		+ "{"
	    		+ "\"UserID\" : \""+userID+"\","
	    		+ "\"BannedByUserID\" : \""+bannedByUserID+"\","
	    		+ "\"BanReason\" : \""+banReason+"\""
	    		+ "}";
		
		if(rm.putRequest(path, json)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Adding Ban successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant add Ban! Request failed.");
			return false;
		}
		
	}

	/**
	 * Remove a Ban from the Gameservers File
	 * 
	 * @param userID
	 * @return boolean
	 */
	public boolean removeBan(String userID) {
		String path = "/Bans";
		String json = ""
	    		+ "{"
	    		+ "\"UserID\" : \""+userID+"\""
	    		+ "}";
		
		if(rm.deleteRequest(path, json)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Removing Ban successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant remove Ban! Request failed.");
			return false;
		}
		
	}

	/**
	 * Get all Bans from the Gameservers File
	 * 
	 * @return A List with Ban Objects / null
	 */
	public List<Ban> getBans() {
		String path = "/Bans";
		String bans = null;
		if((bans = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Bans successful received");
			
			List<Ban> banList = new ArrayList<Ban>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(bans);
				rootNode.path("Bans").elements().forEachRemaining(
						elem -> banList.add(
								new Ban(
										elem.get("BanID").asText(),
										elem.get("UserID").asText(),
										elem.get("LastKnownUsername").asText(),
										elem.get("BannedByUserID").asText(),
										elem.get("BannedByUserName").asText(),
										elem.get("Reason").asText(),
										elem.get("TimestampUnixUTC").asText(),
										elem.get("DurationInSeconds").asLong()
										)));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return banList;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive Bans! Request failed.");
			return null;
		}	
	}

}
