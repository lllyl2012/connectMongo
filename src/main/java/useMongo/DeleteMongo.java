package useMongo;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import mangoJDBC.MongoDBConnection;

public class DeleteMongo {
	public static void main(String[] args) {
		DeleteMongo dm = new DeleteMongo();
		dm.delete();
	}
	public void delete() {
		MongoDatabase mdb = MongoDBConnection.getMongoDatabase();
		MongoCollection<Document> coll = mdb.getCollection("test");
		System.out.println("获取集合成功");
		//删除一个
//		coll.deleteOne(Filters.eq("age", 25));
		//删除多个
		coll.deleteMany(Filters.eq("age", 25));
		System.out.println("删除成功");
		FindIterable<Document> fi = coll.find();
		MongoCursor<Document> cursor = fi.iterator();
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
