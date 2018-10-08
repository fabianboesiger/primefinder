public interface ExceptionHandler {
	
	default void handleException(Exception exception){
		exception.printStackTrace();
		System.exit(0);
	}
	
}
