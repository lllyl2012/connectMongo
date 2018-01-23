package mangoJDBC;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
/**
 * 不需要密码的连接方式
 * @author toshiba2
 *
 */
public class MongoDBConnection {
	private static MongoClient mc;
	private static MongoDatabase db;
	static {
		try {
			//链接到mongo服务
			mc = new MongoClient("localhost",27017);
			//链接到mongo数据库
			db = mc.getDatabase("jdbc");
			System.out.println("已链接到数据库："+db);
		}catch(Exception e) {
			System.out.println("连接mongodb失败");
		}
	}
	/**
	 * 连接mongodb数据库
	 * @return
	 */
	public static MongoDatabase getMongoDatabase() {
		return db;
	}
	/**
	 * 关闭mongodb服务
	 */
	public static void closeMongoClient() {
		if(mc!=null) {
			mc.close();
		}
	}
}
