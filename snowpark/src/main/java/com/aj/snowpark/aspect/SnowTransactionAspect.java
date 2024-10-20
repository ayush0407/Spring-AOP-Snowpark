package com.aj.snowpark.aspect;

import com.aj.snowpark.utils.SnowSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.net.URISyntaxException;

@Aspect
public class SnowTransactionAspect {

    public SnowTransactionAspect() throws URISyntaxException {
    }

    @Around("@annotation(SnowTransaction)")
    public Object manageTransactions(ProceedingJoinPoint proceedingJoinPoint, SnowTransaction snowTransaction) throws Throwable {

        Object res = null;
        try {
            res = proceedingJoinPoint.proceed();
            SnowSession.getSession().sql("commit");
        } catch (Exception e) {
            SnowSession.getSession().sql("rollback");
        }
        return res;
    }
}
