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

/**
 * @author Raptusguru
 *
 */
public class Ban {

	private String banID;
	private String userID;
	private String lastKnownUsername;
	private String bannedByUserID;
	private String bannedByUserName;
	private String reason;
	private String timeStampUnixUTC;
	private long durationInSeconds;
	
	/**
	 * @param banID
	 * @param userID
	 * @param lastKnownUsername
	 * @param bannedByUserID
	 * @param bannedByUserName
	 * @param reason
	 * @param timeStampUnixUTC
	 * @param durationInSeconds
	 */
	public Ban(String banID, String userID, String lastKnownUsername, String bannedByUserID,
			String bannedByUserName, String reason, String timeStampUnixUTC, long durationInSeconds) {
		this.banID = banID;
		this.userID = userID;
		this.lastKnownUsername = lastKnownUsername;
		this.bannedByUserID = bannedByUserID;
		this.bannedByUserName = bannedByUserName;
		this.reason = reason;
		this.timeStampUnixUTC = timeStampUnixUTC;
		this.durationInSeconds = durationInSeconds;
	}

	/**
	 * @return the banID
	 */
	public String getBanID() {
		return banID;
	}

	/**
	 * @param banID the banID to set
	 */
	public void setBanID(String banID) {
		this.banID = banID;
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
	 * @return the bannedByUserID
	 */
	public String getBannedByUserID() {
		return bannedByUserID;
	}

	/**
	 * @param bannedByUserID the bannedByUserID to set
	 */
	public void setBannedByUserID(String bannedByUserID) {
		this.bannedByUserID = bannedByUserID;
	}

	/**
	 * @return the bannedByUserName
	 */
	public String getBannedByUserName() {
		return bannedByUserName;
	}

	/**
	 * @param bannedByUserName the bannedByUserName to set
	 */
	public void setBannedByUserName(String bannedByUserName) {
		this.bannedByUserName = bannedByUserName;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the timeStampUnixUTC
	 */
	public String getTimeStampUnixUTC() {
		return timeStampUnixUTC;
	}

	/**
	 * @param timeStampUnixUTC the timeStampUnixUTC to set
	 */
	public void setTimeStampUnixUTC(String timeStampUnixUTC) {
		this.timeStampUnixUTC = timeStampUnixUTC;
	}


	/**
	 * @return the durationInSeconds
	 */
	public long getDurationInSeconds() {
		return durationInSeconds;
	}

	
	/**
	 * @param durationInSeconds the durationInSeconds to set
	 */
	public void setDurationInSeconds(long durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
	
		
	
}
