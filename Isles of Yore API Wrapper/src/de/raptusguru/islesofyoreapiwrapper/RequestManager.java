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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import de.raptusguru.islesofyoreapiwrapper.exceptions.IncorrectCredentialsException;
import de.raptusguru.islesofyoreapiwrapper.exceptions.RatelimitException;
import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;

/**
 * @author Raptusguru
 *
 */
public class RequestManager {

	private Config config;
	private int ratelimit_counter = 0;
	private long beginOfSecond = 0;
	private String urlConfig;
	private HashMap<String, String> header;
	
	/**
	 * @param config
	 */
	protected RequestManager(Config config) {
		this.config = config;
		
		this.urlConfig = "http://" + this.config.getHost() + ":" + this.config.getPort();
		this.header = new HashMap<String, String>();
		this.header.put("username", this.config.getUsername());
		this.header.put("password", this.config.getPassword());
	}
	
	
	/**
	 * Checks before every request if the Ratelimit is reached
	 * 
	 * @return boolean
	 */
	private boolean isRateLimitReached() {
		try {
			if(this.beginOfSecond+1000 < System.currentTimeMillis()) {
				this.ratelimit_counter = 0;
				this.beginOfSecond = System.currentTimeMillis();
			}
			if(this.ratelimit_counter == this.config.getRatelimit()) {
				throw new RatelimitException("The Ratelimit is reached! Dont make too many requests withing one second or configure the ratelimit in the API Config.");

			}
		} catch (RatelimitException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	/**
	 * @param path
	 * @param body
	 * 
	 * @return boolean
	 */
	public boolean postRequest(String path, String body) {
		if(isRateLimitReached())
			return false;
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.addRequestProperty("username", this.config.getUsername());
			httpURLConnection.addRequestProperty("password", this.config.getPassword());
		    httpURLConnection.setRequestMethod("POST");
		    httpURLConnection.setDoOutput(true);
		    try (OutputStream outputStream = httpURLConnection.getOutputStream()) { 
		    	   outputStream.write(body.getBytes());
		    	   outputStream.flush();
		    	   outputStream.close();
		    	  }
		    	  if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			    	  try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								if(this.config.isPrint_serverResponse())
									Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Response: " + line);
							}
			    	  }
		    	  } else if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED){
		    		  throw new IncorrectCredentialsException("Incorrect user credentials! Please check your Username or Password.");
		    	  }
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			return false;
		}  finally {
		    if (httpURLConnection != null) {
		        httpURLConnection.disconnect();
		    }
		}
		this.ratelimit_counter++;
		return true;
	}
	
	/**
	 * @param path
	 * @return A String with the request response
	 */
	public String getRequest(String path) {
		if(isRateLimitReached())
			return null;
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		HttpURLConnection httpURLConnection = null;
		String response = "";
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.addRequestProperty("username", this.config.getUsername());
			httpURLConnection.addRequestProperty("password", this.config.getPassword());
		    httpURLConnection.setRequestMethod("GET");
			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				try (BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream()))) {
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						if(this.config.isPrint_serverResponse())
							Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Response: " + line);
						response += line;
					}
				}
			} else if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
				throw new IncorrectCredentialsException(
						"Incorrect user credentials! Please check your Username or Password.");
			}
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			return null;
		}  finally {
		    if (httpURLConnection != null) {
		        httpURLConnection.disconnect();
		    }
		}
		this.ratelimit_counter++;
		return response;
	}
	
	/**
	 * @param path
	 * @param body
	 * 
	 * @return boolean
	 */
	public boolean putRequest(String path, String body) {
		if(isRateLimitReached())
			return false;
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.addRequestProperty("username", this.config.getUsername());
			httpURLConnection.addRequestProperty("password", this.config.getPassword());
		    httpURLConnection.setRequestMethod("PUT");
		    httpURLConnection.setDoOutput(true);
		    try (OutputStream outputStream = httpURLConnection.getOutputStream()) { 
		    	   outputStream.write(body.getBytes());
		    	   outputStream.flush();
		    	   outputStream.close();
		    	  }
		    	  if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			    	  try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								if(this.config.isPrint_serverResponse())
									Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Response: " + line);
							}
			    	  }
		    	  } else if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED){
		    		  throw new IncorrectCredentialsException("Incorrect user credentials! Please check your Username or Password.");
		    	  }
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			return false;
		}  finally {
		    if (httpURLConnection != null) {
		        httpURLConnection.disconnect();
		    }
		}
		this.ratelimit_counter++;
		return true;
	}
	
	/**
	 * @param path
	 * @param body
	 * 
	 * @return boolean
	 */
	public boolean deleteRequest(String path, String body) {
		if(isRateLimitReached())
			return false;
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.addRequestProperty("username", this.config.getUsername());
			httpURLConnection.addRequestProperty("password", this.config.getPassword());
		    httpURLConnection.setRequestMethod("DELETE");
		    httpURLConnection.setDoOutput(true);
		    try (OutputStream outputStream = httpURLConnection.getOutputStream()) { 
		    	   outputStream.write(body.getBytes());
		    	   outputStream.flush();
		    	   outputStream.close();
		    	  }
		    	  if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			    	  try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								if(this.config.isPrint_serverResponse())
									Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Response: " + line);
							}
			    	  }
		    	  } else if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED){
		    		  throw new IncorrectCredentialsException("Incorrect user credentials! Please check your Username or Password.");
		    	  }
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			return false;
		}  finally {
		    if (httpURLConnection != null) {
		        httpURLConnection.disconnect();
		    }
		}
		this.ratelimit_counter++;
		return true;
	}

}
