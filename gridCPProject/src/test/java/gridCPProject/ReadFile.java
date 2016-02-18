package gridCPProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class ReadFile {	
	public static String readFromFile(File src) {
        try {
            @SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
            StringBuilder stringBuilder = new StringBuilder(1024);
//            String content2="";  
            String content;
            while((content = bufferedReader.readLine() )!=null){
//            	System.out.println(content);
            	if(content.length()>0)
            		stringBuilder.append(content);
//                System.out.println(stringBuilder.toString());
//            	content2=content2+content+"\r\n";
            }
            return stringBuilder.toString();
//            return content2;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();           
            return null;
            
        }
    } 
	
	public static void main(String args[]){
		File src = new File("B:/package.mo");
		String content_string = readFromFile(src); 
		int a = content_string.indexOf("package");
//		System.out.print(a);
		int end = content_string.indexOf('"', a+7);
//		System.out.print(content_string.indexOf('"', a+7));
		String endString = content_string.substring(a+7, end).trim();
//		System.out.print(content_string..indexOf("end "+endString));
		System.out.print(content_string.substring(a+7, end).trim());
//		System.out.print(readFromFile(src));
		
		
		
	}
}
