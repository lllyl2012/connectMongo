package useMongo;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mangoJDBC.MongoDBConnection;

/**
 * 以流水线的方式，分阶段处理document。

主要阶段：

$project

对流入的每个document进行投影操作，类似于select field1, field2, ...
可以添加新字段、或删除已存在的某些字段。
 * @author toshiba2
 *
 */
public class AggregateMongo {
	MongoDatabase md = MongoDBConnection.getMongoDatabase();
	MongoCollection<Document> mc = md.getCollection("test");
	/**
	 * $project
	 * 只列出name,age,不列出_id
	 * @return
	 */
	public AggregateIterable<Document> project() {
		AggregateIterable<Document> ai = mc.aggregate(Arrays.asList(new Document("$project",new Document("name",1).append("_id", 0).append("age", 1))));
		return ai;
	}
	/**
	 * $match
	 * 类似于where
	 * @param args
	 */
	public AggregateIterable<Document> match(){
		AggregateIterable<Document> ai = mc.aggregate(//
				Arrays.asList(//
					new Document("$project",new Document("name",1).append("age",1).append("_id", 0)),
					new Document("$match",new Document("age",25))
				)
				);
		return ai;
	}
	
	/**
	 * $unwind
	 * 把数组中的元素拆分为多个document
	 * @param args
	 */
	public AggregateIterable<Document> unwind() {
		AggregateIterable<Document> ai = mc.aggregate(
				Arrays.asList(
						new Document("$project",new Document("name",1).append("age",1).append("_id", 0).append("hobby", 1)),
						new Document("$unwind","$hobby")
				));
		return ai;
	}
	
	/**
	 * $group

		类似于group by，可以使用各种聚合操作符，常用的有：
		$sum, $avg, $max, $min, $first, $last
	 * @param args
	 */
	public AggregateIterable<Document> group(){
		AggregateIterable<Document> aggregate = mc.aggregate(Arrays.asList(
	            new Document("$project", new Document("name", 1).append("_id", 0).append("hobby", 1))
	            ,new Document("$unwind", "$hobby")
	            ,new Document("$group", new Document("_id", "$hobby").append("count", new Document("$sum", 1)).append("first", new Document("$first", "$name")))
	    ));
		return aggregate;
	}
	public static void main(String[] args) {
		AggregateMongo am = new AggregateMongo();
		AggregateIterable<Document> ai = am.group();
		for (Document document : ai) {
			System.out.println(document);
		}
	}
}
