package useMongo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mangoJDBC.MongoDBConnection;

public class InsertMongo {
	public static void main(String[] args) {
		InsertMongo im = new InsertMongo();
		im.insert2();
	}
	
	/**
	 * 插入一个文档
	 */
	public void insertOne() {
		MongoDatabase mdb = MongoDBConnection.getMongoDatabase();
		//创建一个集合
//		mdb.createCollection("test");
		MongoCollection<Document> coll = mdb.getCollection("test");
		Document document = new Document().append("name", "monaka")
				.append("weight", "height")
				.append("age", 20);
		coll.insertOne(document);
		System.out.println("插入成功");
	}
	public void insert2() {
		MongoDatabase mdb = MongoDBConnection.getMongoDatabase();
		//创建一个集合
//		mdb.createCollection("test");
		MongoCollection<Document> hobby = mdb.getCollection("test");
		hobby.insertOne(new Document("name", "csc").append("hobby", Arrays.asList("reading", "coding")));
		hobby.insertOne(new Document("name", "nicky").append("hobby", Arrays.asList("game")));
		hobby.insertOne(new Document("name", "jack").append("hobby", Arrays.asList("movie")));
		hobby.insertOne(new Document("name", "tom").append("hobby", Arrays.asList("reading", "coding")));
		hobby.insertOne(new Document("name", "lucy").append("hobby", Arrays.asList("reading", "football")));
		hobby.insertOne(new Document("name", "lion").append("hobby", Arrays.asList("basketball", "football")));
	}
	/**
	 * 插入多个文档
	 */
	public void insertMany() {
		MongoDatabase mdb = MongoDBConnection.getMongoDatabase();
		//创建一个集合
//		mdb.createCollection("test");
		MongoCollection<Document> coll = mdb.getCollection("test");
		Document document = new Document().append("name", "canglaoshi")
				.append("weight", "big")
				.append("age", 40);
		List<Document> list = new ArrayList<Document>();
		list.add(document);
		coll.insertMany(list);
		System.out.println("插入成功");
	}
	
	
}
