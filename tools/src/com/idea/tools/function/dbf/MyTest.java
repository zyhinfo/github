package com.idea.tools.function.dbf;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MyTest {

	/** 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		readDBF();
		//writeDBF();
//		writeDBFFlush();
//		String encoding = System.getProperty("file.encoding");
//		System.out.println(encoding);
	}
	
	public static void writeDBFFlush() throws IOException{
		//		 let us create field definitions first
	    // we will go for 3 fields
	    //
	    DBFField fields[] = new DBFField[ 3];

	    fields[0] = new DBFField();
	    fields[0].setName( "字段1");
	    fields[0].setDataType( DBFField.FIELD_TYPE_C);
	    fields[0].setFieldLength( 10);

	    fields[1] = new DBFField();
	    fields[1].setName( "emp_name");
	    fields[1].setDataType( DBFField.FIELD_TYPE_C);
	    fields[1].setFieldLength( 20);

	    fields[2] = new DBFField();
	    fields[2].setName( "salary");
	    fields[2].setDataType( DBFField.FIELD_TYPE_N);
	    fields[2].setFieldLength( 12);
	    fields[2].setDecimalCount( 2);

	    DBFWriter writer = new DBFWriter(6);
	    //writer.setCharactersetName(null);
	    writer.setFields( fields);
	    FileOutputStream fos = new FileOutputStream( "F:\\test.zip");
	    ZipOutputStream zipOut = new ZipOutputStream(fos);
	    ZipEntry e = new ZipEntry("ccc.dbf");
		zipOut.putNextEntry(e);
		DataOutputStream outStream = new DataOutputStream( zipOut);
		writer.writeHeader(outStream);
	   
	    // now populate DBFWriter
	    //

	    Object rowData[] = new Object[3];
	    rowData[0] = "陈做冠";
	    rowData[1] = "John";
	    rowData[2] = new Double( 5000.00);

	    writer.addRecord(rowData,outStream);

	    rowData = new Object[3];
	    rowData[0] = "郑咏琳";
	    rowData[1] = "Lalit";
	    rowData[2] = new Double( 3400.00);

	    writer.addRecord( rowData,outStream);

	    rowData = new Object[3];
	    rowData[0] = "1002";
	    rowData[1] = "Rohit";
	    rowData[2] = new Double( 7350.00);

	    writer.addRecord( rowData,outStream);
	    
	    rowData = new Object[3];
	    rowData[0] = "1003";
	    rowData[1] = "Rohit";
	    rowData[2] = new Double( 7350.00);

	    writer.addRecord( rowData,outStream);
	    writer.writeEnd(outStream);

	    //writer
	    outStream.close();
	}
	
	public static void writeDBF() throws IOException{
		//		 let us create field definitions first
	    // we will go for 3 fields
	    //
	    DBFField fields[] = new DBFField[ 3];

	    fields[0] = new DBFField();
	    fields[0].setName( "字段1");
	    fields[0].setDataType( DBFField.FIELD_TYPE_C);
	    fields[0].setFieldLength( 10);

	    fields[1] = new DBFField();
	    fields[1].setName( "emp_name");
	    fields[1].setDataType( DBFField.FIELD_TYPE_C);
	    fields[1].setFieldLength( 20);

	    fields[2] = new DBFField();
	    fields[2].setName( "salary");
	    fields[2].setDataType( DBFField.FIELD_TYPE_N);
	    fields[2].setFieldLength( 12);
	    fields[2].setDecimalCount( 2);

	    DBFWriter writer = new DBFWriter();
	    //writer.setCharactersetName(null);
	    writer.setFields( fields);

	    // now populate DBFWriter
	    //

	    Object rowData[] = new Object[3];
	    rowData[0] = "陈做冠";
	    rowData[1] = "John";
	    rowData[2] = new Double( 5000.00);

	    writer.addRecord( rowData);

	    rowData = new Object[3];
	    rowData[0] = "郑咏琳";
	    rowData[1] = "Lalit";
	    rowData[2] = new Double( 3400.00);

	    writer.addRecord( rowData);

	    rowData = new Object[3];
	    rowData[0] = "1002";
	    rowData[1] = "Rohit";
	    rowData[2] = new Double( 7350.00);

	    writer.addRecord( rowData);
	    
	    rowData = new Object[3];
	    rowData[0] = "1003";
	    rowData[1] = "Rohit";
	    rowData[2] = new Double( 7350.00);

	    writer.addRecord( rowData);

	    FileOutputStream fos = new FileOutputStream( "d:\\test.zip");
	    ZipOutputStream zipOut = new ZipOutputStream(fos);
	    ZipEntry e = new ZipEntry("ccc.dbf");
		zipOut.putNextEntry(e);
	    writer.write( zipOut);
	    zipOut.close();
	}
	
	public static void readDBF()throws IOException{
		try {

		      // create a DBFReader object
		      //
		      InputStream inputStream  = new FileInputStream( "d:\\20111212.DBF"); // take dbf file as program argument
		      DBFReader reader = new DBFReader( inputStream); 

		      // get the field count if you want for some reasons like the following
		      //
		      int numberOfFields = reader.getFieldCount();

		      // use this count to fetch all field information
		      // if required
		      //
		      for( int i=0; i<numberOfFields; i++) {

		        DBFField field = reader.getField(i);

		        // do something with it if you want
		        // refer the JavaDoc API reference for more details
		        //
		        System.out.println( field.getName());
		      }

		      // Now, lets us start reading the rows
		      //
		      Object []rowObjects;

		      while( (rowObjects = reader.nextRecord()) != null) {

		        for( int i=0; i<rowObjects.length; i++) {

		          System.out.println( rowObjects[i]);
		        }
		      }

		      // By now, we have itereated through all of the rows
		      
		      inputStream.close();
		    }
		    catch( DBFException e) {

		      System.out.println( e.getMessage());
		    }
		    catch( IOException e) {

		      System.out.println( e.getMessage());
		    }

	}
}
