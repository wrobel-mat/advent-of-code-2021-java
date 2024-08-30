package http;

public record AocSubmitResult(String submittedAnswer, String responseMessage) {

    private static final String GOOD_ANSWER = "That's the right answer!";
    private static final String ALREADY_COMPLETED = "You don't seem to be solving the right level. Did you already complete it?";

    public boolean isGoodAnswer() {
        return responseMessage.startsWith(GOOD_ANSWER) || responseMessage.startsWith(ALREADY_COMPLETED);
    }
}
