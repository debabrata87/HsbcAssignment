package com.example.payment.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	boolean shutdown=false;
	
	
	String inputConsole=null;
	
	//Scanner buf = null;
	BufferedReader buf=null;
	
	public PaymentService() {
		
		System.out.println("PaymentService Instantiated");
		buf= new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	public boolean isShutdown() {
		return shutdown;
	}



	public void setShutdown(boolean t) {
		
		this.shutdown = t;
		try {
			buf.close();
		} catch (IOException e) {
			System.out.println("Reader closed...."+e);
		}
	}


	@Scheduled(fixedDelayString = "${balance.displayFrrquency.in.milliseconds}")
	public void display() {
		

			
		System.out.println("----------------Balance---------------------------------------");
		repo.findAll().forEach((e) -> {
		     if(e.getBalance()>0) { 
		    	 System.out.print(e)		    	 ;
		     
		     }
		    })		;
		
		System.out.println("----------------Balance---------------------------------------");
		
		
		   
		ConsoleReaderThread t=new ConsoleReaderThread(buf,cs);
		t.setName("console");
		    //t.setDaemon(true);
		t.start();
		try {
				Thread.sleep(10000L);
				
				t.terminate();
			
				Thread.sleep(2000L);
				
			} catch (InterruptedException e1) {
				System.out.println("---------Closing Terminal--------");
		}
	    
		System.out.println("Selection Entered  : " +(cs.getConsoleInput()==null ?" No Input ": cs.getConsoleInput()));
	    
		
		
	    if(cs.getConsoleInput()==null) {
	    	 
	    	return;
	    	
	    }
	    if(new String(cs.getConsoleInput()).toLowerCase().trim().equals("quit")) {
	    	
	    	System.out.println("--------------------Thank you for Banking with HSBC--------------------");

	    	this.setShutdown(true);
	    	
	    }
	    else {
	    	 		System.out.println("Processing Payment :" +(cs.getConsoleInput()==null ?" No Input ": cs.getConsoleInput()));
	    	 		
	 	    	    String currency =null;
				    String amount = null;
				    if(cs.getConsoleInput()!=null && !new String(cs.getConsoleInput()).equals("")) {
				    	 currency =new String(cs.getConsoleInput()).trim().split(" ",-1)[0];
				    	 amount =new String(cs.getConsoleInput()).trim().split(" ",-1)[1];
				    	 
				    	 if( (currency==null  || currency.trim().equals("")) || (amount==null  || amount.trim().equals("")) || !isNumeric(amount)) {
				    		 
				    		 System.out.println(" \n Invalid Input  : " +(currency==null ?" No Input ": currency) +" Payment Amount : "+amount  );
					 	     System.out.println(" \n --------------Try Again ------------------------" );
				    	 		
					 	    	
				    		 return;
				    	 }
				    	 
				    	 List<Balance> list=repo.findByCurrency(currency);
				 	    
				 	    if(list!=null && !list.isEmpty()) {
				 	    	Balance b=list.get(0);
				 	    	if(b.getBalance()-Double.parseDouble(amount) >= 0) {
				 	    		b.setBalance(b.getBalance()-Double.parseDouble(amount));
				 	    		repo.save(b);
				 	    	}
				 	    	else {
				 	    		System.out.println("\n Credit Balance Insufficient   : " +(currency==null ?" No Input ": currency) +" Balance : "+ b.getBalance() + " Payment : " +amount);
					 	    	System.out.println("\n --------------Try Again ------------------------" );
				    	 		
				 	    		
				 	    	}
				 	    	
				 	    	
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
	
	
	public boolean isNumeric(String amount) {
		try {
			Double.parseDouble(amount);
			//System.out.println("Amount " + amount);

			return true;
		} catch (NumberFormatException e) {
			System.out.println("Amount non numeric " + amount);

			return false;
		}
	}
	
}
