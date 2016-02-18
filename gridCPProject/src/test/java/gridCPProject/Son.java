package gridCPProject;

import GridCP.util.ResourcesUtil;


//BeanDefinition 
//import org.sonar.api.config.PropertyDefinition
public class Son extends Person {	
	public void eat(){
		super.eat();
		System.out.println("son eat somthing");
	}
	public void sing(){
		System.out.println("son sing ");
		
	}
	
	
	public static void main(String args[]){
//		Person person =new Son ();
//		person.eat();
		String message = ResourcesUtil.getValue("messages", 101+"");
		System.out.println(message);
	}
}
