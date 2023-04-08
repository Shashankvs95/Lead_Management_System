package springbootprojecttwowheller.com.cagl.responseerror;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResponseError {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private List<?> erros;

	public ResponseError() {

	}

	public ResponseError(LocalDateTime timestamp, String message, List erros) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.erros = erros;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getErros() {
		return erros;
	}

	public void setErros(List<?> erros) {
		this.erros = erros;
	}

}
