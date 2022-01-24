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
import de.raptusguru.islesofyoreapiwrapper.model.Team;
import de.raptusguru.islesofyoreapiwrapper.model.TeamMember;

/**
 * @author Raptusguru
 *
 */
public class TeamsController {

	private RequestManager rm;

	/**
	 * 
	 */
	public TeamsController(RequestManager rm) {
		this.rm = rm;
	}
	
	
	/**
	 * Get the current Teams from the Server
	 * 
	 * @return List<Team> / null
	 */
	public List<Team> getActiveTeams(){
		String path = "/Teams";
		String teams = null;
		if((teams = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Teams successful received");
			
			List<Team> teamList = new ArrayList<Team>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(teams);
				rootNode.path("Teams").elements().forEachRemaining(
						team -> {
							//Get the TeamMembers
							List<TeamMember> teamMembers = new ArrayList<TeamMember>();
							team.get("TeamMembers").forEach(userID -> teamMembers.add(
									new TeamMember(userID.get("UniqueNetIdStr").asText())
									));
							
							//Get the Allowed Member Species
							List<String> allowedMemberSpecies = new ArrayList<String>();
							team.get("AllowedMemberSpecies").forEach(species -> allowedMemberSpecies.add(
									species.asText()
									));
							
							teamList.add(
									new Team(
											team.get("IsActiveTeam").asBoolean(),
											team.get("UniqueId").asText(),
											team.get("TeamLeadUniqueNetId").asText(),
											teamMembers,
											allowedMemberSpecies,
											team.get("MaxMembers").asInt()
											)
									);
						}
								
				);
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return teamList;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive Teams! Request failed.");
			return null;
		}		
	}
}
