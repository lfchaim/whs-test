package br.com.whs.whstest;

import java.util.ArrayList;
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

public class TableTest {

	public static void main(String[] args) {
		//create();
		list();
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
	
	private static void create(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase db = mongoClient.getDatabase("test");
		Table table = new Table();
		table.setTableName("User");
		table.setTableSchem("test");
		List<Column> columns = new ArrayList<Column>();
		Column col = new Column();
		col.setColumnName("userName");
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
