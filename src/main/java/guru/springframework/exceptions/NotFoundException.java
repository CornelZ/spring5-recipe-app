package guru.springframework.exceptions;

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = -6192490923252130603L;

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
