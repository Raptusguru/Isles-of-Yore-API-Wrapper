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

import de.raptusguru.islesofyoreapiwrapper.RequestManager;
import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;

/**
 * @author Raptusguru
 *
 */
public class AnnouncementController {

	private RequestManager rm;

	/**
	 * @param rm 
	 */
	public AnnouncementController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Send an Announcement to the Gameserver
	 * 
	 * @param message
	 * @return boolean
	 */
	public boolean sendAnnouncement(String message) {
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try sending Announcement..");
		String path = "/Announce";
		String json = ""
				+ "{"
				+ "\"message\" : \"" + message + "\""
				+ "}";

		if (rm.postRequest(path, json)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Announcement successful sended");
			return true;
		} else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR,
					"Cant send Announcement! Sending Announcement failed.");
			return false;
		}
	}

	
	
}
