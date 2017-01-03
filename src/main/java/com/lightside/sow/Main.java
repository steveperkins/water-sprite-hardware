package com.lightside.sow;

import spark.Spark;

public class Main {
	
	public static void main(String[] args) {
		Spark.webSocket("/flow", SuckitSowWebSocketHandler.class);
		Spark.staticFileLocation("/public");
		Spark.init();
	}
	
}
