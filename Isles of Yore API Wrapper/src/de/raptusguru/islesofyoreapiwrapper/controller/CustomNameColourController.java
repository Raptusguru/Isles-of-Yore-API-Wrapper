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
import de.raptusguru.islesofyoreapiwrapper.model.CustomNameColour;

/**
 * @author Raptusguru
 *
 */
public class CustomNameColourController {

	private RequestManager rm;

	public CustomNameColourController(RequestManager rm) {
		this.rm = rm;
	}

	/**
	 * Set the Custom Name Color for a specific Player <br>
	 * Activate or Deactivate the use of Custom Name Color for a specific player <i>(useCustomNameColour = true/false)</i>
	 * 
	 * @param userID
	 * @param useCustomNameColor
	 * @param customNameColorModel
	 * @return boolean
	 */
	public boolean setCustomNameColourForPlayer(String userID, boolean useCustomNameColor, CustomNameColour customNameColorModel) {
		String path = "/NameColour";
		String json = ""
	    		+ "{"
	    		+ "\"UserID\" : \""+userID+"\","
	    		+ "\"UseCustomColour\" : \""+useCustomNameColor+"\","
	    		+ "\"Colour\" : {"
		    				+ "\"R\" : \""+customNameColorModel.getR()+"\","
		    				+ "\"G\" : \""+customNameColorModel.getG()+"\","
		    				+ "\"B\" : \""+customNameColorModel.getB()+"\","
		    				+ "\"A\" : \""+customNameColorModel.getA()+"\""
	    					+ "}"
	    		+ "}";
		
		if(rm.postRequest(path, json)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Posting Custom Name Colour change successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant post Custom Name Colour change! Posting Name Colour change failed.");
			return false;
		}
	}
}
