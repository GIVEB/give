package ten.give.domain.exception.advice;

import jdk.jfr.Experimental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.ExErrorResult;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExErrorResult NoAuthenticationExHandle(Exception e){
        log.error("[exceptionHandle] ex", e);
        return new ExErrorResult("Bad" , e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ExErrorResult illegalExHandle(IllegalStateException e){
        log.error("[exceptionHandle] ex",e);
        return new ExErrorResult("Bad",e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExErrorResult illegalStatementExHandle(IllegalArgumentException e){
        log.error("[exceptopnHandle] ex",e);
        return new ExErrorResult("Bad",e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExErrorResult exHandle(Exception e){
        log.error("[exceptionHandle] ex",e);
        return new ExErrorResult("exception","server Error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExErrorResult noSuchTarget(NoSuchTargetException e){
        log.error("[exceptionHandle] ex", e);
        return new ExErrorResult("noSuchTarget",e.getMessage());
    }


}
