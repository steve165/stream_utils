package org.dgt.wikitopics;

import com.satori.rtm.*;
import com.satori.rtm.model.*;
import  org.dgt.wikitopics.StreamDataCollector;

public class wikitopics {
  static final String endpoint = "ws://open-data.api.satori.com";
  static final String appkey = "cBeDB1DF5baDCC2D1afADd6c71900B29";
  static final String channel = "Meetup-RSVP";
  static int    counter=0;
  static String channelName;
  static StreamDataCollector sdc/* = new StreamDataCollector()*/;

  public static void main(String[] args) throws InterruptedException {

     channelName = args[0];
     sdc = new StreamDataCollector(channelName);

    final RtmClient client = new RtmClientBuilder(endpoint, appkey)
        .setListener(new RtmClientAdapter() {
          @Override
          public void onEnterConnected(RtmClient client) {
            System.out.println("Connected to Satori RTM!" + endpoint);
		/*sdc = new StreamDataCollector(channelName);*/
          }
        })
        .build();


    SubscriptionAdapter listener = new SubscriptionAdapter() {
      @Override
      public void onSubscriptionData(SubscriptionData data) {
        for (AnyJson json : data.getMessages()) {
            sdc.scanObject(json.toString());
          // System.out.println("DDDD Got message: " + json.toString());
          // System.out.println("Count = " + counter++);
        }
      }
    };

	System.out.println("Channel Name = " + args[0]);

    client.createSubscription(/*channel*/ args[0], SubscriptionMode.SIMPLE, listener);

    client.start();
  }
}
