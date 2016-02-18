package GridCP.svn;

import java.io.File;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SvnTest {
	
	 private static SVNClientManager ourClientManager;
	 
	 public static void main(String[] args) throws SVNException {
		 
//		 FSRepositoryFactory.setup();
		 SVNRepositoryFactoryImpl.setup();
		 
		 SVNURL repositoryURL = null;
	        try {
	            repositoryURL = SVNURL.parseURIEncoded("svn://192.168.0.167/test/trunk");
	        } catch (SVNException e) {
	            //
	        }	        
	        String name = "huyajun";
			//版本库的用户名密码
	        String password = "huyajun";
			//工作副本目录
//	        String myWorkingCopyPath = "B:/MyWorkingCopy6";
	        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
	        ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)options, name, password);
	    	File updateFile=new File("B:/svndemo/123/trunk/11.txt");
			//获得updateClient的实例
			SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
			updateClient.setIgnoreExternals(false);
			//执行更新操作
			long versionNum= updateClient.doUpdate(updateFile, SVNRevision.HEAD, SVNDepth.INFINITY,false,false);
			System.out.println("工作副本更新后的版本："+versionNum);
			
	        
//	        File wcDir = new File(myWorkingCopyPath);
//	        if (wcDir.exists()) {
//	           System.err.print("the destination directory '"+ wcDir.getAbsolutePath() + "' already exists!");
//	        }
//	        wcDir.mkdirs();
//	        try {
//	            /*
//	             * 递归的把工作副本从repositoryURL check out 到 wcDir目录。
//	             * SVNRevision.HEAD 意味着把最新的版本checked out出来。 
//	             */
//	           
//				SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
//	            updateClient.setIgnoreExternals(false);
////	            updateClient.doCheckout(repositoryURL,wcDir,SVNRevision.HEAD, SVNRevision.HEAD, true);
//	            updateClient.doCheckout(repositoryURL, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);
//
//	        } catch (SVNException svne) {
//	            //
//	        }
	 }
}
