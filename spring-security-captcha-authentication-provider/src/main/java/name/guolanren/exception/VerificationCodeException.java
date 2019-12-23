package name.guolanren.exception;

/**
 * @author guolanren
 */
public class VerificationCodeException extends RuntimeException {

    public VerificationCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerificationCodeException(String msg) {
        super(msg);
    }
}
