package org.dgt.wikitopics;

import com.satori.rtm.*;
import com.satori.rtm.model.*;

public class wikitopics {
  static final String endpoint = "ws://open-data.api.satori.com";
  static final String appkey = "2CFa5d42bd26A94208dD27F7F790E5Ef";
  static final String channel = "world";
  static int    counter=0;

  public static void main(String[] args) throws InterruptedException {

	

    final RtmClient client = new RtmClientBuilder(endpoint, appkey)
        .setListener(new RtmClientAdapter() {
          @Override
          public void onEnterConnected(RtmClient client) {
            System.out.println("Connected to Satori RTM!" + endpoint);
          }
        })
        .build();


    SubscriptionAdapter listener = new SubscriptionAdapter() {
      @Override
      public void onSubscriptionData(SubscriptionData data) {
        for (AnyJson json : data.getMessages()) {
          System.out.println("DDDD Got message: " + json.toString());
	  System.out.println("Count = " + counter++);
        }
      }
    };

    client.createSubscription(channel, SubscriptionMode.SIMPLE, listener);

    client.start();
  }
}
