package mangoJDBC;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
/**
 * ����Ҫ��������ӷ�ʽ
 * @author toshiba2
 *
 */
public class MongoDBConnection {
	private static MongoClient mc;
	private static MongoDatabase db;
	static {
		try {
			//���ӵ�mongo����
			mc = new MongoClient("localhost",27017);
			//���ӵ�mongo���ݿ�
			db = mc.getDatabase("jdbc");
			System.out.println("�����ӵ����ݿ⣺"+db);
		}catch(Exception e) {
			System.out.println("����mongodbʧ��");
		}
	}
	/**
	 * ����mongodb���ݿ�
	 * @return
	 */
	public static MongoDatabase getMongoDatabase() {
		return db;
	}
	/**
	 * �ر�mongodb����
	 */
	public static void closeMongoClient() {
		if(mc!=null) {
			mc.close();
		}
	}
}
