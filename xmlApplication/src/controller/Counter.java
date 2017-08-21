
package controller;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Counter  extends Thread{

	
	FileFunc func;
	Files file;
	int[] temp;
	int[] tempTwo;
	int count;
	
	
	//constructor
	public Counter(FileFunc func,Files fl){
		
		//initialize
		this.func=func;
		this.file=fl;
		
		tempTwo=null;
		temp=null;
		count=0;
		
	} //end constructor
	

	
//  count  repeating number 
	
	public void run() {
		// TODO Auto-generated method stub
		try{
			
			
			 //declerations to create new xml file after counting
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			
			
			//create  new xml file and  rewrite  number with counter  into  xml file
			//create start element
			 Document doc = docBuilder.newDocument();
			 Element rootElement = doc.createElement("CounterList"); 
			 doc.appendChild(rootElement);
			 
			 // write contents into xml file 
			 TransformerFactory transformerFactory = TransformerFactory.newInstance();
			 Transformer transformer = transformerFactory.newTransformer();

			
			 
			 //numbers of all xml file are converted to array 
			 //and storaged by temp array
			 temp=func.AllxmlFileToArray(file, tempTwo);
			 
			 
			 for(int j = 0; j < temp.length; j++)
			   {
			       for(int k=0;k< temp.length-1; k++)
			       {

			           if (temp[j] == temp[k+1])
			           {
			               count++;
			           } //end for k

			          
			       } //end  for k

			     //create child element
					  Element staff = doc.createElement("Sayi"); 
				      Attr attr = doc.createAttribute("index"); 
				      attr.setValue(Integer.toString(j));			//for index="j"
				      Attr attr2 = doc.createAttribute("TekrarAdedi"); 
				      attr2.setValue(Integer.toString(count));
			 
				      staff.appendChild(doc.createTextNode(Integer.toString(temp[j])));// innertext for Sayi child
				      staff.setAttributeNode(attr);
				      staff.setAttributeNode(attr2);
				      rootElement.appendChild(staff);
				      
			       count=0;
			       
			       
			   } //end for j
			 
			  //write xml file 
			 DOMSource source = new DOMSource(doc);
		     StreamResult result = new StreamResult(new File("Counter.xml"));
		     transformer.transform(source, result);
		     

			 
			
		}catch(Exception e){
			
			
			
		}//end catch
		
	   
		
		
		
		super.run();
	} //end run function

	
	
	
	
	




		 
		
	

 
 
	
	
	
	
	
	
} //end class
