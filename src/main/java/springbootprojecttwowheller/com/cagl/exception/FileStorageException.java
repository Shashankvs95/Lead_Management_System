package springbootprojecttwowheller.com.cagl.exception;

public class FileStorageException extends RuntimeException {

	private String message;

	public FileStorageException(String message) {
		super();
		this.message = message;
	}

	public FileStorageException() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
