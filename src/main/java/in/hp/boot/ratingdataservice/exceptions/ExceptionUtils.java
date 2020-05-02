package in.hp.boot.ratingdataservice.exceptions;

import org.springframework.util.StringUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionUtils {

    public static String formExceptionMessageFromUserIdMovieId(String userId, String movieId) {
        return !StringUtils.isEmpty(movieId) ? ("User ID :: " + userId + ", Movie ID:: " + movieId)
                : "User ID:: " + userId;
    }

    public static Supplier<ResourceNotFoundException> getResourceNotFoundExceptionSupplier(String userId, String movieId) {
        return () -> {
            throw new ResourceNotFoundException(formExceptionMessageFromUserIdMovieId(userId, movieId));
        };
    }

    public static Supplier<ResourceNotFoundException> getResourceNotFoundExceptionSupplier(String userId) {
        return () -> {
            throw new ResourceNotFoundException(formExceptionMessageFromUserIdMovieId(userId, ""));
        };
    }

    public static Supplier<ResourceConflictException> getResourceConflictException(String userId, String movieId) {
        return () -> {
            throw new ResourceConflictException(formExceptionMessageFromUserIdMovieId(userId, movieId));
        };
    }

    public static Consumer<Object> getResourceConflictExceptionConsumer(String userId, String movieId) {
        return w -> {
            throw new ResourceConflictException(ExceptionUtils.formExceptionMessageFromUserIdMovieId(userId, movieId));
        };
    }

}
