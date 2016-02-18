package GridCP.svn;

import java.io.File;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SvnSwitch {
//	声明SVN客户端管理类
	private static SVNClientManager ourClientManager;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		//初始化支持svn://协议的库。 必须先执行此操作。
		SVNRepositoryFactoryImpl.setup();
		//相关变量赋值
		SVNURL repositoryURL = null;
		SVNURL repositoryURLDes = null;
		try {
			repositoryURL = SVNURL.parseURIEncoded("svn://192.168.0.167/test/trunk");
			repositoryURLDes = SVNURL.parseURIEncoded("svn://192.168.0.167/test/brunch/11.txt");
		} catch (SVNException e) {
			//
		}
		String name = "test";
		String password = "test";
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		//实例化客户端管理类
		ourClientManager = SVNClientManager.newInstance(
				(DefaultSVNOptions) options, name, password);
		//要提交的文件
		File commitFile=new File("B:/svndemo/user/trunk/11.txt");
		//获取此文件的状态（是文件做了修改还是新添加的文件？）
		SVNUpdateClient updateClient=ourClientManager.getUpdateClient();


		updateClient.doSwitch(commitFile, repositoryURL, SVNRevision.HEAD, true);
		System.out.println("123");
				
	}
}
