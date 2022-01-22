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

import de.raptusguru.islesofyoreapiwrapper.exceptions.ConfigException;

/**
 * @author Raptusguru
 *
 */
public class Config {

	private String host;
	private String port;
	private String username;
	private String password;
	
	//Ratelimit per Second
	private int ratelimit = 5;
	
	//Print Raw Server Response
	private boolean print_serverResponse = false;

	/**
	 * 
	 */
	protected Config() {
		
	}

	/**
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 */
	protected Config(String host, String port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public void validate() {
		try {
			if(host == null || host.isEmpty())
				throw new ConfigException("Host is required and can not be null or empty!");
			if(port == null || port.isEmpty())
				throw new ConfigException("Port is required and can not be null or empty!");
			if(username == null || username.isEmpty())
				throw new ConfigException("Username is required and can not be null or empty!");
			if(password == null)
				throw new ConfigException("Port is required and can not be null!");
		} catch (ConfigException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the ratelimit
	 */
	public int getRatelimit() {
		return ratelimit;
	}

	/**
	 * @param ratelimit the ratelimit to set
	 */
	public void setRatelimit(int ratelimit) {
		this.ratelimit = ratelimit;
	}

	/**
	 * @return the print_serverResponse
	 */
	public boolean isPrint_serverResponse() {
		return print_serverResponse;
	}

	/**
	 * @param print_serverResponse the print_serverResponse to set
	 */
	public void setPrint_serverResponse(boolean print_serverResponse) {
		this.print_serverResponse = print_serverResponse;
	}

	

	
}
