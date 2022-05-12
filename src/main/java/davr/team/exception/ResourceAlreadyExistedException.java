package davr.team.exception;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
public class ResourceAlreadyExistedException extends RuntimeException {
    public ResourceAlreadyExistedException(String message) {
        super(message);
    }
}
