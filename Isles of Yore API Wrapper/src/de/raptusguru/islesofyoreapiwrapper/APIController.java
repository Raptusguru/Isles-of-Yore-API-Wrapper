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

import de.raptusguru.islesofyoreapiwrapper.controller.AIController;
import de.raptusguru.islesofyoreapiwrapper.controller.AdminController;
import de.raptusguru.islesofyoreapiwrapper.controller.AnnouncementController;
import de.raptusguru.islesofyoreapiwrapper.controller.BanController;
import de.raptusguru.islesofyoreapiwrapper.controller.CommandController;
import de.raptusguru.islesofyoreapiwrapper.controller.CustomNameColourController;
import de.raptusguru.islesofyoreapiwrapper.controller.OnlinePlayersController;
import de.raptusguru.islesofyoreapiwrapper.controller.PlayerController;
import de.raptusguru.islesofyoreapiwrapper.controller.SessionController;
import de.raptusguru.islesofyoreapiwrapper.controller.WorldController;

/**
 * @author Raptusguru
 *
 */
public class APIController {
	// Controller
	private AdminController admins;
	private AIController ai;
	private AnnouncementController announcement;
	private BanController bans;
	private CommandController command;
	private OnlinePlayersController onlinePlayers;
	private PlayerController player;
	private WorldController world;
	private SessionController session;
	private CustomNameColourController customNameColour;
	
	/**
	 * @param rm
	 */
	protected APIController(RequestManager rm) {
		this.admins = new AdminController(rm);
		this.ai = new AIController(rm);
		this.announcement = new AnnouncementController(rm);
		this.bans = new BanController(rm);
		this.command = new CommandController(rm);
		this.onlinePlayers = new OnlinePlayersController(rm);
		this.player = new PlayerController(rm);
		this.world = new WorldController(rm);
		this.session = new SessionController(rm);
		this.customNameColour = new CustomNameColourController(rm);
	}

	/**
	 * @return the admins
	 */
	public AdminController admins() {
		return admins;
	}

	/**
	 * @return the ais
	 */
	public AIController ai() {
		return ai;
	}

	/**
	 * @return the announcement
	 */
	public AnnouncementController announcement() {
		return announcement;
	}

	/**
	 * @return the bans
	 */
	public BanController bans() {
		return bans;
	}

	/**
	 * @return the command
	 */
	public CommandController command() {
		return command;
	}

	/**
	 * @return the onlinePlayers
	 */
	public OnlinePlayersController onlinePlayers() {
		return onlinePlayers;
	}

	/**
	 * @return the player
	 */
	public PlayerController player() {
		return player;
	}

	/**
	 * @return the world
	 */
	public WorldController world() {
		return world;
	}

	/**
	 * @return the session
	 */
	public SessionController session() {
		return session;
	}

	/**
	 * @return the customNameColour
	 */
	public CustomNameColourController customNameColour() {
		return customNameColour;
	}
	
	

}
