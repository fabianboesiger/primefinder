import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// Stores the already known prime numbers
		ArrayList <Integer> known = new ArrayList <Integer> ();
		// Current number to check
		int current = 2;
		
		// Set optional limit
		while(true){
			
			// Sets to true if current number to check is divisible through a prime number
			boolean divisible = false;
			
			// Check if current number to check is divisible through any already known prime number
			for(int i = 0; i < known.size(); i++){
				if(known.get(i) > current/2){
					// No need to check further
					break;
				}
				else if(current % known.get(i) == 0){
					// Current number to check is divisible
					divisible = true;
					// No need to check further
					break;
				}
			}
			
			if(!divisible){
				// Current number is a prime number, push to the already known prime numbers
				known.add(current);		
				System.out.println("Prime number found: "+current);
			}
			current++;
			
		}
		
	}

}
