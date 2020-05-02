package in.hp.boot.ratingdataservice.exceptions;

import java.util.function.Supplier;

public class ExceptionUtils {
    public static Supplier<RuntimeException> throwRuntimeExceptionSupplier(String userId, String movieId) {
        return () -> {
            throw new ResourceNotFoundException("User ID:: " + userId + ", Movie ID:: " + movieId);
        };
    }
}
