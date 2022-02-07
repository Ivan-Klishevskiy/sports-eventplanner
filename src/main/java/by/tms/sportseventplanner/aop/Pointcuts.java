package by.tms.sportseventplanner.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut(value = "execution(* by.tms.sportseventplanner.controller.*.*(..))" +
            "||execution(* by.tms.sportseventplanner.service.EventService.*(..))" +
            "||execution(* by.tms.sportseventplanner.service.OrganizationService.*(..))" +
            "||execution(* by.tms.sportseventplanner.service.CommentService.*(..))"+
            "||execution(* by.tms.sportseventplanner.utils.*.*(..))")
    public void controllerPointcut() {
    }
}
