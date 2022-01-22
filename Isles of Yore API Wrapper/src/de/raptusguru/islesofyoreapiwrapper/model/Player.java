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
public class Player {
	private String userID;
	private String lastKnownUsername;
	private List<String> allKnownUsernames;
	private List<String> logonTimestamps;
	private boolean useCustomColour;
	private CustomNameColour customNameColour;
	private Pawn pawn;
	private boolean hasPersitedPawn;
	
	/**
	 * @param userID
	 * @param lastKnownUsername
	 * @param allKnownUsernames
	 * @param logonTimestamps
	 * @param useCustomColour
	 * @param customNameColour
	 * @param pawn
	 * @param hasPersitedPawn
	 */
	public Player(String userID, String lastKnownUsername, List<String> allKnownUsernames,
			List<String> logonTimestamps, boolean useCustomColour, CustomNameColour customNameColour,
			Pawn pawn, boolean hasPersitedPawn) {
		this.userID = userID;
		this.lastKnownUsername = lastKnownUsername;
		this.allKnownUsernames = allKnownUsernames;
		this.logonTimestamps = logonTimestamps;
		this.useCustomColour = useCustomColour;
		this.customNameColour = customNameColour;
		this.pawn = pawn;
		this.hasPersitedPawn = hasPersitedPawn;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the lastKnownUsername
	 */
	public String getLastKnownUsername() {
		return lastKnownUsername;
	}

	/**
	 * @param lastKnownUsername the lastKnownUsername to set
	 */
	public void setLastKnownUsername(String lastKnownUsername) {
		this.lastKnownUsername = lastKnownUsername;
	}

	/**
	 * @return the allKnownUsernames
	 */
	public List<String> getAllKnownUsernames() {
		return allKnownUsernames;
	}

	/**
	 * @param allKnownUsernames the allKnownUsernames to set
	 */
	public void setAllKnownUsernames(List<String> allKnownUsernames) {
		this.allKnownUsernames = allKnownUsernames;
	}

	/**
	 * @return the logonTimestamps
	 */
	public List<String> getLogonTimestamps() {
		return logonTimestamps;
	}

	/**
	 * @param logonTimestamps the logonTimestamps to set
	 */
	public void setLogonTimestamps(List<String> logonTimestamps) {
		this.logonTimestamps = logonTimestamps;
	}

	/**
	 * @return the useCustomColour
	 */
	public boolean isUseCustomColour() {
		return useCustomColour;
	}

	/**
	 * @param useCustomColour the useCustomColour to set
	 */
	public void setUseCustomColour(boolean useCustomColour) {
		this.useCustomColour = useCustomColour;
	}

	/**
	 * @return the customNameColour
	 */
	public CustomNameColour getCustomNameColour() {
		return customNameColour;
	}

	/**
	 * @param customNameColour the customNameColour to set
	 */
	public void setCustomNameColour(CustomNameColour customNameColour) {
		this.customNameColour = customNameColour;
	}

	/**
	 * @return the pawn
	 */
	public Pawn getPawn() {
		return pawn;
	}

	/**
	 * @param pawn the pawn to set
	 */
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}

	/**
	 * @return the hasPersitedPawn
	 */
	public boolean isHasPersitedPawn() {
		return hasPersitedPawn;
	}

	/**
	 * @param hasPersitedPawn the hasPersitedPawn to set
	 */
	public void setHasPersitedPawn(boolean hasPersitedPawn) {
		this.hasPersitedPawn = hasPersitedPawn;
	}
	
	
}
