import java.io.File;

public class Main{
	
	public static void main(String[] args){
		
		// Start a prime finder instance
		PrimeFinder primeFinder = new PrimeFinder(new File("primes.txt").getAbsolutePath(), 1024);
		// Run it
		primeFinder.run();
		
	}
	
}
