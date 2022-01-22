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
public class API {

	private Config config;
	private RequestManager requestManager;
	
	/**
	 * 
	 */
	public API() {
		this.config = new Config();
	}
	
	/**
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 */
	public API(String host, String port, String username, String password) {
		this.config = new Config(host, port, username, password);
	}
	
	/**
	 * @return
	 */
	public Config config() {
		return this.config;
	}

	/**
	 * @return
	 */
	public RequestManager build() {
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Building API..");
		this.config.validate();
		this.requestManager = new RequestManager(this.config);
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "API Ready");
		return this.requestManager;
	}
}
