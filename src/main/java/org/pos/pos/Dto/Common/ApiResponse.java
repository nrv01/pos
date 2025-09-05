package org.pos.pos.Dto.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(force = true)
public class ApiResponse<T> extends BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, String> errors;

    public ApiResponse(boolean success, String message, T data, Map<String, String> errors) {
        super(success, message);
        this.data = data;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, null);
    }

    public static <T> ApiResponse<T> validationError(String message, Map<String, String> errors) {
        return new ApiResponse<>(false, message, null, errors);
    }

}
