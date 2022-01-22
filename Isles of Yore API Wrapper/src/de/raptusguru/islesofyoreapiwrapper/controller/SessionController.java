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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.raptusguru.islesofyoreapiwrapper.RequestManager;
import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;

/**
 * @author Raptusguru
 *
 */
public class SessionController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public SessionController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Get the Server Start Up Time in UnixUTC Format
	 * 
	 * @return String / null
	 */
	public String getServerUptime() {
		String path = "/Session";
		String session = null;
		if((session = rm.getRequest(path)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Server Uptime successful received");
			ObjectMapper objectMapper = new ObjectMapper();
			String serverUptime = null;

			try {
				JsonNode rootNode = objectMapper.readTree(session);
				serverUptime = rootNode.get("SessionStartUnixUTCTimestamp").asText();				
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return serverUptime;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive Server Uptime! Request failed.");
			return null;
		}	
	}

}
