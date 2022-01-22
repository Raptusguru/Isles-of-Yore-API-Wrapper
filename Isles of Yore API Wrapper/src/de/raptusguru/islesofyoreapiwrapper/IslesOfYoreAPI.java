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
package de.raptusguru.islesofyoreapiwrapper;

import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;

/**
 * @author Raptusguru
 *
 */
public class IslesOfYoreAPI {
	private Config config;

	/**
	 * 
	 */
	public IslesOfYoreAPI() {
		this.config = new Config();
	}
	
	/**
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 */
	public IslesOfYoreAPI(String host, String port, String username, String password) {
		this.config = new Config(host, port, username, password);
	}
	
	/**
	 * @return The API Controller Object
	 */
	public APIController build() {
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Building API..");
		this.config.validate();
		RequestManager rm = new RequestManager(this.config);
		APIController apiController = new APIController(rm);
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "API Ready");
		return apiController;
	}
	
	/**
	 * @return The API Configuration Object
	 */
	public Config config() {
		return this.config;
	}
	
}
