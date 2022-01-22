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
public class CommandController {

	private RequestManager rm;

	/**
	 * @param rm
	 */
	public CommandController(RequestManager rm) {
		this.rm = rm;
	}
	
	/**
	 * Send a command like ingame console to the gameserver
	 * 
	 * @param command
	 * @param args
	 * @return boolean
	 */
	public boolean sendCommand(String command, String[] args) {
		String path = "/Commands";
		//\""+args+"\"
		String jsonArgs = "";
		for(int i = 0; i < args.length; i++) {
			if(i < args.length-1)
				jsonArgs += "\"" + args[i] + "\",";
			else
				jsonArgs += "\"" + args[i] + "\"";
		}
		
		String json = ""
	    		+ "{"
	    		+ "\"Command\" : \""+command+"\","
	    		+ "\"Arguments\" : ["+jsonArgs+"]"
	    		+ "}";
		
		if(rm.postRequest(path, json)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Posting command successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant post command! Request failed.");
			return false;
		}
	}

}
