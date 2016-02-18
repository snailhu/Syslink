/**
 * 文件操作类
 */
package GridCP.core.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//import com.ice.jni.registry.NoSuchKeyException;
//import com.ice.jni.registry.NoSuchValueException;
//import com.ice.jni.registry.RegistryException;
//import com.ice.jni.registry.RegistryKey;
//import com.ice.jni.registry.Registry;

//import server.common.CaxSoftware;




/**
 * @author lvly，hanl
 * 
 */
public class FileHelper {

	/**
	 * 
	 */
	public FileHelper() {
	}

	/**
	 * 复制目录，仅复制目录下的文件，不复制目录文件夹
	 * 
	 * @param src
	 *            源文件目录
	 * @param des
	 *            目标文件目录
	 * @param create
	 *            是否创建目标文件目录
	 * @return 错误码，0=成功
	 */
	static public int copyDirectory(String src, String des, boolean create) {

		File srcDir = new File(src);
		File dstDir = new File(des);

		if (srcDir.isDirectory()) { //源路径是目录

			if (!dstDir.exists()) { //目的目录不存在

				if (create) {
					dstDir.mkdirs(); // 在目标文件目录创建新的拷贝目录
				} else {
					return 1;
				}
			}
		}

		File[] subFile = srcDir.listFiles();

		for (int i = 0; i < subFile.length; i++) {

			if (subFile[i].isDirectory()) { // 如果文件是目录 遍历

				String dst = new File(dstDir, subFile[i].getName()).getParent();

				copyDirectory(subFile[i].getAbsolutePath(),
						dst + "\\" + subFile[i].getName(), true);

			} else { // 如果是文件 直接上传

				String name = subFile[i].getName();

				InputStream in = null;
				try {
					in = new FileInputStream(subFile[i]);
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

				OutputStream out = null;
				try {
					out = new FileOutputStream(new File(dstDir, name));
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

				byte[] b = new byte[1024];

				int len;

				try {
					while ((len = in.read(b)) > 0) {
						out.write(b, 0, len);
					}
				} catch (IOException e) {

					e.printStackTrace();
				}

				try {
					in.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return 0;
	}
	
	/**
	 * 复制文件
	 * @param srcPath 源文件路径
	 * @param desPath 目标文件路径
	 * @param create true=目录目录
	 * @return
	 */
	static public int copyFile(String srcPath, String desDirectoy, boolean create){
		File srcFile = new File(srcPath);
		assert(srcFile.exists() && srcFile.isFile()); //源文件存在
		
		File desDir = new File(desDirectoy); //目的路径存在
		assert(desDir.isDirectory());
		
		// 创建目录
		if(create && !desDir.exists()){
			desDir.mkdir();
		}
		try {
			//复制文件到目的文件夹
			Files.copy(srcFile.toPath(), desDir.toPath().resolve(srcFile.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			return 1; // 复制文件失败
		}
		
		return 0;
	}

	/**
	 * 通过文件名过滤文件
	 * 
	 * @param src
	 *            源文件目录
	 * @param nameFilter
	 *            文件名过滤器
	 * @return 过滤得到的文件
	 */
	static public List<File> filterFiles(String src, FilenameFilter nameFilter) {

		List<File> filelist = new LinkedList<File>();

		File dir = new File(src);
		File[] file;
		//过滤文件
		file = dir.listFiles(nameFilter);

		for (int i = 0; i < file.length; i++) {

			//添加到list
			filelist.add(file[i]);
		}

		return filelist;
	}

	/**
	 * 通过文件后缀过滤文件
	 * 
	 * @param src
	 *            源文件目录
	 * @param extFilter
	 *            后缀过滤器
	 * @return 过滤得到的文件
	 */
	static public List<File> filterFiles(String src,
			FileNameExtensionFilter extFilter) {

		List<File> filelist = new LinkedList<File>();

		// 该函数未使用:

		// File dir = new File(src);
		File[] file;

		// file = dir.listFiles(extFilter);
		JFileChooser chooser = new JFileChooser(src);
		chooser.setFileFilter(extFilter);
		file = chooser.getSelectedFiles();
		for (int i = 0; i < file.length; i++) {

			filelist.add(file[i]);
		}

		return filelist;
	}

	/**
	 * 生成路径
	 * 
	 * @param parent
	 *            父路径
	 * @param name
	 *            名字
	 * @return 路径
	 */
	public static String makePath(String parent, String name) {

		final String trim = "\\/";

		//对parent 去掉结果的trim 对那么 去掉开头的 trim
		return StringHelper.trimEnd(parent, trim) + "/"
				+ StringHelper.trimStart(name, trim);
	}

	/**
	 * 判断文件存在
	 * @param path 文件路径
	 * @return 文件是否存在
	 */
	public static boolean fileExist(String path) {

//		Path p = Paths.get(path);
//
//		return Files.exists(p);
		return false;
	}

	/**
	 * 通过路径获取最后一个名字
	 * @param path
	 * @return
	 */
	public static String getLastFileName(String path) {
		
		//替换分隔符
		String string = path.replace("\\", "/");

		if (string.endsWith("/")) {
			//去掉最后一个"/"
			string = string.substring(0, string.lastIndexOf("/"));
		}

		//返回lastname
		return string.substring(string.lastIndexOf("/") + 1);
	}

	
	// c:\a\b\f(.txt)->c:\a\b
	/**
	 * 去掉LastName
	 * @param path 全名
	 * @return 父目录
	 */
	public static String removeLastFileName(String path) {
		//去掉结果的分隔符
		String string = StringHelper.trimEnd(path.replace("\\", "/"), "/");

		//去掉lastname
		int lastIndex = string.lastIndexOf('/');
		return path.substring(0, lastIndex);
	}

	// c:\a\b\f.txt->c:\a\b\f
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String removeFileExtension(String path) {
		int lastIndex = path.lastIndexOf('.');
		return path.substring(0, lastIndex);
	}

	/**
	 * 创建文件或目录
	 * 
	 * @param absPath
	 *            文件或目录路径
	 * @param create
	 *            当父目录不存在时是否创建目录
	 * @param directory
	 *            需要创建的是否为目录
	 * @return true=成功
	 */
	public static boolean createFile(String absPath, boolean create,
			boolean directory) {

		File file = new File(absPath);

		if (!file.exists()) { // 判断文件或目录是否已经存在，若存在返回false

			File parentFile = new File(file.getParent());

			if ((!parentFile.exists()) && create == false) {
				return false;

			} else {
				Path path = Paths.get(absPath);

				if (directory) { // 创建目录
					try {
						Files.createDirectories(path);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else { // 创建文件
					try {
						Files.createDirectories(path.getParent());

						Files.createFile(path);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 删除文件或目录
	 * 
	 * @param absPath
	 *            文件或目录路径
	 * @return true=删除成功
	 */
	public static boolean deleteFile(String absPath) {

		File file = new File(absPath);

		if (file.exists()) { //判断文件是否存在 

			if (file.isDirectory()) { //文件时目录

				File[] subFile = file.listFiles();

				for (int i = 0; i < subFile.length; i++) {

					if (subFile[i].isDirectory()) { //递归删除目录下的文件

						deleteFile(subFile[i].getAbsolutePath());
					} else {

						if (!subFile[i].delete()) {
							return false;
						}
					}
				}
				file.delete();
			} else {
				if (!file.delete()) { //删除文件
					return false;
				}
			}
		}
		return true;
	}
	
   /** 
     * 重命名文件或文件夹 
     *  
     * @param resFilePath 
     *            源文件路径 
     * @param newFileName 
     *            重命名(不含路径) 
     * @return 操作成功标识 
     */  
    public static boolean renameFile(String resFilePath, String newFileName) {  
        String newFilePath = FileHelper.makePath(
        		FileHelper.removeLastFileName(resFilePath), newFileName);  
       
        File resFile = new File(resFilePath);  
        File newFile = new File(newFilePath);  
        return resFile.renameTo(newFile);  
    }
    
    /**
     * 重命名文件或者文件夹
     * @param resFilePath
     * @param newFilePath
     * @return
     */
    public static boolean renameFile2(String resFilePath, String newFilePath){
    	File resFile = new File(resFilePath);  
        File newFile = new File(newFilePath);  
        return resFile.renameTo(newFile);  
    }
    /**
     * 检查并修改Bat文件内容: 如果Bat文件中的执行文件（带路径）存在，不用修改；否则查找注册表，获得nType的安装路径，修改Bat文件
     * @param batFilePath Bat文件的路径
     * @param type 软件类型
     * @return 是否修改成功
     * @throws IOException 
     */
//    public static boolean checkAndModifyBatFile(String batFilePath, CaxSoftware type) throws IOException{
//    	
//    	File batFile = new File(batFilePath);
//    	
//		if(!batFile.exists() || !batFile.isFile() )
//		{
//			return false;
//		}
//		
//		BufferedReader bReader = new BufferedReader(new FileReader(batFile));
//		
//		String temp = null;
//		String sBatCont = null;  //用于存储执行文件内容
//		
//		temp = bReader.readLine();		
//		
//		while(temp != null)
//		{	
//			sBatCont += temp;
//			sBatCont += "\r\n";			
//			temp = bReader.readLine();
//		}
//		
//		// 获取软件安装路径，在“”内为软件安装路径
//		String filePath = sBatCont.substring(sBatCont.indexOf("\""), sBatCont.lastIndexOf("\""));
//		
//		//检查执行文件是否存在。	如果存在，则直接退出。
//		if(FileHelper.fileExist(filePath)){
//			return true;
//		}
//		// 执行文件不存在，备份执行文件。
//		String sBakFile = batFilePath;
//		sBakFile = sBakFile.replace(".bat", ".bak");
//		FileHelper.copyFile(batFilePath, sBakFile,true);
//		
//		//软件安装路径
//		String sSoftExecuteFileFromReg="";
//		
//		switch(type){
//		case Ansys:
//			//HKEY_LOCAL_MACHINE;
//			sSoftExecuteFileFromReg = getRegKeyString("SYSTEM\\ControlSet001\\Control\\Session Manager\\Environment", "ANSYS120_DIR");
//			if (sSoftExecuteFileFromReg!="")
//			{
//				
//			}
//			break;
//		case Nastran:
//			//nastran 2010版本;\\HKEY_CURRENT_USER,HKEY_LOCAL_MACHINE;
//			sSoftExecuteFileFromReg = getRegKeyString("Software\\MSC.Software Corporation\\MD Nastran\\10.1.0\\Shortcuts\\MD Nastran 2010.1", "Command");
//			break;
//		case Adams:
//			//Adams 19.1.0(2010)版本;HKEY_CURRENT_USER;HKEY_LOCAL_MACHINE;
//			sSoftExecuteFileFromReg = getRegKeyString("SOFTWARE\\MSC.Software Corporation\\MD Adams\\19.1.0\\Shortcuts\\Adams - Command", "Command");
//			break;
//		case Fluent:
//			//fluent 6.3版本;HKEY_CURRENT_USER
//			sSoftExecuteFileFromReg = getRegKeyString("Software\\Fluent Inc Products\\Fluent", "");
//			break;
//		case ABAQUS:
//		case LS_DYNA:
//		case Excel:
//		default:
//			break;
//		}
//		if (!sSoftExecuteFileFromReg.isEmpty())  
//		{
//			int pos=sSoftExecuteFileFromReg.indexOf("\"");
//			if(pos != -1){
//				sSoftExecuteFileFromReg =sSoftExecuteFileFromReg.substring(1,sSoftExecuteFileFromReg.length()-2);
//			}
//		}else {
//			return false;
//		}
//		
//		// 替换执行文件路径
//		sBatCont = sBatCont.replace(filePath, sSoftExecuteFileFromReg);
//		
//		OutputStream out = new FileOutputStream(batFilePath);
//		byte []b = sBatCont.getBytes();
//		out.write(b);
//		out.close();
//    	return true;
//    }
    
    /**
     * 从注册表中获取软件的安装路径
     * @param sPath
     * @param sKeyName 
     * @return 软件安装路径
     */
//    public static String getRegKeyString(String sPath, String sKeyName){
    	//RegistryKey  softWare = null;
    	//String softWareValue = "";
//    	try {
//			softWare = Registry.HKEY_LOCAL_MACHINE.openSubKey(sPath);
//		} catch (NoSuchKeyException e) {
//			try {
//			softWare = Registry.HKEY_CURRENT_USER.openSubKey(sPath);
//			} catch (NoSuchKeyException e1) {
////				e1.printStackTrace();
//			} catch (RegistryException e1) {
//				e1.printStackTrace();
//			}
////			e.printStackTrace();
//		} catch (RegistryException e) {
//			e.printStackTrace();
//		}
//    	
//    	if(softWare != null){
//    		try {
//				softWareValue = softWare.getStringValue(sKeyName);
//			} catch (NoSuchValueException e) {
//				e.printStackTrace();
//			} catch (RegistryException e) {
//				e.printStackTrace();
//			}
//    	}
    	 
    	//return softWareValue;   	
//    }

} // endclass
