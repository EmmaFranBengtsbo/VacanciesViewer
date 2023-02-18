package ru.example;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.example.util.exception.ErrorInfo;
import ru.example.util.validation.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> conflict(HttpServletRequest req, ConstraintViolationException e) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(e);
        if (rootCause == null) {
            rootCause = e;
        }
        String errorMessage = rootCause.getMessage();
        String propKey = "propertyPath=";
        String msgKey = "interpolatedMessage='";
        Map<String, List<String>> map = StringUtil.getMessage(errorMessage, propKey, msgKey);
        if (map != null) {
            StringBuilder builder = new StringBuilder();
            for (String key : map.keySet()) {
                builder.append(key);
                builder.append(": ");
                for (String element : map.get(key)) {
                    builder.append(element);
                    builder.append(", ");
                }
                builder.delete(builder.length()-2, builder.length()-1);
                builder.append("; ");
            }
            builder.delete(builder.length()-2, builder.length()-1);
            builder.append(".");
            ErrorInfo body = new ErrorInfo(builder.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_XML)
                .body(body);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_XML)
                    .body(new ErrorInfo());
        }
    }
}
