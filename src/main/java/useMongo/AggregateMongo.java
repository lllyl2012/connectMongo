package useMongo;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mangoJDBC.MongoDBConnection;

/**
 * ����ˮ�ߵķ�ʽ���ֽ׶δ���document��

��Ҫ�׶Σ�

$project

�������ÿ��document����ͶӰ������������select field1, field2, ...
����������ֶΡ���ɾ���Ѵ��ڵ�ĳЩ�ֶΡ�
 * @author toshiba2
 *
 */
public class AggregateMongo {
	MongoDatabase md = MongoDBConnection.getMongoDatabase();
	MongoCollection<Document> mc = md.getCollection("test");
	/**
	 * $project
	 * ֻ�г�name,age,���г�_id
	 * @return
	 */
	public AggregateIterable<Document> project() {
		AggregateIterable<Document> ai = mc.aggregate(Arrays.asList(new Document("$project",new Document("name",1).append("_id", 0).append("age", 1))));
		return ai;
	}
	/**
	 * $match
	 * ������where
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
	 * �������е�Ԫ�ز��Ϊ���document
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

		������group by������ʹ�ø��־ۺϲ����������õ��У�
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
