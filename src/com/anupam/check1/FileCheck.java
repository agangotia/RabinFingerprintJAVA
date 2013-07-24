package com.anupam.check1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import com.google.common.io.ByteStreams;
import org.rabinfingerprint.fingerprint.RabinFingerprintLong;
import org.rabinfingerprint.fingerprint.RabinFingerprintLongWindowed;
import org.rabinfingerprint.polynomial.Polynomial;

public class FileCheck {
	
	 public static void main(String[] args) {
	        System.out.println("Hello, World");
	       //fingerprintCheck();
	        //slidingMoreBytesWindowCheck1File();
	        //slidingWindowCheck1File();
	       //slidingWindowCheck2File();
	       // slidingMoreBytesWindowCheck2File();
	        ArrayList result=findBoundaries("C:\\Users\\anugan\\test.txt");
	        showArrayList(result);
	   
	    }

	 public static void fingerprintCheck()
	 {
		  // Create new random irreducible polynomial
	     // These can also be created from Longs or hex Strings
	     Polynomial polynomial = Polynomial.createIrreducible(53);

	     // Create a fingerprint object
	     RabinFingerprintLong rabin = new RabinFingerprintLong(polynomial);

	     // Push bytes from a file stream
	     try {
			rabin.pushBytes(ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\Dedup Work\\Helper Cpp Projects\\Mp3TagRead\\Mp3TagRead\\Time.mp3")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     // Get fingerprint value and output
	     System.out.println(Long.toString(rabin.getFingerprintLong(), 16));
	 }
	 public static void slidingWindowCheck1File()
	 {
		// Create new random irreducible polynomial
		// These can also be created from Longs or hex Strings
		Polynomial polynomial = Polynomial.createIrreducible(53);

		// Create a windowed fingerprint object with a window size of 48 bytes.
		RabinFingerprintLongWindowed window = new RabinFingerprintLongWindowed(polynomial, 48);
		try {
			for (byte b : ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\test.txt"))) {
			    // Push in one byte. Old bytes are automatically popped.
			    window.pushByte(b);
			    // Output current window's fingerprint
			    System.out.println(Long.toString(window.getFingerprintLong(), 16));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void slidingMoreBytesWindowCheck1File()
	 {
		// Create new random irreducible polynomial
		// These can also be created from Longs or hex Strings
		Polynomial polynomial = Polynomial.createIrreducible(53);

		// Create a windowed fingerprint object with a window size of 48 bytes.
		RabinFingerprintLongWindowed window = new RabinFingerprintLongWindowed(polynomial, 1024);
		
		try {
			
			byte[] objArray=ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\time.mp3"));
			int i=0;
			int loopCount=0;
			while(i<objArray.length){
				
					if(i+1024<objArray.length)
					{
						byte[] newObjArray=Arrays.copyOfRange(objArray,i,i+1024);
						window.pushBytes(newObjArray);
						System.out.println(Long.toString(window.getFingerprintLong(), 16));
						i=i+1024;
						loopCount++;
					}
					
					else
					{
						byte[] newObjArray=Arrays.copyOfRange(objArray,i,objArray.length);
						window.pushBytes(newObjArray);
						System.out.println(Long.toString(window.getFingerprintLong(), 16));
						i+=(objArray.length-i);
						loopCount++;
					}
				}
			System.out.println("Loop Count :"+loopCount);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void slidingWindowCheck2File()
	 {
		// Create new random irreducible polynomial
		// These can also be created from Longs or hex Strings
		Polynomial polynomial = Polynomial.createIrreducible(53);
		HashMap hm = new HashMap<String,Integer>(); 

		// Create a windowed fingerprint object with a window size of 48 bytes.
		RabinFingerprintLongWindowed window = new RabinFingerprintLongWindowed(polynomial, 48);
		try {
			for (byte b : ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\test.txt"))) {
			    // Push in one byte. Old bytes are automatically popped.
			    window.pushByte(b);
			    // Output current window's fingerprint
			   
			    String fingerprint=Long.toString(window.getFingerprintLong(), 16);
			    System.out.println(fingerprint);
			    if(!hm.containsKey(fingerprint))
			    {
			    	hm.put(fingerprint, 1);
			    }
			    else
			    {
			    	int a=(Integer) hm.get(fingerprint);
			    	hm.put(fingerprint,a+1);
			    	
			    }
			   // hm.put(window.getFingerprintLong(), 1);
			}
			
			 window = new RabinFingerprintLongWindowed(polynomial, 48);
			 for (byte b : ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\test2.txt"))) {
				    // Push in one byte. Old bytes are automatically popped.
				    window.pushByte(b);
				    // Output current window's fingerprint
				   
				    String fingerprint=Long.toString(window.getFingerprintLong(), 16);
				    System.out.println(fingerprint);
				    if(!hm.containsKey(fingerprint))
				    {
				    	hm.put(fingerprint, 1);
				    }
				    else
				    {
				    	int a=(Integer) hm.get(fingerprint);
				    	hm.put(fingerprint,a+1);
				    	
				    }
				   // hm.put(window.getFingerprintLong(), 1);
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator it = hm.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	 }
	 public static void slidingMoreBytesWindowCheck2File(){

		// Create new random irreducible polynomial
			// These can also be created from Longs or hex Strings
			Polynomial polynomial = Polynomial.createIrreducible(53);
			HashMap hm = new HashMap<String,Integer>(); 
			// Create a windowed fingerprint object with a window size of 48 bytes.
			RabinFingerprintLongWindowed window = new RabinFingerprintLongWindowed(polynomial, 48);
			
			try {
				
				{
					byte[] objArray=ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\time.mp3"));
					int i=0;
					int loopCount=0;
					while(i<objArray.length){
						
							if(i+10240<objArray.length)
							{
								byte[] newObjArray=Arrays.copyOfRange(objArray,i,i+10240);
								window.pushBytes(newObjArray);
								//System.out.println(Long.toString(window.getFingerprintLong(), 16));
								String fingerprint=Long.toString(window.getFingerprintLong(), 16);
								i=i+10240;
								loopCount++;
								 if(!hm.containsKey(fingerprint))
								    {
								    	hm.put(fingerprint, 1);
								    }
								    else
								    {
								    	int a=(Integer) hm.get(fingerprint);
								    	hm.put(fingerprint,a+1);
								    	
								    }
							}
							
							else
							{
								byte[] newObjArray=Arrays.copyOfRange(objArray,i,objArray.length);
								window.pushBytes(newObjArray);
								//System.out.println(Long.toString(window.getFingerprintLong(), 16));
								 String fingerprint=Long.toString(window.getFingerprintLong(), 16);
								i+=(objArray.length-i);
								loopCount++;
								 if(!hm.containsKey(fingerprint))
								    {
								    	hm.put(fingerprint, 1);
								    }
								    else
								    {
								    	int a=(Integer) hm.get(fingerprint);
								    	hm.put(fingerprint,a+1);
								    	
								    }
							}
						}
					System.out.println("Loop Count :"+loopCount);
					
				}
				
			 window = new RabinFingerprintLongWindowed(polynomial, 48);
			 {
				 byte[] objArray=ByteStreams.toByteArray(new FileInputStream("C:\\Users\\anugan\\time2.mp3"));
					int i=0;
					int loopCount=0;
					while(i<objArray.length){
						
							if(i+10240<objArray.length)
							{
								byte[] newObjArray=Arrays.copyOfRange(objArray,i,i+10240);
								window.pushBytes(newObjArray);
								//System.out.println(Long.toString(window.getFingerprintLong(), 16));
								String fingerprint=Long.toString(window.getFingerprintLong(), 16);
								i=i+10240;
								loopCount++;
								 if(!hm.containsKey(fingerprint))
								    {
								    	hm.put(fingerprint, 1);
								    }
								    else
								    {
								    	int a=(Integer) hm.get(fingerprint);
								    	hm.put(fingerprint,a+1);
								    	
								    }
							}
							
							else
							{
								byte[] newObjArray=Arrays.copyOfRange(objArray,i,objArray.length);
								window.pushBytes(newObjArray);
								//System.out.println(Long.toString(window.getFingerprintLong(), 16));
								 String fingerprint=Long.toString(window.getFingerprintLong(), 16);
								i+=(objArray.length-i);
								loopCount++;
								 if(!hm.containsKey(fingerprint))
								    {
								    	hm.put(fingerprint, 1);
								    }
								    else
								    {
								    	int a=(Integer) hm.get(fingerprint);
								    	hm.put(fingerprint,a+1);
								    	
								    }
							}
						}
					System.out.println("Loop Count 2:"+loopCount);
					
			 }
			 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Iterator it = hm.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
	 }
	 public static ArrayList<Integer> findBoundaries(String fileNameWithPath){
		//this function finds boundaries in a file using ,
		 //if last 8 bits of the finger print are zero, then its a boundary.
		 
		 //VAriable declaration
		// Create new random irreducible polynomial
			// These can also be created from Longs or hex Strings
			Polynomial polynomial = Polynomial.createIrreducible(53);
			// Create a windowed fingerprint object with a window size of 48 bytes.
			RabinFingerprintLongWindowed window = new RabinFingerprintLongWindowed(polynomial, 48);
			ArrayList resultBoundaries=new ArrayList<Integer>();
			boolean startNewWindow=true;
			int indexByteArray=0;
			try {
				for (byte b : ByteStreams.toByteArray(new FileInputStream(fileNameWithPath))) {
				   if(startNewWindow){
					   resultBoundaries.add(indexByteArray);
					   startNewWindow=false;
				   }
					// Push in one byte. Old bytes are automatically popped.
				    window.pushByte(b);
				    // Output current window's fingerprint
				    if(checkCondition(window.getFingerprintLong())){
				    	resultBoundaries.add(indexByteArray);
				    	startNewWindow=true;
				    	window.reset();
				    }
				   // System.out.println(Long.toString(window.getFingerprintLong(), 16));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultBoundaries;
	 }
	 public static boolean checkCondition(long value){
		 String valueBinarySTring=Long.toBinaryString(value);
		 String last12bits=null;
		 if(valueBinarySTring.length()>12)
		 {
			 last12bits=valueBinarySTring.substring(valueBinarySTring.length()-12, valueBinarySTring.length());
		 }
		 else
		 {
			 last12bits=valueBinarySTring;
		 }
		 int i=0;
		 while(i<last12bits.length())
		 {
			 if(last12bits.charAt(i)=='1')
				 return false;
			 i++;
		 }
		 
		 return true;
	 }
	 public static void showArrayList( ArrayList<Integer> result){
		 boolean switchV=true;
		 for(int item : result){
			 if(switchV){
				 System.out.println("\n Start"+item);
				 switchV=false;
			 }
			 else
			 {
				 System.out.println("\n End"+item);
				 switchV=true;
			 }
			 
		 }
	 }
}
