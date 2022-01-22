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
import de.raptusguru.islesofyoreapiwrapper.model.Admin;

/**
 * @author Raptusguru
 *
 */
public class AdminController {

	private RequestManager rm;

	/**
	 * @param rm 
	 */
	public AdminController(RequestManager rm) {
		this.rm = rm;
	}
	
	/**
	 * Get the admins that are entered in the gameservers file
	 * 
	 * @return A List with Admin Objects / null
	 */
	public List<Admin> getAdmins(){
		String path = "/Admins";
		String admins = null;
		if((admins = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Admins successful received");
			
			List<Admin> adminList = new ArrayList<Admin>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(admins);
				rootNode.path("Admins").elements().forEachRemaining(id -> adminList.add(new Admin(id.asText())));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return adminList;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive Admins! Request failed.");
			return null;
		}		
	}
	
	/**
	 * Add an Admin to the Gameservers File
	 * 
	 * @param userID
	 * @return boolean
	 */
	public boolean addAdmin(String userID) {
		String path = "/Admins?userid="+userID;
		String body = "";
		
		if(rm.putRequest(path, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Adding Admin successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant add Admin! Request failed.");
			return false;
		}
	}

	/**
	 * Remove a Admin from the Gameservers File
	 * 
	 * @param userID
	 * @return boolean
	 */
	public boolean removeAdmin(String userID) {
		String path = "/Admins?userid=" + userID;
		String body = "";
		
		if(rm.deleteRequest(path, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Removing Admin successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant remove Admin! Request failed.");
			return false;
		}
		
	}

}
