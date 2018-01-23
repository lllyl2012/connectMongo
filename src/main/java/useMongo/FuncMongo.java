package useMongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import mangoJDBC.MongoDBConnection;

public class FuncMongo {
	MongoDatabase mdb = MongoDBConnection.getMongoDatabase();
	MongoCollection<Document> mc = mdb.getCollection("test");
	/**
	 * 统计总数
	 * @return
	 */
	public Long count() {
		Long num = mc.count();
		return num;
	}
	
	/**
	 * sort排序
	 * 升序，降序，一个升序一个降序
	 * @param args
	 */
	public FindIterable<Document> asc() {
		FindIterable<Document> fi = mc.find().sort(Sorts.ascending("age"));
		return fi;
	}
	public FindIterable<Document> desc() {
		FindIterable<Document> fi = mc.find().sort(Sorts.descending("name"));
		return fi;
	}
	public FindIterable<Document> ascAndDesc() {
		FindIterable<Document> fi = mc.find().sort(Sorts.orderBy(Sorts.descending("age"),Sorts.ascending("name")));
		return fi;
	}
	
	/**
	 * skipe && limit
	 * 跳过前五条，显示五条
	 * @param args
	 */
	public FindIterable<Document> skipeAndLimit(){
		FindIterable<Document> fi = mc.find().sort(Sorts.descending("age")).skip(1).limit(2);
		return fi;
	}
	/**
	 * distinct
	 * 唯一性
	 * @param args
	 */
	public DistinctIterable<String> distinct(){
		DistinctIterable<String> di = mc.distinct("name", String.class);
		for (String string : di) {
			System.out.println(string);
		}
		return di;
	}
	

	public static void main(String[] args) {
		FuncMongo fm = new FuncMongo();
		fm.distinct();
		FindIterable<Document> list = fm.skipeAndLimit();
//		for (Document document : list) {
//			System.out.println(document);
//		}
	}
}
