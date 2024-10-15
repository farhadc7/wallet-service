package ir.snappay.walletservice.exception;

import io.jsonwebtoken.ExpiredJwtException;
import ir.snappay.walletservice.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity<>(
                ResponseObject.builder()
                        .error(ExceptionResponse.builder()
                                .message(createMessage(e)).build()).build()
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleCustomException(CustomException e){
        return new ResponseEntity<>( createResponse(e.getErrorCode().getReason(),e.getErrorCode().getValue())
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
           return createResponse("The username or password is incorrect",401);
        }

        if (exception instanceof AccountStatusException) {
            return createResponse("The account is locked",403);
        }

        if (exception instanceof AccessDeniedException) {
            return createResponse("You are not authorized to access this resource",403);
        }

        if (exception instanceof SignatureException) {
            return createResponse("The JWT signature is invalid",403);
        }

        if (exception instanceof ExpiredJwtException) {
            return createResponse("The JWT token has expired",403);
        }

        if (errorDetail == null) {
            return createResponse("Unknown internal server error.",500);
        }

        return createResponse("Unknown internal server error.",500);
    }

    private ResponseObject createResponse(String message,int code){
       return ResponseObject.builder()
                .error(ExceptionResponse.builder()
                        .message(message)
                        .code(code)
                        .build()).build();
    }





    private String createMessage(MethodArgumentNotValidException e){
        StringBuilder stringBuilder= new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error->{
            String fieldName;
            try{
                fieldName= ((FieldError)error).getField();
            }catch (ClassCastException ex){
                fieldName= error.getObjectName();
            }
            String message= error.getDefaultMessage();
            stringBuilder.append(String.format("%s not valid - %s\n",fieldName,message));
        });
        return stringBuilder.toString();
    }
}
