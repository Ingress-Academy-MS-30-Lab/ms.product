package az.ingress.aspect;

import static az.ingress.model.constant.ApplicationConstants.ACTION_LOG;
import static az.ingress.model.constant.ApplicationConstants.LOG_END;
import static az.ingress.model.constant.ApplicationConstants.LOG_ERROR;
import static az.ingress.model.constant.ApplicationConstants.LOG_START;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

  private final ObjectMapper objectMapper;

  @Around("@within(az.ingress.annotation.Logable) "
      + "|| @annotation(az.ingress.annotation.Logable)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    var methodSignature = (MethodSignature) joinPoint.getSignature();
    var methodName = methodSignature.getName();
    var parameters = buildParams(methodSignature, joinPoint.getArgs());
    var log = LoggerFactory.getLogger(joinPoint.getSourceLocation().getWithinType());
    logEvent(LOG_START, log, methodName, parameters);
    Object response;
    try {
      response = joinPoint.proceed();
    } catch (Throwable e) {
      logEvent(LOG_ERROR, log, methodName, parameters);
      throw e;
    }
    logEndEvent(methodSignature, log, response);
    return response;
  }

  private void logEndEvent(MethodSignature methodSignature, Logger log, Object response) throws JsonProcessingException {
    var methodName = methodSignature.getName();
    if (void.class.equals(methodSignature.getReturnType())) {
      log.info("ActionLog.{}.end", methodName);
    } else {
      var responseParameters = new StringBuilder(objectMapper.writeValueAsString(response));
      logEvent(LOG_END, log, methodName, responseParameters);
    }

  }

  private void logEvent(String eventName, Logger log, String methodName, StringBuilder parameters) {
    log.info(ACTION_LOG, eventName, methodName, parameters);
  }

  private StringBuilder buildParams(MethodSignature methodSignature, Object[] args) throws JsonProcessingException {
    var builder = new StringBuilder();

    var params = methodSignature.getMethod().getParameters();
    for (int i = 0; i < params.length; i++) {
      var currentParam = params[i];
      var currentArg = args[i];

      builder.append(" ").append(currentParam.getName())
          .append(":")
          .append(objectMapper.writeValueAsString(currentArg));
    }

    return builder;
  }

}
