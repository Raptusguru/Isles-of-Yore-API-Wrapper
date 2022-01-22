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
package de.raptusguru.islesofyoreapiexample;

import java.util.List;

import de.raptusguru.islesofyoreapiwrapper.API;
import de.raptusguru.islesofyoreapiwrapper.RequestManager;
import de.raptusguru.islesofyoreapiwrapper.model.AIModel;
import de.raptusguru.islesofyoreapiwrapper.model.AdminModel;
import de.raptusguru.islesofyoreapiwrapper.model.BanModel;
import de.raptusguru.islesofyoreapiwrapper.model.PlayerModel;
import de.raptusguru.islesofyoreapiwrapper.model.PlayersModel;
import de.raptusguru.islesofyoreapiwrapper.model.WorldModel;

/**
 * @author Raptusguru
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Variant 1 for set up API Options
		 * 
		 * For configure the Ratelimit you can use
		 * API api = new API("127.0.0.1", "8085", "Waldi", "NotYourBirthday");
		 * api.config.setRatelimit(5);
		 * RequestManager rm = api.build();
		 */
		
		/*
		 * Variant 2 for standard options
		 * 
		 * RequestManager rm = new API("127.0.0.1", "8085", "Waldi", "NotYourBirthday").build();
		 */
		
		API api = new API("underground-community.com", "8085", "Admin", "UCTest");
		api.config().setRatelimit(20);
		api.config().setPrint_serverResponse(true);
		RequestManager rm = api.build();
		
		//Make an Announcement
		rm.announce("Hello everyone!");
		
		// Add a Ban
		rm.addBan("324234234", "2234234234", "Just a Test Ban");

		// Remove a Ban
		rm.removeBan("324234234");

		// Add an Admin
		rm.addAdmin("123456789");

		// Remove an Admin
		rm.removeAdmin("123456789");
		
		//Get Admins (IDs)
		List<AdminModel> adminIDs = rm.getAdmins();
		System.out.println("- All Admins (IDs) -");
		adminIDs.forEach(admin -> System.out.println("AdminID: " + admin.getId() + "\r"));
		
		//Get Bans
		List<BanModel> bans = rm.getBans();
		bans.forEach(ban -> System.out.println(
				"- Banlist -"
				+ "BanID: " + ban.getBanID() + "\r"
				+ "UserID: " + ban.getUserID() + "\r"
				+ "LastKnownUsername: " + ban.getLastKnownUsername() + "\r"
				+ "BannedByUserID: " + ban.getBannedByUserID() + "\r"
				+ "BannedByUserName: " + ban.getBannedByUserName() + "\r"
				+ "Reason: " + ban.getReason() + "\r"
				+ "TimestampUnixUTC: " + ban.getTimeStampUnixUTC() + "\r"
				+ "DurationInSeconds: " + ban.getDurationInSeconds() + "\r"
				));
		
		
		//Get online Players
		List<PlayersModel> onlinePlayers = rm.getOnlinePlayers();
		onlinePlayers.forEach(player -> System.out.println(
				"- Current online Players"
				+ "UserName: " + player.getUserName() + "\r"
				+ "UserID: " + player.getUserID() + "\r"
				+ "Species: " + player.getSpecies() + "\r"
				+ "Needs: (Eat: " + player.getNeeds().getEat() + ", Drink: " + player.getNeeds().getDrink() + ", Oxygen: " + player.getNeeds().getOxygen() + ", Rest: " + player.getNeeds().getRest() + ")\r"
				+ "Growth: " + player.getGrowth() + "\r"
				+ "GrowthStage: " + player.getGrowthStage() + "\r"
				+ "Health: " + player.getHealth() + "\r"
				+ "IsAlive: " + player.isAlive() + "\r"
				+ "Location: (x: " + player.getLocation().getX() + ", y: " + player.getLocation().getY() + ", z: " + player.getLocation().getZ() + ")\r"
				+ "LoginUnixUTCTimestamp: " + player.getLoginUnixUTCTimestamp() + "\r"
				));
		
		//Get a specific Player (saved data)
		PlayerModel savedPlayer = rm.getSavedPlayer("76561198009027096");
		System.out.println(
				"- Player Infos -\r"
				+ "UserID: " + savedPlayer.getUserID() + "\r"
				+ "LastKnownUsername: " + savedPlayer.getLastKnownUsername() + "\r"
				+ "UseCustomNameColour: " + savedPlayer.isUseCustomColour() + "\r"
				+ "CustomNameColour: (R: " + savedPlayer.getCustomNameColour().getR() + ", G: " + savedPlayer.getCustomNameColour().getG() + ", B: " + savedPlayer.getCustomNameColour().getB() + ", A: " + savedPlayer.getCustomNameColour().getA() + ")"
				);
		savedPlayer.getAllKnownUsernames().forEach(username -> System.out.println("AllKnownUsernames: " + username));
		savedPlayer.getLogonTimestamps().forEach(logonTimestamp -> System.out.println("LogonTimestamps: " + logonTimestamp));
		System.out.println("HasPersistedPawn: " + savedPlayer.isHasPersitedPawn());
		System.out.println(
				"- Character Infos -\r"
				+ "Species: " + savedPlayer.getPawn().getSpecies() + "\r"
				+ "OwnerUserID: " + savedPlayer.getPawn().getOwnerUserID() + "\r"
				+ "Growth: " + savedPlayer.getPawn().getGrowth() + "\r"
				+ "Hunger: " + savedPlayer.getPawn().getHunger() + "\r"
				+ "Thirst: " + savedPlayer.getPawn().getThirst() + "\r"
				+ "Stamina: " + savedPlayer.getPawn().getStamina() + "\r"
				+ "Oxygen: " + savedPlayer.getPawn().getOxygen() + "\r"
				+ "Location: (X: " + savedPlayer.getPawn().getLocation().getX() + ", Y: " + savedPlayer.getPawn().getLocation().getY() + ", Z: " + savedPlayer.getPawn().getLocation().getZ() + ")\r"
				+ "BasePresetId: " + savedPlayer.getPawn().getPersistedSkin().getBasePresetID() + "\r"
				+ "BaseColour: (R: " + savedPlayer.getPawn().getPersistedSkin().getBaseColour().getR() + ", G: " + savedPlayer.getPawn().getPersistedSkin().getBaseColour().getG() + ", B: " + savedPlayer.getPawn().getPersistedSkin().getBaseColour().getB() + ", A: " + savedPlayer.getPawn().getPersistedSkin().getBaseColour().getA() + ")"
				);
		savedPlayer.getPawn().getPersistedSkin().getAlphas().forEach(elem ->
			System.out.println(
					"Alphas: ("
					+ "Slot: " + elem.getSlot() + ", "
					+ "ID: " + elem.getId() + ", "
					+ "Color: (R: " + elem.getColor().getR() + ", G: " + elem.getColor().getG() + ", B: " + elem.getColor().getB() + ", A: " + elem.getColor().getA() + "))"
					)
		);
		
		//Get the Server Uptime
		String serverUptime = rm.getServerUptime();
		System.out.println("Server Uptime (UnixUTC): " + serverUptime);
		
		//Change Custom Name Colour of a specific User (Currently not working?!)
		//rm.setCustomNameColourForPlayer("76561198009027096", true, new CustomNameColourModel(0, 250, 234, 1.0));
		
		//Get actual World Info
		WorldModel world = rm.getWorld();
		System.out.println(
				"- World Info -\r"
				+ "Time: " + world.getTime() + "\r"
				+ "Weather: " + world.getWeather() + "\r"
				+ "Season: " + world.getSeason() + "\r"
				+ "Day: " + world.getDay() + "\r"
				+ "Month: " + world.getMonth() + "\r"
				+ "Year: " + world.getYear() + "\r"
				+ "Latitude: " + world.getLatitude() + "\r"
				+ "Longitude: " + world.getLongitude()
				);
		
		//Get the AIs from the running Server
		List<AIModel> ais = rm.getAI();
		ais.forEach(ai -> 
			System.out.println(
					"- AI Info -\r"
					+ "IsActive: " + ai.isActive() + "\r"
					+ "Species: " + ai.getSpecies() + "\r"
					+ "Needs: (Eat: " + ai.getNeeds().getEat() + ", Drink: " + ai.getNeeds().getDrink() + ", Oxygen: " + ai.getNeeds().getOxygen() + ", Rest: " + ai.getNeeds().getRest() + ")\r"
					+ "Growth: " + ai.getGrowth() + "\r"
					+ "GrowthStage: " + ai.getGrowthStage() + "\r"
					+ "Health: " + ai.getHealth() + "\r"
					+ "IsAlive: " + ai.isAlive() + "\r"
					+ "Location: (X: " + ai.getLocation().getX() + ", Y: " + ai.getLocation().getY() + ", Z: " + ai.getLocation().getZ() + ")\r"
					)
		);
		
		
	}
}
