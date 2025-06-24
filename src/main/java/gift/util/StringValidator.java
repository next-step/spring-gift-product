package gift.util;

public abstract class StringValidator {

    public static boolean validate(String str) {
        if (str != null && !str.isBlank()) {
            return true;
        }
        return false;
    }
}
