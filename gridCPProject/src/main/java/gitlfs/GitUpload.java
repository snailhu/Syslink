package gitlfs;

import java.io.File;
import java.io.IOException;
//import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import ru.bozaro.gitlfs.client.AuthHelper;
import ru.bozaro.gitlfs.client.BatchUploader;
import ru.bozaro.gitlfs.client.Client;
import ru.bozaro.gitlfs.client.auth.AuthProvider;
import ru.bozaro.gitlfs.client.io.FileStreamProvider;
import ru.bozaro.gitlfs.client.io.StreamProvider;
import ru.bozaro.gitlfs.common.data.Meta;

public class GitUpload {
	
	public static void gitUpload(){
		
		 try{
			 final AuthProvider auth = AuthHelper.create("root@192.168.0.137:/root/repository_git/snail.git");
			 final Client client = new Client(auth);
			 client.putObject( new FileStreamProvider(new File("C:\\Users\\Snail\\Documents\\GitHub\\test10\\snail\\activiti.zip")));
//			 final ExecutorService pool = Executors.newFixedThreadPool(4);
//			 final BatchUploader uploader = new BatchUploader(client, pool);
//			 CompletableFuture<Meta> future = uploader.upload(new FileStreamProvider(new File("c:/test.txt")));
			 System.out.println("test");
//			 try {
////				String oid = future.get().getOid();
////				System.out.println(oid);
//				System.out.println("123");
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) { 
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		 }catch(IOException e){
			 e.printStackTrace();
		 }
	}
	
	public static void main(String args[]){
		
		gitUpload();
		
	}
	
	
	
			

}
