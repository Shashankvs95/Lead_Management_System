package springbootprojecttwowheller.com.cagl.exception;

public class FileNotFoundException extends RuntimeException {

	private String message;

	public FileNotFoundException(String message) {
		super();
		this.message = message;
	}

	public FileNotFoundException() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
