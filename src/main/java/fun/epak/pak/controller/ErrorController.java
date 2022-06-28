package fun.epak.pak.controller;

import fun.epak.pak.exceptions.SaveFileException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({SaveFileException.class, FileSizeLimitExceededException.class})
    public String getFileEror(){
        return "/errors/file";
    }
}
