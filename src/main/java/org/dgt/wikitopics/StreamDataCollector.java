package org.dgt.wikitopics;

import	java.util.Iterator;
import	org.json.*;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public	class	StreamDataCollector	{

	static	int	counter;
	static	boolean	mdc = false;
	static	DBCollection collection;

	public	StreamDataCollector(String collName)	{

		try	{
			System.out.println("Connecting to Mongo");
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("test");
			collection = db.createCollection(collName, (DBObject)null);
			System.out.println("Connected to Mongo");
		}
		catch (Exception e)	{
			System.out.println(e.toString());
		}
	}


	public	void	scanObject(String sob)	{

		counter++;
		//System.out.println("Record received: " + counter);


		try	{
			JSONObject jso = new JSONObject(sob);
			
			DBObject dbObject = (DBObject)JSON.parse(sob);
			collection.insert(dbObject);

			System.out.println("Number of docs: " + collection.getCount());
			System.out.println(sob);

		/*	System.out.println(jso.getString("venue"));
			System.out.print("{");
			for(Iterator keys=jso.keys();keys.hasNext();) {
    				// Object name = jso.get(key.next());
     				//now name contains the firstname, and so on... 
				System.out.print(keys.next() + "|");
			} */
			System.out.println("}(" + counter +")");
		}
		catch (JSONException jsonE)	{
			System.out.print("NO VENUE");
		}
		System.out.println("}(" + counter +")");
	}

}
