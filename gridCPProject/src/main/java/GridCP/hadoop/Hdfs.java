//package GridCP.hadoop;
//
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.junit.Test;
////import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
//import org.tmatesoft.svn.core.wc.SVNUpdateClient;
//import org.tmatesoft.svn.core.auth.SVNAuthentication;
//
//public class Hdfs {
//
//	static final String ROOT ="hdfs://192.168.0.176:9000/" ;
//	@Test
//	public void test() throws IOException, InterruptedException, URISyntaxException {
//		Configuration con = new  Configuration();
//		con.set("fs.defaultFS", "hdfs://192.168.0.176:9000/");
//		FileSystem fs = FileSystem.get(new URI(ROOT), con,"root");
//		Path srcPath = new Path("c:/test.txt");
//	    Path tarPath = new Path(ROOT+"data/test.txt");
//	    fs.copyFromLocalFile(srcPath, tarPath);
////	    ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)options, name, password);
////	    SVNDiffClient
////	    SVNUpdateClient
//	}
//
//}
