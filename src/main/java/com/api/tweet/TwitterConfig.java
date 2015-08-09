package com.api.tweet;

import twitter4j.auth.AccessToken;

public class TwitterConfig {
	private static String consumerKey = "______________________";
	private static String consumerSecretKey = "__________________________";
	private static String accessTokenKey = "______________________________";
	private static String accessTokenSecretKey = "__________________________";
	
	
	public AccessToken getAccessToken(){
		return new AccessToken(accessTokenKey, accessTokenSecretKey);
	}


	public  String getConsumerKey() {
		return consumerKey;
	}


	public String getConsumerSecretKey() {
		return consumerSecretKey;
	} 

}
