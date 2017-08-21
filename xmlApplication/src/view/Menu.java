package view;

import java.util.Scanner;

import controller.Counter;
import controller.FileThreads;
import controller.Files;
import controller.FileFunc;



public class Menu {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		
		//declerations
	    Scanner scan=new Scanner(System.in);
	    int selection;
	    
	//initialize files info filename-file number-# of number in every file
     Files File=new Files("file",10,100);
     
     //initialize FileFunc
     FileFunc Cfile=new FileFunc(File);
     
     //initialize Counter as a thread
     Counter cnt=new Counter(Cfile,File);
     Thread trd=new Thread(cnt);
     
     
     
     
    
    
     
     // create xml file using thread
     for(int i=1;i<=File.getFileNumber();i++){
			
		   FileThreads Fthreads=new FileThreads();
		   Fthreads.SetVar(File, i);    //burda gereksiz yere sürekli File gönderiliyor. Düzelt bunu sonra  unutma
			Thread t=new Thread(Fthreads);
			t.start();
		}
    
    	
    	System.out.println("----Xml files succesfuly created----");
    	
    		System.out.println("for selection sort -->1!");
    		System.out.println("for Bubble sort    -->2!");
    		System.out.println("for Ýnsertion sort -->3!");
    		System.out.println("Choose : ");
			
    		selection=scan.nextInt();
    		scan.close();
    		
    		
    		if(selection==1||selection==2||selection==3){
    			
    			
    			if(Cfile.Sort(selection)){
    				System.out.println("----Xml files are succesfuly sorted----");
    				
    				trd.start();
    				System.out.println("----Counter.xml files are succesfuly created!----");
    				
    				
    				
    			}
    			else{
    				System.out.println("----Error occurupted while Xml files  sorting !----");
    			} //end else Sfile.sort
    		}else{
    			System.out.println("Please Enter Correct input ! Try again!");
    		
    		} //end if selection control
    			
   
    
	}//end main

}
