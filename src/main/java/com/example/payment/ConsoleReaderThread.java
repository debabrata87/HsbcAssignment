package com.example.payment;


import java.io.BufferedReader;
import java.io.IOException;


import com.example.payment.model.ConsoleInput;


public class ConsoleReaderThread extends Thread {

	//Scanner s;
	
	BufferedReader reader;
	
	ConsoleInput cs;
	
	private volatile boolean alive = true;
	
	
	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public ConsoleInput getCs() {
		return cs;
	}

	public void setCs(ConsoleInput cs) {
		this.cs = cs;
	}

	public ConsoleReaderThread(BufferedReader s,ConsoleInput cs) {
		this.reader=s;
		this.cs=cs;
	}
	
	public ConsoleReaderThread() {
		this.reader=null;
		this.cs=null;
	}
	public void terminate() {
		
		//System.out.println("Thread Terminate ");
		this.alive = false;
           
    }
	
	public void run() {
		
		
		while(alive) {
		      cs.setConsoleInput(null);
		      //PaymentService.currencyAndPayment=null;
	     
	    	  
	          
		    
	          System.out.print("Enter quit to stop or your Currency and Payment :");
	          
	          String input=null;
	          //s = new Scanner(System.in).useDelimiter("\n"); 
	         
	          //s = new Scanner(System.in);
	          try {
				input=reader.readLine();
			} catch (IOException e) {
				
			}
	        
	       
	          
	         
	          //BufferedReader buf = new BufferedReader (new InputStreamReader (System.in));
	           
	          //String input=s.nextLine();
			
	          System.out.println("User Entered : "+(input!=null?input : "default ") );
	          
	          
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
