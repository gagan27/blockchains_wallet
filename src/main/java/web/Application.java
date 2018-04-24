package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	
for(int i=0;i<args.length;i++)
	System.out.println("hello"+args[i]);
        SpringApplication.run(Application.class, args);
        
    }
}