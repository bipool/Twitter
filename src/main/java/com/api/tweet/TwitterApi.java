package com.api.tweet;


import java.util.List;

import org.apache.log4j.Logger;

import twitter4j.AccountSettings;
import twitter4j.Location;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * @author Bipool
 *
 */

public class TwitterApi {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	/**
	 * To post your status in your twitter timeline
	 * @param status
	 * @throws TwitterException
	 */
	public void postMessage(String status) throws TwitterException{
		Twitter twitter = getTwitterInstance();
		twitter.updateStatus(status);
	}
	
	
	
	
	
	/**
	 * To get my time line Info
	 * @throws TwitterException
	 */
	public void getMyTimeline()throws TwitterException{
		Twitter twitter = getTwitterInstance();
		List<Status> statuses = twitter.getHomeTimeline();
		if(statuses == null || statuses.size() == 0){
			System.out.println("Nothing found");
			return; 
		}
		
		
		
	    for (Status status : statuses) {
	    	
	        System.out.println(status.getCreatedAt() + ":: " + status.getUser().getName() + ":" +
	                           status.getText());
	        
	        User user = status.getUser();
	        System.out.println( user.getName() + " :: " + user.getFollowersCount() );
	        System.out.println("-------------");
	    }
	}
	
	
	
	
	
	/**
	 * To get my Twitter account settings
	 * @throws TwitterException
	 */
	public void getAccountSettings() throws TwitterException{
		Twitter twitter = getTwitterInstance();
        AccountSettings settings = twitter.getAccountSettings();
        
        System.out.println("Screen name: " + settings.getScreenName());
        System.out.println("Language: " + settings.getLanguage());
        System.out.println("Time Zone: " + settings.getTimeZone().getName());
        System.out.println("Sleep time enabled: " + settings.isSleepTimeEnabled());
        System.out.println("Sleep end time: " + settings.getSleepEndTime());
        System.out.println("Sleep start time: " + settings.getSleepStartTime());
        System.out.println("Geo enabled: " + settings.isGeoEnabled());
        
        System.out.println("Listing trend locations:");
        Location[] locations = settings.getTrendLocations();
        for (Location location : locations) {
            System.out.println(" " + location.getName());
        }
        
        RateLimitStatus rateLimitStatus = settings.getRateLimitStatus();
        System.out.println("Limit : " + rateLimitStatus.getLimit());
        System.out.println("Remain Limit : " + rateLimitStatus.getRemaining());
        System.out.println("Reset time in sec : " + rateLimitStatus.getSecondsUntilReset());
        
	}
	
	
	
	/**
	 * To Delete my status
	 * @throws TwitterException
	 */
	public void deleteMyLastStatus() throws TwitterException{
		Twitter twitter = getTwitterInstance();
		List<Status> statuses = twitter.getHomeTimeline();
		if(statuses == null || statuses.size() == 0){
			System.out.println("Nothing found");
			return; 
		
		}
		Status lastStatus = statuses.get(0);
		if(lastStatus == null || lastStatus.getId() == 0){
			System.out.println("No Status found");
			return;
		} 
		
		twitter.destroyStatus(lastStatus.getId());
	}
	
	
	
	/**
	 * To get twitter instance
	 * @return Twitter instance
	 */
	private Twitter getTwitterInstance(){
		Twitter twitter = new TwitterFactory().getInstance();
		setOAuth(twitter);
		return twitter;
	}
	
	private void setOAuth(Twitter twitter){
		TwitterConfig config = new TwitterConfig();
		twitter.setOAuthConsumer(config.getConsumerKey(), config.getConsumerSecretKey());
		twitter.setOAuthAccessToken(config.getAccessToken());
	}
}
