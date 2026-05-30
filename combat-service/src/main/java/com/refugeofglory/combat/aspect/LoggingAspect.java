package com.refugeofglory.combat.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.refugeofglory.combat.service.*.*(..))")
    public void logBeforeAction(JoinPoint joinPoint) {
        log.info("Executando: {} com argumentos: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(
            pointcut = "execution(* com.refugeofglory.combat.service.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Concluído: {}",
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(
            pointcut = "execution(* com.refugeofglory.combat.service.*.*(..))",
            throwing = "exception"
    )
    public void logError(JoinPoint joinPoint, Exception exception) {
        log.error("Erro em: {} — mensagem: {}",
                joinPoint.getSignature().getName(),
                exception.getMessage());
    }

    public void logAudit(String user, String action) {
        log.info("Auditoria — usuário: {} executou: {}", user, action);
    }
}