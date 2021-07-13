package cn.edu.zucc.wyk31801026.handle;

import cn.edu.zucc.wyk31801026.response.Result;
import cn.edu.zucc.wyk31801026.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 全局异常处理，没有指定异常，所有异常都会被捕获
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        e.printStackTrace();
        return Result.error().code(e.getCode())
                .message(e.getErrorMsg());
    }

    /**
     * 权限异常
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result error(){
        return Result.error().code(ResultCode.NO_PERMISSION.getCode())
                .message(ResultCode.NO_PERMISSION.getMessage());
    }


}
