package kr.co.itcen.jblog.exception;

public class FileUploadException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public FileUploadException() {
		super("FileUpload Exception");

	}

	public FileUploadException(String message) {
		super(message);
	}
}
