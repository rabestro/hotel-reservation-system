package lv.id.jc.hotel.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.Logger.Level.WARNING;
import static java.util.stream.Collectors.joining;

@Aspect
@Component
@SuppressWarnings("squid:S1186")
public class LoggingAspect {
    private static final System.Logger LOG = System.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {
    }

    /**
     * A join point is in the service layer if the method is defined
     * in a type in the lv.id.jc.hotel.service package or any sub-package
     * under that.
     */
    @Pointcut("within(lv.id.jc.hotel.service..*)")
    public void inServiceLayer() {
    }

    /**
     * A join point is in the data access layer if the method is defined
     * in a type in the lv.id.jc.hotel.repository package or any sub-package
     * under that.
     */
    @Pointcut("within(lv.id.jc.hotel.repository..*)")
    public void inDataAccessLayer() {
    }

    /**
     * A data access operation is the execution of any method defined on a
     * dao interface. This definition assumes that interfaces are placed in the
     * "dao" package, and that implementation types are in sub-packages.
     */
    @Pointcut("execution(* lv.id.jc.hotel.repository.*.*(..))")
    public void dataAccessOperation() {
    }

    /**
     * A business service is the execution of any method defined on a service
     * interface. This definition assumes that interfaces are placed in the
     * "service" package, and that implementation types are in sub-packages.
     */
    @Pointcut("execution(* lv.id.jc.hotel.service.*.*(..))")
    public void businessService() {
    }

    @Before("businessService()")
    public void beforeCommand(JoinPoint joinPoint) {
        LOG.log(INFO, () -> {
            var parameters = Arrays.stream(joinPoint.getArgs())
                    .map(Object::toString)
                    .collect(joining(", ", "(", ")"));
            return methodName(joinPoint) + " " + parameters;
        });
    }

    @AfterReturning("businessService()")
    public void afterSuccessful(JoinPoint joinPoint) {
        LOG.log(INFO, () -> methodName(joinPoint) + " successful processed.");
    }

    @AfterThrowing("businessService()")
    public void afterThrowing(JoinPoint joinPoint) {
        LOG.log(WARNING, () -> methodName(joinPoint) + " fails.");
    }

    private String methodName(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getName();
    }
}
