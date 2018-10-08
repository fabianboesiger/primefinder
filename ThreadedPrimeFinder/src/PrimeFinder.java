import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrimeFinder implements ExceptionHandler{
	
	private final String PATH;
	private final int MAX_THREADS;
	
	public PrimeFinder(final String PATH, final int MAX_THREADS){
		this.PATH = PATH;
		this.MAX_THREADS = MAX_THREADS;
	}
	
	public void run(){

		//////////////////////////////// SETUP BLOCK ////////////////////////////////
		
		//System.out.println("Path to prime number storage: " + PATH);		
		
		// Reader for the prime number storage
		BufferedReader reader = null;
		String line = null;
		// Setup for the reader
		try{
			reader = new BufferedReader(new FileReader(PATH)); 
		}
		catch(FileNotFoundException exception){
			handleException(exception);
		}
		
		// Writer for the prime number storage
		BufferedWriter writer = null;
		// Setup for the writer in append mode
		try{
			writer = new BufferedWriter(new FileWriter(PATH, true)); 
		}
		catch(IOException exception){
			handleException(exception);
		}

		// Current number to check
		int current = 2;
		try{
			while((line = reader.readLine()) != null){
				current = Integer.parseInt(line) + 1;
				//System.out.println("Jumping to number " + current);
			}
		}
		catch(IOException exception){
			handleException(exception);
		}
		
		// Close reader
		try{
			reader.close();
		}
		catch(IOException exception){
			handleException(exception);
		}
		
		//////////////////////////////// PROCESSING BLOCK ////////////////////////////////
		
		// Set optional limit
		while(current < Integer.MAX_VALUE){
			
			// Set up a list of threads and run them
			ArrayList <PrimeFinderThread> primeFinderThreads = new ArrayList <PrimeFinderThread> ();
			for(int i = current; i < (((current * 2 - 1) >= current + MAX_THREADS) ? (current + MAX_THREADS) : (current * 2 - 1)); i++){
				primeFinderThreads.add(new PrimeFinderThread(PATH, i));
				primeFinderThreads.get(primeFinderThreads.size() - 1).run();
			}
			
			current += primeFinderThreads.size();
			
			// Join threads
			for(int i = 0; i < primeFinderThreads.size(); i++){
				try{
					PrimeFinderThread primeFinderThread = primeFinderThreads.get(i);
					primeFinderThread.join();
					
					// Add to prime numbers list if number is prime number
					if(primeFinderThread.isPrime()){
						try{
							writer.write(String.valueOf(primeFinderThread.getNumber()));
							writer.newLine();
							
							System.out.println("Prime number found: " + primeFinderThread.getNumber());
						}catch(IOException exception){
							handleException(exception);
						}
					}
					
					
				}catch(InterruptedException exception){
					handleException(exception);
				}
			}
			
			try{
				writer.flush();
			}catch(IOException exception){
				handleException(exception);
			}
				
		}
		
		////////////////////////////////EXIT BLOCK ////////////////////////////////

		
		
		// Close writer
		try{
			writer.close();
		}
		catch(IOException exception){
			handleException(exception);
		}
		
	}
	
}
