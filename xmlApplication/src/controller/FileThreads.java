package controller;

import java.io.File;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileThreads  extends Thread{
	
	Files fl;
	int index;
	
	
	public void SetVar(Files fl,int index){
		
		this.fl=fl;
		this.index=index;
	}

	public void run() {
		// TODO Auto-generated method stub
try{
			
			//declerations
		      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		    
		    	  
		    	   
			      //create new document
		    	  //create start element/root element
		    	  
			      Document doc = docBuilder.newDocument();
			      Element rootElement = doc.createElement("SayiList");   // <SayiList></SayiList>
			      doc.appendChild(rootElement);
			      
			      // to write contents into xml file 
			      
			      TransformerFactory transformerFactory = TransformerFactory.newInstance();
			      Transformer transformer = transformerFactory.newTransformer();
			    
			      
			      //write contents in xml files
		    	 for(int j=1;j<=fl.getNumberInFile();j++){
		    		 
		    		//create child element
		    		 Element staff = doc.createElement("Sayi"); 
				     Attr attr = doc.createAttribute("index"); 
				     attr.setValue(Integer.toString(j));			//for index="j"
				      
		    		 //to be different every value in xml files, these variables/code must create again in every loop
		 		     Random Rnd=new Random();
		 		     int sayi=Rnd.nextInt(100); //create random value for sayi
		 		    
		 		     //set  innertext for Sayi child
				     staff.appendChild(doc.createTextNode(Integer.toString(sayi)));
				     staff.setAttributeNode(attr);
				     rootElement.appendChild(staff);
				
		    	 }  //end for j
		    	  
		    	 
		    	   //save as a  xml file 
		    	    DOMSource source = new DOMSource(doc);
			        StreamResult result = new StreamResult(new File("file"+index+".xml"));
			        transformer.transform(source, result);
			        
		}catch(Exception e){
			
			System.out.println("Error occurupted while creating xml files!");
		} //end catch
		
	
		super.run();
		
	}  //end run function
	
} //end class
