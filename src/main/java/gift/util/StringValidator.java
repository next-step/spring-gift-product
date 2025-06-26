package gift.util;

public final class StringValidator {

    private StringValidator() {
        throw new AssertionError("utility class");
    }

    public static boolean isNotBlank(String str) {
        if (str != null && !str.isBlank()) {
            return true;
        }
        return false;
    }
}
