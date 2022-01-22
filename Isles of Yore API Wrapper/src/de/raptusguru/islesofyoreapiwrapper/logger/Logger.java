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
package de.raptusguru.islesofyoreapiwrapper.logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Raptusguru
 *
 */
public class Logger {

	
	/**
	 * A static Logger method that can be used to log everything with date and time to the console output
	 * 
	 * @param className
	 * @param logLevel
	 * @param msg
	 */
	public static void log(String className, LOGLEVEL logLevel, String msg) {
		switch (logLevel.name()) {
		case "ERROR":
				logError(className, msg);
			break;
		case "WARN":
				logWarn(className, msg);
			break;
		case "INFO":
				logInfo(className, msg);
			break;
		}
	}

	/**
	 * @param className
	 * @param msg
	 */
	private static void logInfo(String className, String msg) {
		String formattedMsg = "[" + getDate() + "][" + getTime() + "][IoY-API][" + className + "][INFO] " + msg;
		System.out.println(formattedMsg);
	}

	/**
	 * @param className
	 * @param msg
	 */
	private static void logWarn(String className, String msg) {
		String formattedMsg = "[" + getDate() + "][" + getTime() + "][IoY-API][" + className + "][WARN] " + msg;
		System.out.println(formattedMsg);
	}

	/**
	 * @param msg 
	 * @param className 
	 * 
	 */
	private static void logError(String className, String msg) {
		String formattedMsg = "[" + getDate() + "][" + getTime() + "][IoY-API][" + className + "][ERROR] " + msg;
		System.out.println(formattedMsg);
	}
	
	/**
	 * @return String
	 */
	private static String getDate() {
		return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.now());
	}
	
	/**
	 * @return String
	 */
	private static String getTime() {
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now());
	}
}
