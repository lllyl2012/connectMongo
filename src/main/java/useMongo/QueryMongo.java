package useMongo;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import mangoJDBC.MongoDBConnection;

public class QueryMongo {
	MongoDatabase mdb = MongoDBConnection.getMongoDatabase();
	MongoCollection<Document> mc = mdb.getCollection("test");;
	
	/**
	 * 遍历所有文档
	 */
	public void iteratorAllDocument() {
		   //检索所有文档  
        /** 
        * 1. 获取迭代器FindIterable<Document> 
        * 2. 获取游标MongoCursor<Document> 
        * 3. 通过游标遍历检索出的文档集合 
        * */  
		FindIterable<Document> fi = mc.find();
		//游标迭代
//		MongoCursor<Document> mongoCursor = fi.iterator();
//		while(mongoCursor.hasNext()) {
//			System.out.println(mongoCursor.next());
//		}
		//普通迭代
//		for (Document document : fi) {
//			System.out.println(document);
//		}
		for (Document document : fi) {
			System.out.println(document.toJson());
		}
	}
	
	/**
	 * eq
	 */
	public void filterEq() {
		FindIterable<Document> fi = mc.find(Filters.eq("name", "monaka"));
	}
	/**
	 * 使用运算符“$lt”,"$gt","$lte","$gte"
	 */
	public void operator() {
		Document doc = new Document("age",new Document("$lt",37));
		FindIterable<Document> fi = mc.find(doc);
		for (Document document : fi) {
			System.out.println(document);
		}
	}
	public void Filters() {
		FindIterable<Document> doc = mc.find(Filters.lte("age", 35));
	}
	/**
	 * 使用and连接
	 */
	public void operatorAnd() {
		Document doc = new Document("age",new Document("$lt",37)).append("name", "monaka");
		FindIterable<Document> fi = mc.find(doc);
		for (Document document : fi) {
			System.out.println(document);
		}
	}
	public void FilterAnd() {
		FindIterable<Document> fi = mc.find(Filters.and(Filters.eq("name", "monaka"),Filters.ne("age", 35)));
		for (Document document : fi) {
			System.out.println(document);
		}
	}
	/**
	 * in
	 */
	public void filterIn() {
		FindIterable<Document> fi = mc.find(Filters.in("name", Arrays.asList("monaka","canglaoshi")));
	}
	/**
	 * 使用or连接
	 */
	public void operatorOr() {
		Document doc = new Document("$or",Arrays.asList(new Document("name","monaka"),new Document("name","canglaoshi")));
		FindIterable<Document> fi = mc.find(doc);
		for (Document document : fi) {
			System.out.println(document);
		}
	}
	public void FilterOr() {
		FindIterable<Document> fi = mc.find(Filters.or(Filters.eq("age", 25),Filters.eq("age", 35)));
	}
	/**
	 * between..and
	 * @param args
	 */
	public void operatorBetweenAnd() {
		Document doc = new Document("age",new Document("$lt",37).append("$gt", 26));
		FindIterable<Document> fi = mc.find(doc);
		for (Document document : fi) {
			System.out.println(document);
		}
	}
	
	public static void main(String[] args) {
		QueryMongo qm = new QueryMongo();
		qm.FilterAnd();
	}
}
