package SDA.Exceptions;

public class EvaluationIncapacityException extends RuntimeException {
    public EvaluationIncapacityException() {
        super("The candidate cannot be evaluated");
    }
}
