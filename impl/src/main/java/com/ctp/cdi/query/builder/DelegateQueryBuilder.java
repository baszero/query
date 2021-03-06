package com.ctp.cdi.query.builder;

import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import com.ctp.cdi.query.handler.EntityDaoHandler;
import com.ctp.cdi.query.handler.QueryInvocationContext;
import com.ctp.cdi.query.meta.MethodType;
import com.ctp.cdi.query.meta.QueryInvocation;

@QueryInvocation(MethodType.DELEGATE)
public class DelegateQueryBuilder extends QueryBuilder {

    @Override
    public Object execute(QueryInvocationContext context) throws Exception {
        InvocationContext invocation = context.getInvocation();
        if (EntityDaoHandler.contains(invocation.getMethod())) {
            return callEntityHandler(invocation, context.getEntityClass(), context.getEntityManager());
        }
        return invocation.proceed();
    }
    
    private Object callEntityHandler(InvocationContext ctx, Class<?> entityClass, EntityManager entityManager)
            throws Exception {
        return EntityDaoHandler.create(entityManager, entityClass).invoke(ctx.getMethod(), ctx.getParameters());
    }

}
