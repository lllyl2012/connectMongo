package useMongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateManyModel;

import mangoJDBC.MongoDBConnection;

public class UpdateMonge{
	public static void main(String[] args) {
		UpdateMonge um = new UpdateMonge();
		um.updateMany();
	}
   public void updateMany(){
      try{   
         // ���ӵ����ݿ�
         MongoDatabase mongoDatabase =MongoDBConnection.getMongoDatabase();
         System.out.println("Connect to database successfully");
         
         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
         System.out.println("���� test ѡ��ɹ�");
         
         //�����ĵ�   ���ĵ���likes=100���ĵ��޸�Ϊlikes=200   
         collection.updateMany(Filters.eq("age", 30), new Document("$set",new Document("age",35).append("name", "gaga")));
//         collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
         System.out.println("���³ɹ�");
         //�����鿴���  
         FindIterable<Document> findIterable = collection.find();  
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
         while(mongoCursor.hasNext()){  
            System.out.println(mongoCursor.next());  
         }  
      
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}