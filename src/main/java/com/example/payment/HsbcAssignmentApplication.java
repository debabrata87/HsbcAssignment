package com.example.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.payment.service.PaymentService;

@SpringBootApplication
public class HsbcAssignmentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx= SpringApplication.run(HsbcAssignmentApplication.class, args);
		
		System.out.println("----------------Welcome to HSBC -------------------------");
		System.out.println("---------------------------------------------------------");
		
		
		PaymentService ps=ctx.getBean(PaymentService.class);
		
		
		
		while (true) {
			
			
			
			if(ps.isT()){
				//ctx.close();
				//System.exit(1);
				//int exitCode=SpringApplication.exit(ctx, ()->0);
				
				//System.exit(exitCode);
				System.out.println("--------------------Shutting Down Application --------------------");

		    	
				System.exit(SpringApplication.exit(ctx, ()->-1 ));
			}
			else {
				//System.out.println("--------------------Not Shut Down--------------------"+ps.isT());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	

}
