import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PrimeFinderThread extends Thread implements ExceptionHandler{
	
	private final String PATH;
	private int current;
	private boolean isPrime = false;
	
	public PrimeFinderThread(final String PATH, int current){
		super();
		
		this.PATH = PATH;
		this.current = current;
	}
	
	public void run(){
		
		BufferedReader reader = null;
		String line = null;
		
		try{
			
			// Setup for the reader
			try{
				reader = new BufferedReader(new FileReader(PATH)); 
			}
			catch(FileNotFoundException exception){
				handleException(exception);
			}
			
			// Sets to true if current number to check is divisible through a prime number
			boolean divisible = false;
			
			// Check if current number to check is divisible through any already known prime number
			while((line = reader.readLine()) != null){
				int prime = Integer.parseInt(line);
				
				if(prime > current/2){
					// No need to check further
					break;
				}
				else if(current % prime == 0){
					// Current number to check is divisible
					divisible = true;
					// No need to check further
					break;
				}
			}
			
			if(!divisible){
				// Current number is a prime number, push to the already known prime numbers
				isPrime = true;
			}
			
			// Close reader
			try{
				reader.close();
			}
			catch(IOException exception){
				handleException(exception);
			}
			
		}
		catch(IOException exception){
			handleException(exception);
		}
		
	}
	
	public boolean isPrime(){
		return isPrime;
	}
	
	public int getNumber(){
		return current;
	}
	
}
