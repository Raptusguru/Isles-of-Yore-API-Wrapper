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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.raptusguru.islesofyoreapiwrapper.exceptions.IncorrectCredentialsException;
import de.raptusguru.islesofyoreapiwrapper.exceptions.RatelimitException;
import de.raptusguru.islesofyoreapiwrapper.logger.LOGLEVEL;
import de.raptusguru.islesofyoreapiwrapper.logger.Logger;
import de.raptusguru.islesofyoreapiwrapper.model.AIModel;
import de.raptusguru.islesofyoreapiwrapper.model.AdminModel;
import de.raptusguru.islesofyoreapiwrapper.model.AlphasModel;
import de.raptusguru.islesofyoreapiwrapper.model.BanModel;
import de.raptusguru.islesofyoreapiwrapper.model.BaseColourModel;
import de.raptusguru.islesofyoreapiwrapper.model.ColorModel;
import de.raptusguru.islesofyoreapiwrapper.model.CustomNameColourModel;
import de.raptusguru.islesofyoreapiwrapper.model.LocationModel;
import de.raptusguru.islesofyoreapiwrapper.model.NeedsModel;
import de.raptusguru.islesofyoreapiwrapper.model.PawnModel;
import de.raptusguru.islesofyoreapiwrapper.model.PersistedSkinModel;
import de.raptusguru.islesofyoreapiwrapper.model.PlayerModel;
import de.raptusguru.islesofyoreapiwrapper.model.PlayersModel;
import de.raptusguru.islesofyoreapiwrapper.model.WorldModel;

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
	 * @return
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
	 * @param url
	 * @param body
	 */
	private boolean postRequest(URL url, String body) {
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
	 * @param url
	 * @return
	 */
	private String getRequest(URL url) {
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
	 * @param url
	 * @param body
	 */
	private boolean putRequest(URL url, String body) {
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
	 * @param url
	 * @param body
	 */
	private boolean deleteRequest(URL url, String body) {
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

	
	/**
	 * Send an Announcement to the Server
	 * 
	 * @param msg
	 * The Message you want to send as Announcement
	 * @return boolean
	 */
	public boolean announce(String msg) {
		if(isRateLimitReached())
			return false;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try sending Announcement..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Announce");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String body = ""
	    		+ "{"
	    		+ "\"message\" : \""+msg+"\""
	    		+ "}";
		
		if(postRequest(url, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Announcement successful sended");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant send Announcement! Sending Announcement failed.");
			return false;
		}
	}


	
	/**
	 * Get the Admins (IDs)
	 * 
	 * @return List<AdminModel>/null
	 */
	public List<AdminModel> getAdmins() {
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive Admins..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Admins");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String admins = null;
		if((admins = getRequest(url)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Admins successful received");
			
			List<AdminModel> adminList = new ArrayList<AdminModel>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(admins);
				rootNode.path("Admins").elements().forEachRemaining(id -> adminList.add(new AdminModel(id.asText())));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return adminList;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive Admins! Request failed.");
			return null;
		}		
	}
	
	/**
	 * Add an Admin
	 * 
	 * @param string
	 * @return boolean
	 */
	public boolean addAdmin(String userID) {
		if(isRateLimitReached())
			return false;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try adding Admin..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Admins?userid="+userID);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String body = "";
		
		if(putRequest(url, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Adding Admin successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant add Admin! Request failed.");
			return false;
		}
	}
	
	/**
	 * Remove an Admin
	 * 
	 * @param string
	 * @return boolean
	 */
	public boolean removeAdmin(String userID) {
		if(isRateLimitReached())
			return false;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try removing Admin..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Admins?userid="+userID);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String body = "";
		
		if(deleteRequest(url, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Removing Admin successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant remove Admin! Request failed.");
			return false;
		}
	}

	/**
	 * Get the Bans
	 * 
	 * @return List<BanModel>/null
	 */
	public List<BanModel> getBans() {
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive Bans..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Bans");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String bans = null;
		if((bans = getRequest(url)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Bans successful received");
			
			List<BanModel> banList = new ArrayList<BanModel>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(bans);
				rootNode.path("Bans").elements().forEachRemaining(
						elem -> banList.add(
								new BanModel(
										elem.get("BanID").asText(),
										elem.get("UserID").asText(),
										elem.get("LastKnownUsername").asText(),
										elem.get("BannedByUserID").asText(),
										elem.get("BannedByUserName").asText(),
										elem.get("Reason").asText(),
										elem.get("TimestampUnixUTC").asText(),
										elem.get("DurationInSeconds").asLong()
										)));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return banList;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive Bans! Request failed.");
			return null;
		}	
		
	}

	/**
	 * Get current online Players
	 * 
	 * @return List<PlayersModel>/null
	 */
	public List<PlayersModel> getOnlinePlayers() {
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive online Players..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Players");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String players = null;
		if((players = getRequest(url)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Online Players successful received");
			
			List<PlayersModel> onlinePlayers = new ArrayList<PlayersModel>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(players);
				rootNode.path("Players").elements().forEachRemaining(
						elem -> onlinePlayers.add(
								new PlayersModel(
										elem.get("UserName").asText(),
										elem.get("UserID").asText(),
										elem.get("Species").asText(),
										new NeedsModel(
											elem.get("Needs").get("Eat").asDouble(),
											elem.get("Needs").get("Drink").asDouble(),
											elem.get("Needs").get("Oxygen").asDouble(),
											elem.get("Needs").get("Rest").asDouble()
										),
										elem.get("Growth").asDouble(),
										elem.get("GrowthStage").asText(),
										elem.get("Health").asDouble(),
										elem.get("IsAlive").asBoolean(),
										new LocationModel(
											elem.get("Location").get("X").asDouble(),
											elem.get("Location").get("Y").asDouble(),
											elem.get("Location").get("Z").asDouble()
										),
										elem.get("LoginUnixUTCTimestamp").asText()
										)));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return onlinePlayers;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive online Players! Request failed.");
			return null;
		}	
	}


	/**
	 * Get specific Player (Saved Data)
	 * 
	 * @param string userID (Steam64 ID)
	 * 
	 * @return PlayerModel/null
	 */
	public PlayerModel getSavedPlayer(String userID) {
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive saved Player..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Players?userid="+userID);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String player = null;
		if((player = getRequest(url)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Saved Player successful received");
			ObjectMapper objectMapper = new ObjectMapper();
			PlayerModel savedPlayer = null;
			
			
			try {
				JsonNode rootNode = objectMapper.readTree(player);
				
				//AllKnownUsernames
				List<String> allKnownUsernames = new ArrayList<String>();
				rootNode.get("Player").get("AllKnownUsernames").forEach(username -> allKnownUsernames.add(username.asText()));
				
				//LogonTimestamps
				List<String> logonTimestamps = new ArrayList<String>();
				rootNode.get("Player").get("LogonTimestamps").forEach(timestamp -> logonTimestamps.add(timestamp.asText()));
				
				//Pawn->PersistedSkin->Alphas
				List<AlphasModel> alphas = new ArrayList<AlphasModel>();
				rootNode.get("Pawn").get("PersistedSkin").get("Alphas").forEach(
						elem -> alphas.add(new AlphasModel(
											elem.get("Slot").asText(),
											elem.get("ID").asText(),
											new ColorModel(
													elem.get("Color").get("R").asDouble(),
													elem.get("Color").get("R").asDouble(),
													elem.get("Color").get("R").asDouble(),
													elem.get("Color").get("R").asInt()
													)
											))
						);
				
				savedPlayer = new PlayerModel(
						rootNode.get("Player").get("NetId").asText(),
						rootNode.get("Player").get("LastKnownUsername").asText(),
						allKnownUsernames,
						logonTimestamps,
						rootNode.get("Player").get("UseCustomNameColour").asBoolean(),
						new CustomNameColourModel(
							rootNode.get("Player").get("CustomNameColour").get("R").asInt(),
							rootNode.get("Player").get("CustomNameColour").get("G").asInt(),
							rootNode.get("Player").get("CustomNameColour").get("B").asInt(),
							rootNode.get("Player").get("CustomNameColour").get("A").asDouble()
						),
						new PawnModel(
							rootNode.get("Pawn").get("Species").asText(),
							new PersistedSkinModel(
								rootNode.get("Pawn").get("PersistedSkin").get("BasePresetId").asText(),
								new BaseColourModel(
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("R").asDouble(),
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("G").asDouble(),
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("B").asDouble(),
									rootNode.get("Pawn").get("PersistedSkin").get("BaseColour").get("A").asInt()
								),
								alphas								
								),
							rootNode.get("Pawn").get("OwnerNetId").asText(),
							new LocationModel(
								rootNode.get("Pawn").get("Location").get("X").asDouble(),
								rootNode.get("Pawn").get("Location").get("Y").asDouble(),
								rootNode.get("Pawn").get("Location").get("Z").asDouble()
								),
							rootNode.get("Pawn").get("Growth").asDouble(),
							rootNode.get("Pawn").get("Health").asDouble(),
							rootNode.get("Pawn").get("Stamina").asDouble(),
							rootNode.get("Pawn").get("Thirst").asDouble(),
							rootNode.get("Pawn").get("Hunger").asDouble(),
							rootNode.get("Pawn").get("Oxygen").asDouble()
						),
						rootNode.get("HasPersistedPawn").asBoolean()
						);
				
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return savedPlayer;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive saved Player! Request failed.");
			return null;
		}	
	}
	
	/**
	 * Get the Server Uptime
	 * 
	 * @return String
	 */
	public String getServerUptime() {
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive Server Uptime..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Session");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String session = null;
		if((session = getRequest(url)) != null) {
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

	
	/**
	 * Set the Custom Name Colour for a Player
	 * 
	 * @param userID
	 * @param useCustomNameColor
	 * @param customNameColorModel
	 * @return boolean
	 */
	public boolean setCustomNameColourForPlayer(String userID, boolean useCustomNameColor, CustomNameColourModel customNameColorModel) {
		if(isRateLimitReached())
			return false;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try posting Custom Name Colour change..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/NameColour");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String body = ""
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
		
		if(postRequest(url, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Posting Custom Name Colour change successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant post Custom Name Colour change! Posting Name Colour change failed.");
			return false;
		}
	}
	
	/**
	 * Get the World
	 * 
	 * @return WorldModel
	 */
	public WorldModel getWorld() {
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive saved Player..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/World");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String world = null;
		if((world = getRequest(url)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Saved Player successful received");
			ObjectMapper objectMapper = new ObjectMapper();
			WorldModel worldServer = null;

			try {
				JsonNode rootNode = objectMapper.readTree(world);
				worldServer = new WorldModel(
						rootNode.get("Time").asDouble(),
						rootNode.get("Weather").asText(),
						rootNode.get("Season").asText(),
						rootNode.get("Day").asInt(),
						rootNode.get("Month").asInt(),
						rootNode.get("Year").asInt(),
						rootNode.get("Lat").asDouble(),
						rootNode.get("Long").asDouble()
						);

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return worldServer;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive saved Player! Request failed.");
			return null;
		}	
	}
	
	/**
	 * Get the AI
	 * 
	 * @return List<AIModel>/null
	 */
	public List<AIModel> getAI(){
		if(isRateLimitReached())
			return null;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try to receive AI..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/AI");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String ais = null;
		if((ais = getRequest(url)) != null) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "AI successful received");
			
			List<AIModel> aisServer = new ArrayList<AIModel>();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				JsonNode rootNode = objectMapper.readTree(ais);
				rootNode.path("AI").elements().forEachRemaining(
						elem -> aisServer.add(
								new AIModel(
										elem.get("IsActive").asBoolean(),
										elem.get("Species").asText(),
										new NeedsModel(
												elem.get("Needs").get("Eat").asDouble(),
												elem.get("Needs").get("Drink").asDouble(),
												elem.get("Needs").get("Oxygen").asDouble(),
												elem.get("Needs").get("Rest").asDouble()
												),
										elem.get("Growth").asDouble(),
										elem.get("GrowthStage").asText(),
										elem.get("Health").asDouble(),
										elem.get("IsAlive").asBoolean(),
										new LocationModel(
												elem.get("Location").get("X").asDouble(),
												elem.get("Location").get("Y").asDouble(),
												elem.get("Location").get("Z").asDouble()
												)
										)
								));
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return aisServer;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant receive AI! Request failed.");
			return null;
		}	
	}
	
	/**
	 * @param userID
	 * @param bannedByUserID
	 * @param banReason
	 * @return boolean
	 */
	public boolean addBan(String userID, String bannedByUserID, String banReason) {
		if(isRateLimitReached())
			return false;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try adding Ban..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Bans");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String body = ""
	    		+ "{"
	    		+ "\"UserID\" : \""+userID+"\","
	    		+ "\"BannedByUserID\" : \""+bannedByUserID+"\","
	    		+ "\"BanReason\" : \""+banReason+"\""
	    		+ "}";
		
		if(putRequest(url, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Adding Ban successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant add Ban! Request failed.");
			return false;
		}
	}


	/**
	 * Remove a Ban
	 * 
	 * @param string
	 * @return boolean
	 */
	public boolean removeBan(String userID) {
		if(isRateLimitReached())
			return false;
		
		Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Try removing Ban..");
		
		URL url = null;
		try {
			url = new URL(this.urlConfig + "/Bans");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String body = ""
	    		+ "{"
	    		+ "\"UserID\" : \""+userID+"\""
	    		+ "}";
		
		if(deleteRequest(url, body)) {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.INFO, "Removing Ban successful");
			return true;
		}
		else {
			Logger.log(this.getClass().getSimpleName(), LOGLEVEL.ERROR, "Cant remove Ban! Request failed.");
			return false;
		}
	}
}
