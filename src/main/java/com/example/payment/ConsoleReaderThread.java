package com.example.payment;


import java.util.Scanner;

import com.example.payment.model.ConsoleInput;


public class ConsoleReaderThread extends Thread {

	Scanner s;
	ConsoleInput cs;
	
	private volatile boolean alive = true;
	
	
	public Scanner getS() {
		return s;
	}

	public void setS(Scanner s) {
		this.s = s;
	}

	public ConsoleInput getCs() {
		return cs;
	}

	public void setCs(ConsoleInput cs) {
		this.cs = cs;
	}

	public ConsoleReaderThread(Scanner s,ConsoleInput cs) {
		this.s=s;
		this.cs=cs;
	}
	
	public ConsoleReaderThread() {
		this.s=null;
		this.cs=null;
	}
	public void terminate() {
		
		System.out.println("\n Thread: Terminate ");
		
		
        this.alive = false;
        
        
        
    }
	
	public void run() {
		
		s = new Scanner(System.in); 
        
		while(alive) {
		      cs.setConsoleInput(null);
		      //PaymentService.currencyAndPayment=null;
	     
	    	  
	          
		    
	          System.out.print("Enter quit to stop or your Currency and Payment :");
	          
	          String input=null;
	          //s = new Scanner(System.in).useDelimiter("\n"); 
	         
	          //s = new Scanner(System.in);
	          input=s.nextLine();
	        
	       
	          
	         
	          //BufferedReader buf = new BufferedReader (new InputStreamReader (System.in));
	           
	          //String input=s.nextLine();
			
	          System.out.print("\n Enter  :"+(input!=null?input : "default ") );
	          
	          
	          if (input == null || input.equals("")) {
	        	cs.setConsoleInput(null);
	        	//cs.setConsoleInput(new StringBuffer("quit"));
	        	
	            
	          }
	          if ("quit".equalsIgnoreCase(input.trim())) {
	        	 //PaymentService.currencyAndPayment=new StringBuffer("quit");
	        	  
	        	 cs.setConsoleInput(new StringBuffer("quit"));
		        	alive=false;
	          }
	          else {
	        	  //PaymentService.currencyAndPayment=new StringBuffer(input);
	        	  
	        	  cs.setConsoleInput(new StringBuffer(input));
		          alive =false;
	          }
	       
		}
	}
}
