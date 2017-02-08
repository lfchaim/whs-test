package br.com.whs.whstest.test.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.whs.whstest.mongodb.Column;
import br.com.whs.whstest.mongodb.Table;

public class TableMongoTest {

	public static void main(String[] args) {
		delete();
		create();
		list();
		update();
	}

	private static void list(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("test");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
	}
	
	private static void delete(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("test");
		collection.deleteOne(Filters.eq("tableName","Customer"));
	}
	
	private static void update(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("test");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		    	Document doc = cursor.next();
		    	GsonBuilder gsonBuilder = new GsonBuilder();
		    	Gson gson = gsonBuilder.create();
		    	Map<String,Object> mapDoc = (Map)gson.fromJson(doc.toJson(), Map.class);
		    	mapDoc.put("updated", new Date().toString());
		    	Document docUp = new Document(mapDoc);
		    	collection.updateOne(doc, new Document("$set", new Document("updated", new Date())));
		    	//collection.updateOne(doc, docUp);
		    	//collection.updateOne(doc, new Document("updated", new Date()));
		    }
		    list();
		} finally {
		    cursor.close();
		}
	}

	private static void create(){
		String tableName = "Customer";
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase db = mongoClient.getDatabase("test");
		Table table = new Table();
		table.setTableName(tableName);
		table.setTableSchem("test");
		table.setUpdated(new Date());
		List<Column> columns = new ArrayList<Column>();
		Column col = new Column();
		col.setColumnName(tableName.toLowerCase()+"Name");
		col.setColumnSize(100);
		col.setDataType(12);
		col.setDecimalDigits(0);
		col.setIsAutoincrement(false);
		col.setOrdinalPosition(1);
		columns.add(col);
		table.setColumns(columns);
		Gson gson = new Gson();
		String data = gson.toJson(table);
		
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, Object> map = g.fromJson(data, new TypeToken<Map<String, Object>>() {}.getType());

		Document doc = new Document(map);
		MongoCollection<Document> collection = db.getCollection("test");
		collection.insertOne(doc);
		
	}
}
