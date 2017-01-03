package com.lightside.sow;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;
import org.json.JSONTokener;

@WebSocket
public class SuckitSowWebSocketHandler {
	
	@OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
		WebsocketSessionRegistry.getInstance().addSession(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
    	WebsocketSessionRegistry.getInstance().removeSession(session);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
    	JSONObject obj = new JSONObject(new JSONTokener(message));
    	
        broadcastMessage(obj.getInt("player"), obj.getDouble("speed"), obj.getDouble("volume"));
    }
    
    public void broadcastMessage(int player, Double speed, Double amount) {
    	WebsocketSessionRegistry.getInstance().getSessions().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("player", player)
                    .put("speed", speed)
                    .put("volume", amount)
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
