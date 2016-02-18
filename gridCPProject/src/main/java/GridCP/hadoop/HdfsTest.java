//package GridCP.hadoop;
//
//import java.net.URI;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//
//
//
//public class HdfsTest {
//	static final String ROOT = "hdfs://192.168.0.176:9000/";
//    static final Configuration config= new Configuration();
//       
//        /**
//     * 创建文件夹
//     * @param path    相对路径。如"/test/"
//     * @return 是否创建成功
//     * @throws Exception
//     */
//    public static boolean Mkdir(String path) throws Exception {
//        FileSystem fileSystem = getFileSystem();
//        boolean isCreate = fileSystem.mkdirs(new Path(path));
//        return isCreate;
//    }
//    
//    public  static FileSystem getFileSystem() throws Exception{
//        return FileSystem.get(new URI(ROOT), config,"hadoop");
////        return FileSystem.get(config);
//    }
//     
//    public static boolean Removedir(String path) {
//        boolean result = false;
//        Path strPath = new Path(path);
//        try {
//            FileSystem fileSystem = getFileSystem();
//            if(fileSystem.isDirectory(strPath)){
//                result = fileSystem.delete(strPath);
//            }
//            else{
//                throw new NullPointerException("逻辑异常：删除文件夹属性出错，未能删除文件夹，如果删除文件应使用deleteFile(String path)");
//            }
//        } catch (Exception e) {
//            System.out.println("异常：" + e.getMessage());
//        }
//        return result;
//    }
//    
//    /**
//     * 删除文件
//     * @param path    文件路径
//     * @return    是否成功删除
//     */
//    public static boolean deleteFile(String path) {
//        boolean result = false;
//        Path strPath = new Path(path);
//        try {
//            FileSystem fileSystem = getFileSystem();
//            if(fileSystem.isFile(strPath)){
//                result = fileSystem.delete(strPath);
//            }
//            else{
//                throw new NullPointerException("逻辑异常：删除文件属性出错，未能删除文件，如果删除文件夹应使用Removedir(String path)");
//            }
//        } catch (Exception e) {
//            System.out.println("异常：" + e.getMessage());
//        }
//        return result;
//    }
//    
//    /**
//     * 上传文件
//     * @param src    本地磁盘文件
//     * @param tar    上传文件至HDFS路径
//     * @return        是否成功上传
//     */
//    public static boolean putFile(String src,String tar) {
//        boolean result = false;
//        Path srcPath = new Path(src);
//        Path tarPath = new Path(tar);
//        try {
//            FileSystem fileSystem = getFileSystem();
//            fileSystem.copyFromLocalFile(srcPath, tarPath);
//            result = true;
//        } catch (Exception e) {
//            System.out.println("异常：" + e.getMessage());
//        }
//        return result;
//    	
//    }
//    
//    /**
//     * 下载文件至本地磁盘
//     * @param path    HDFS路径
//     * @return        返回本地磁盘路径
//     */
//    public static String DownFile(String path){    
//        int index = path.lastIndexOf('.') > 0 ? path.lastIndexOf('.') : 0;
//        String name = path.substring(index);
//        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
//        String target =String.format("{0}\\{1}{2}", System.getProperty("user.dir"),format.format(new Date()), name);
//        Path strPath = new Path(path);
//        Path tarPath = new Path(target);
//        try {
//            FileSystem fileSystem = getFileSystem();
//            fileSystem.copyToLocalFile(strPath, tarPath);
//        } catch (Exception e) {
//            System.out.println("异常：" + e.getMessage());
//        }
//        return target;
//    }
//    
//    
//    public static void main(String[] args) throws Exception {        
//    	putFile("c:/test.txt",ROOT+"data/test.txt");
//        
//    }
//}
//
