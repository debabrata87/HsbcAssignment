package com.example.payment.service;


import java.util.List;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.payment.ConsoleReaderThread;
import com.example.payment.model.Balance;
import com.example.payment.model.ConsoleInput;
import com.example.payment.repository.BalanceRepo;

@Component
@EnableScheduling
public class PaymentService {

	
	@Autowired
	BalanceRepo repo;
	
	@Autowired
	ConsoleInput cs;
	
	//@Value("${balance.displayFrrquency.in.milliseconds}") 
	//String displayFrrquency;
	
	boolean t=false;
	
	
	static boolean on = true;	
	//static StringBuffer currencyAndPayment;
	String inputConsole=null;
	
	Scanner buf = null;
	
	public boolean isT() {
		return t;
	}



	public void setT(boolean t) {
		this.t = t;
	}



	//@Scheduled(fixedDelay = 30000)
	
	@Scheduled(fixedDelayString = "${balance.displayFrrquency.in.milliseconds}")
	public void display() {
		
		
		on=true;
		
		//System.out.println("Balance...."+usd);
		
		//System.out.println("Refresh Rate ...."+displayFrrquency);
			
		System.out.println("----------------Balance---------------------------------------");
		repo.findAll().forEach((e) -> {
		     if(e.getBalance()>0) { 
		    	 System.out.print(e)		    	 ;
		     
		     }
		    })		;
		
		System.out.println("----------------Balance---------------------------------------");
		
		
		buf= new Scanner(System.in).useDelimiter("\n");
		   
		ConsoleReaderThread t=new ConsoleReaderThread(buf,cs);
		t.setName("console");
		    //t.setDaemon(true);
		t.start();
		try {
				Thread.sleep(10000L);
				
				t.terminate();
			
				Thread.sleep(2000L);
				
			} catch (InterruptedException e1) {
				System.out.println(" \n Closing Terminal \n ");
		}
	    
		   
		    
		//Scanner scanner = new Scanner(System.in).useDelimiter("\n");;

	    
	    //System.out.print("Enter quit to stop or your Currency and Payment :");

	    // get their input as a String
	    //String currencyAndPayment = scanner.next();

	    //System.out.println(" \n Selection Entered  : " +(currencyAndPayment==null ?" No Input ": currencyAndPayment));
		System.out.println(" \n Selection Entered  : " +(cs.getConsoleInput()==null ?" No Input ": cs.getConsoleInput()));
	    
		
		
	    if(cs.getConsoleInput()==null) {
	    	t.getS().hasNext();
		    System.out.println(" \n No Input ");
		    
	    	return;
	    	
	    }
	    if(new String(cs.getConsoleInput()).toLowerCase().trim().equals("quit")) {
	    	
	    	System.out.println("--------------------Thank you for Banking with HSBC--------------------");

	    	this.setT(true);
	    	
	    	
	    }
	    else {
	    	 		System.out.println(" \n Processing  Payment  : " +(cs.getConsoleInput()==null ?" No Input ": cs.getConsoleInput()));
	    	 		
	 	    	    String currency =null;
				    String amount = null;
				    if(cs.getConsoleInput()!=null && !cs.getConsoleInput().equals("")) {
				    	 currency =new String(cs.getConsoleInput()).trim().split(" ",-1)[0];
				    	 amount =new String(cs.getConsoleInput()).trim().split(" ",-1)[1];
				    	 
				    	 if( (currency==null  || currency.trim().equals("")) || (amount==null  || amount.trim().equals("")) ) {
				    		 
				    		 System.out.println(" \n Invalid Input  : " +(currency==null ?" No Input ": currency) +" Payment Amount : "+amount  );
					 	     System.out.println(" \n --------------Try Again ------------------------" );
				    	 		
					 	    	
				    		 return;
				    	 }
				    	 
				    	 List<Balance> list=repo.findByCurrency(currency);
				 	    
				 	    if(list!=null && !list.isEmpty()) {
				 	    	Balance b=list.get(0);
				 	    	b.setBalance(b.getBalance()-Double.parseDouble(amount));
				 	    	repo.save(b);
				 	    	
				 	    }
				 	    else {
				 	    	System.out.println(" \n Your System is not loaded with Currecy  : " +(currency==null ?" No Input ": currency) );
				 	    	System.out.println(" \n --------------Try Again ------------------------" );
			    	 		
				 	    }
				 	    
				 	    
				 	   System.out.println("----------------Balance---------------------------------------");
						repo.findAll().forEach((e) -> {
						     if(e.getBalance()>0) { 
						    	 System.out.print(e)		    	 ;
						     
						     }
						    })		;
						
						System.out.println("----------------Balance---------------------------------------");
					
				 	    
				    }
		    	
	    }
	    
	    
	    
	    
	}
	
}
