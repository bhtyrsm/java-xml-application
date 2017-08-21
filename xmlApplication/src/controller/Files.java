package controller;

public class Files {

	String fileName;		//set file name
	int fileNumber;  	    //set file number
	int NumberInFile;     //set  # of number in every file
	
	
	
	
	
	public Files(String Fname,int FNum,int FNumContain) {
		// TODO Auto-generated constructor stub
		
		//set file info
		setFileName(Fname);
		setFileNumber(FNum);
		setNumberInFile(FNumContain);
		
	}
	
	//return file name
	public String getFileName() {
		return fileName;
	}

	//set file name
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

      
	//retun files number
	public int getFileNumber() {
		return fileNumber;
	}

    //set files number
	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

      //return # of number in files
	public int getNumberInFile() {
		return NumberInFile;
	}


	//set # of number in files
	public void setNumberInFile(int NumberInFile) {
		this.NumberInFile = NumberInFile;
	}


	
	
	
	
	
	
}
