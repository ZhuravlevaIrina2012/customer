package telran.ashkelon2020.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1403422301586105660L;

	public DocumentNotFoundException(String id) {
		super("Document " + id + " not found");
	}
	
	

}
