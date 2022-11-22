package ng.temire.hrbp.test.exceptions;

public class RecordNotFoundException extends RuntimeException {
  public RecordNotFoundException(String id) {
    super("Post id: " + id + " was not found. ");
  }
}
