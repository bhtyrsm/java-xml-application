package controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





public class FileFunc {


	Files fl;
	int[] tempOne;
	int[] tempTwo;
	
	
	
	// constructor
	
	public FileFunc(Files Fl){
		
		//initialize
		this.fl=Fl;
		tempOne=null;
		
	}  //end constructor
	

	//given xml file to array for sorting every xml file
	public int[] xmlFileToArray(Files file,String fileName,int[] arr) throws ParserConfigurationException, SAXException, IOException{
		
	
		try{
			
			 //initialize xml file   
			 //load xml file
             File names = new File(fileName);
            
            //initialize arr variable
            arr = new int[file.getNumberInFile()];
            
            //initialize for xml files
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			//to get nodes from given xml file
			Document doc = dBuilder.parse(names); //  look ->File names=new File(fileName) , fileName that given xml file 
			doc.getDocumentElement().normalize();

			 //get nodes from given xml file
			 NodeList nodes = doc.getElementsByTagName("Sayi");

			
			//get numbers from nodes
			//set number to array
			for (int j = 0; j< nodes.getLength(); j++) {
			  Node node = nodes.item(j);
			  arr[j]=Integer.parseInt(node.getTextContent().toString());
			}
				
		} catch(Exception e){
		
			
		} //end cathc
			
			
	   // return array that include number of given xml file
		return arr	;
	
	} //end function xmlFileToArray
	

	   //sort xml files 
	public boolean Sort(int selection){
			
			try{
				
				
				//declerations to create new xml file after sorting
				 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			
				for(int i=1;i<=fl.getFileNumber();i++){
					
					
					
				    //xmlFileToArray  return array that  include numbers of given xml file name
					//arr set numbers
					int[] arr=xmlFileToArray(fl, fl.getFileName()+i+".xml", tempOne);
				
					
					
				    // first of all , sort array according to selection
				
					//for SELECTÝON SORT
					if(selection==1){
						
						 int tmp, min;

	                        for (int a = 0; a < arr.length - 1; a++)
	                        {
	                            min = a;
	                            for (int j = a + 1; j < arr.length; j++)
	                            {
	                                if (arr[j] < arr[min])
	                                    min = j;

	                            }
	                            if (min != a)
	                            {
	                            	tmp = arr[a];
	                                arr[a] = arr[min];
	                                arr[min] = tmp;
	                            }

	                        }  	//end for a	
	
					} //end if selection sort
					
					
					//For BUBBLE SORT
					if(selection==2){
						
						int a = 1, j, val;
	                      
                        while (a < arr.length)
                        {
                            j = arr.length - 1;
                            while (j >= 1)
                            {
                                if (arr[j - 1] > arr[j])
                                {
                                    val = arr[j];
                                    arr[j] = arr[j - 1];
                                    arr[j - 1] = val;
                                }
                                j--;
                            }  //end while j
                            a++;
                        } // end while a					
					} //end is bubble sort
					
					
					
					//For ÝNSERTÝON SORT
					if(selection==3){
						int tmp;
						for (int a= 0; a <arr.length; a++)
                        {
                            for (int j = a; j > 0; j--)
                            {
                                if (arr[j] < arr[j - 1])
                                {
                                    tmp = arr[j];
                                    arr[j] = arr[j - 1];
                                    arr[j - 1] = tmp;
                                }
                            }
                        }
						
					} //end if insertion sort
					
					
					
					
					//now create  new xml files and  rewrite sorted number into every xml files
					
					//create start element
				      Document doc = docBuilder.newDocument();
				      Element rootElement = doc.createElement("SayiList"); 
				      doc.appendChild(rootElement);
				      
				      // write contents into xml file 
				      TransformerFactory transformerFactory = TransformerFactory.newInstance();
				      Transformer transformer = transformerFactory.newTransformer();
				    
				      for(int j=0;j<fl.getNumberInFile();j++){
				    		 
				    		//create child element
				    		  Element staff = doc.createElement("Sayi"); 
						      Attr attr = doc.createAttribute("index"); 
						      attr.setValue(Integer.toString(j));			//for index="j"
						      
				      
		
						      staff.appendChild(doc.createTextNode(Integer.toString(arr[j])));// innertext for Sayi child
						      staff.setAttributeNode(attr);
						      rootElement.appendChild(staff);
						
				    	 }  //end for j
				      
				        //write xml file 
				    	 DOMSource source = new DOMSource(doc);
					      StreamResult result = new StreamResult(new File(fl.getFileName()+i+".xml"));
					      transformer.transform(source, result);
					      
				
				
			} //end for i
			}catch(Exception e){
				
				return false;
			} //end catch	
			return true;
		}  //end function Sort
	
	
		//all xml file are convert array to find repeating numbers
	public int[] AllxmlFileToArray(Files fl,int[] arr){
		
		
		try{
	
			int index=0;
			arr=new int[fl.getFileNumber()*fl.getNumberInFile()];
			
			
		     
		      
		     //daha verimli bir yol düþün
		
			for(int i=1;i<=fl.getFileNumber();i++){
				
			    //xmlFileToArray function  return array that  include numbers of given xml file name
				//tempThree set numbers
				int[] tempThree=xmlFileToArray(fl, fl.getFileName()+i+".xml", tempOne);
			
				
				 //read all xml files 
				 // storage all number into arr array
				for(int a=0;a<tempThree.length;a++){
					
					arr[index]=tempThree[a];
					index++;
				}
				
			} //end for i
			
		}catch(Exception e){
			System.out.println("Error occurupted while converting all xml file to array");
		}
		
		return arr;
	}
	
} //end class
