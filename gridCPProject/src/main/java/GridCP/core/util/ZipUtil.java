package GridCP.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {

	/**
	 * 解压到指定目录
	 * @param zipPath
	 * @param descDir
	 * @author shenwp
	 */
	public static List<String> unZipFiles(String zipPath,String descDir)throws IOException{
		descDir = descDir.replaceAll("\\*", "/");
		if(!descDir.endsWith("/")){
			descDir+="/";
		}
		return unZipFiles(new File(zipPath), descDir);
	}
	/**
	 * 解压文件到指定目录
	 * @param zipFile
	 * @param descDir
	 * @author shenwp
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> unZipFiles(File zipFile,String descDir)throws IOException{
		List<String> fileNames = new ArrayList<String>();
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		//zip.getEntries()
		for(Enumeration entries = zip.entries();entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry)entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
			//判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if(!file.exists()){
				file.mkdirs();
			}
			//判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if(new File(outPath).isDirectory()){
				continue;
			}
			fileNames.add(zipEntryName);
//			System.out.println(outPath);
			 //输出文件路径信息  
           // System.out.println(outPath.substring(outPath.lastIndexOf('/') + 1)); 
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			in.close();
			out.close();
		}
		//System.out.println("******************解压完毕********************");
		return fileNames;
	}
}
