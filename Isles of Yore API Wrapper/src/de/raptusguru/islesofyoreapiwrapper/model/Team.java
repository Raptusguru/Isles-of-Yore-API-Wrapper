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
public class Team {

	private boolean isActiveTeam;
	private String uniqueID;
	private String teamLeaderUserID;
	private List<TeamMember> teamMember;
	private List<String> allowedMemberSpecies;
	private int maxMembers;
	/**
	 * @param isActiveTeam
	 * @param uniqueID
	 * @param teamLeaderUserID
	 * @param teamMember
	 * @param allowedMemberSpecies
	 * @param maxMembers
	 */
	public Team(boolean isActiveTeam, String uniqueID, String teamLeaderUserID, List<TeamMember> teamMember,
			List<String> allowedMemberSpecies, int maxMembers) {
		super();
		this.isActiveTeam = isActiveTeam;
		this.uniqueID = uniqueID;
		this.teamLeaderUserID = teamLeaderUserID;
		this.teamMember = teamMember;
		this.allowedMemberSpecies = allowedMemberSpecies;
		this.maxMembers = maxMembers;
	}
	/**
	 * @return the isActiveTeam
	 */
	public boolean isActiveTeam() {
		return isActiveTeam;
	}
	/**
	 * @param isActiveTeam the isActiveTeam to set
	 */
	public void setActiveTeam(boolean isActiveTeam) {
		this.isActiveTeam = isActiveTeam;
	}
	/**
	 * @return the uniqueID
	 */
	public String getUniqueID() {
		return uniqueID;
	}
	/**
	 * @param uniqueID the uniqueID to set
	 */
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	/**
	 * @return the teamLeaderUserID
	 */
	public String getTeamLeaderUserID() {
		return teamLeaderUserID;
	}
	/**
	 * @param teamLeaderUserID the teamLeaderUserID to set
	 */
	public void setTeamLeaderUserID(String teamLeaderUserID) {
		this.teamLeaderUserID = teamLeaderUserID;
	}
	/**
	 * @return the teamMember
	 */
	public List<TeamMember> getTeamMember() {
		return teamMember;
	}
	/**
	 * @param teamMember the teamMember to set
	 */
	public void setTeamMember(List<TeamMember> teamMember) {
		this.teamMember = teamMember;
	}
	/**
	 * @return the allowedMemberSpecies
	 */
	public List<String> getAllowedMemberSpecies() {
		return allowedMemberSpecies;
	}
	/**
	 * @param allowedMemberSpecies the allowedMemberSpecies to set
	 */
	public void setAllowedMemberSpecies(List<String> allowedMemberSpecies) {
		this.allowedMemberSpecies = allowedMemberSpecies;
	}
	/**
	 * @return the maxMembers
	 */
	public int getMaxMembers() {
		return maxMembers;
	}
	/**
	 * @param maxMembers the maxMembers to set
	 */
	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}
	
	
}
