package com.api.tweet;

import twitter4j.TwitterException;


public class App 
{
    public static void main( String[] args ) throws TwitterException
    {
    	 try {
	    	TwitterApi api = new TwitterApi();
	    	api.postMessage("Post Message using Twitter4J");
	    	api.getMyTimeline();
	    	api.getAccountSettings();
	    	api.deleteMyLastStatus();
	    	
    	 }catch (TwitterException ex){
    		 ex.printStackTrace();
    		 System.out.println("Something is going wrong " + ex.getMessage());
    	 }
    	
    }
}
