package com.adbms.tweets;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TwitterStreaming {

	public static void main(String[] args) {
        try
        {
        	Mongo m = new Mongo("localhost", 27017);
            DB db = m.getDB("twitterdb");
//          int tweetCount = 100;
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("auth.properties")));
			final DBCollection coll = db.getCollection("tweets");
            
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	.setOAuthConsumerKey(prop.getProperty("consumer_key"))
        	.setOAuthConsumerSecret(prop.getProperty("consumer_secret"))
        	.setOAuthAccessToken(prop.getProperty("access_token"))
        	.setOAuthAccessTokenSecret(prop.getProperty("token_secret"));
        	
        	StatusListener listener = new StatusListener(){
//                int count = 0;
                public void onStatus(Status status) {
//               		System.out.println(status.getId() +  " : " + status.getSource()+ " : " +status.getCreatedAt()+ " : " + status.getUser().getName() + " : " +status.getText());
                	//System.out.println(status.getUser().getName() + " : " + status.getText());
                	
                	DBObject dbObj = new BasicDBObject();
                	dbObj.put("id_str", status.getId());
                	dbObj.put("name", status.getUser().getName());
                	dbObj.put("text", status.getText());
					dbObj.put("date",status.getCreatedAt());
                	dbObj.put("source", status.getSource());
					if (status.getPlace() !=null) {
						dbObj.put("country",status.getPlace().getCountry());
					} else {
						return;
					}
//                	if(status.getGeoLocation() != null) {
//	                	DBObject pos = new BasicDBObject();
//	                	pos.put("long", status.getGeoLocation().getLongitude());
//	                	pos.put("lat", status.getGeoLocation().getLatitude());
//	                	dbObj.put("pos", pos);
//                	} else if( status.getPlace() != null ) {
//                		dbObj.put("country", status.getPlace().getCountry());
//                	}
                	coll.insert(dbObj);
                }
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
                public void onException(Exception ex) {
                    ex.printStackTrace();
                }
				@Override
				public void onScrubGeo(long arg0, long arg1) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void onStallWarning(StallWarning arg0) {
					// TODO Auto-generated method stub
					
				}
            };
            
            TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
            twitterStream.addListener(listener);
            // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
            twitterStream.sample();
            
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}

}