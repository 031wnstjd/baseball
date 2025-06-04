public class Game {

    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        return new GuessResult(isSolved(guessNumber), countStrikes(guessNumber), countBalls(guessNumber));
    }

    private boolean isSolved(String guessNumber) {
        return guessNumber.equals(question);
    }

    private int countStrikes(String guessNumber) {
        char[] questionCharArray = question.toCharArray();
        char[] guessNumberCharArray = guessNumber.toCharArray();

        int strikes = 0;
        for (int i = 0; i < guessNumber.length(); i++) {
            if (guessNumberCharArray[i] == questionCharArray[i]) {
                strikes++;
            }
        }
        return strikes;
    }

    private int countBalls(String guessNumber) {
        char[] questionCharArray = question.toCharArray();
        char[] guessNumberCharArray = guessNumber.toCharArray();

        int balls = 0;
        for (int i = 0; i < guessNumber.length(); i++) {
            for (int j = 0; j < question.length(); j++) {
                if (i == j) continue;
                if (guessNumberCharArray[i] == questionCharArray[j]) {
                    balls++;
                }
            }
        }
        return balls;
    }

    private void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if (guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
