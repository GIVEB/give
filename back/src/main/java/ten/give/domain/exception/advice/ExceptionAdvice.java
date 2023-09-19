package ten.give.domain.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.DonorCardErrorResult;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public DonorCardErrorResult illegalExHandle(IllegalStateException e){
        log.error("[exceptionHandle] ex",e);
        return new DonorCardErrorResult("Bad",e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public DonorCardErrorResult illegalStatementExHandle(IllegalArgumentException e){
        log.error("[exceptopnHandle] ex",e);
        return new DonorCardErrorResult("Bad",e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public DonorCardErrorResult exHandle(Exception e){
        log.error("[exceptionHandle] ex",e);
        return new DonorCardErrorResult("exception","server Error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public DonorCardErrorResult noSuchTarget(NoSuchTargetException e){
        log.error("[exceptionHandle] ex", e);
        return new DonorCardErrorResult("noSuchTarget",e.getMessage());
    }

}
