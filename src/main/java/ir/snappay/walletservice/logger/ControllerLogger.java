package ir.snappay.walletservice.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class ControllerLogger {
    @Around("within(ir.snappay.walletservice.controller..*)")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        CustomLogger.get().info("start api:"+ methodName);
        var startDate= new Date().getTime();

        try{
           var res=  joinPoint.proceed();
           var duration= new Date().getTime() - startDate;
           CustomLogger.get().info("successful finish of api: "+methodName +", duration: "+duration+" ms");

           return res;
        } catch (Throwable e) {
            CustomLogger.get().error("error in calling api:"+ methodName,e);
            throw e;
        }

    }
}
