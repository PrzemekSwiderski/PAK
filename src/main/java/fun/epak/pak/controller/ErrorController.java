package fun.epak.pak.controller;

import fun.epak.pak.exceptions.NoPostException;
import fun.epak.pak.exceptions.NoUserException;
import fun.epak.pak.exceptions.SaveFileException;
import fun.epak.pak.exceptions.SubscribeYourselfException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({SaveFileException.class, FileSizeLimitExceededException.class})
    public String getErrorView() {
        return "/errors/file";
    }

    @ExceptionHandler(SubscribeYourselfException.class)
    public String getErrorSubscribeView() {
        return "/errors/subscribe";
    }

    @ExceptionHandler(NoUserException.class)
    public String getErrorNoUserView() {
        return "/errors/noUser";
    }

    @ExceptionHandler(NoPostException.class)
    public String getErrorNoPostView() {
        return "/errors/noPost";
    }
}

