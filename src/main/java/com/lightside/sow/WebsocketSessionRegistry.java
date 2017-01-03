package com.lightside.sow;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.websocket.api.Session;

public class WebsocketSessionRegistry {
	
	private static WebsocketSessionRegistry INSTANCE;
	private List<Session> sessions;
	
	public static WebsocketSessionRegistry getInstance() {
		if(null == INSTANCE) INSTANCE = new WebsocketSessionRegistry();
		return INSTANCE;
	}
	public List<Session> getSessions() {
		if(null == sessions) sessions = new ArrayList<Session>();
		return sessions;
	}
	public void addSession(Session session) {
		getSessions().add(session);
	}
	public void removeSession(Session session) {
		getSessions().remove(session);
	}
}


