package de.hackathlon.restgame.server;

import java.util.concurrent.ConcurrentHashMap;

public class StaticObjects {
	public static ConcurrentHashMap<String, GenericPair<Long, Boolean>> users=new ConcurrentHashMap<String, GenericPair<Long,Boolean>>();
	//player/opponent/showed message already
	public static ConcurrentHashMap<String, GenericPair<String, Boolean>> gameInvitations=new ConcurrentHashMap<String, GenericPair<String,Boolean>>();
	public static ConcurrentHashMap<String, String> responsesToInvitations=new ConcurrentHashMap<String, String>();
	public static ConcurrentHashMap<String, String> runningGames=new ConcurrentHashMap<String, String>();
	public static ConcurrentHashMap<String, GenericPair<String[], Boolean>> boardsState= new ConcurrentHashMap<String, GenericPair<String[],Boolean>>();

}
