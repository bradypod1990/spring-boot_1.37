package com.feng.advice;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LogAspect {

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void pointCut() {}
	
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		boolean isLog = true;
		Signature signature = joinPoint.getSignature();
		if(!(signature instanceof MethodSignature)) {
			throw new IllegalAccessException("只支持在方法上加注解");
		}
		//根据方法上的注解判断是否需要打印日志
		MethodSignature methodSignature = (MethodSignature)signature;
		Method method = methodSignature.getMethod();
		Log logg = AnnotationUtils.getAnnotation(method, Log.class);
		if(logg != null) {
			isLog = !logg.ignore();
		}
		Date startDate = new Date();
		if(isLog) {
			//获取请求URI
			ServletRequestAttributes requestAttr =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			String uri = requestAttr.getRequest().getRequestURI();
			//获取参数
			Object[] args = joinPoint.getArgs();
			//获取方法名
			String methodName = joinPoint.getSignature().getName();
			//获取IP地址
			String ip = getIpAddr(requestAttr.getRequest());
			log.info("时间{} IP地址:{} ;请求URI: {}  ; 方法名：{};方法参数{}", startDate, ip, uri, methodName, Arrays.toString(args));
			
		}
		result = joinPoint.proceed();
		if(isLog) {
			Date endDate = new Date();
			//获取方法名
			String methodName = joinPoint.getSignature().getName();
			log.info("时间{}  方法名:{}  执行结束，执行时间为:{}秒", endDate, methodName, (endDate.getTime()-startDate.getTime())/1000);
		}
		return result;
	}
	
	/**
     * 指定拦截器规则；也可直接使用within(@org.springframework.web.bind.annotation.RestController *)
     * 这样简单点 可以通用
     * @param 异常对象
     */
	 @AfterThrowing(pointcut="pointCut()",throwing="e")
	 public void afterThrowable(Throwable e) {
	        log.error("切面发生了异常：", e);
	        //这里可以做个统一异常处理
	        //自定义一个异常 包装后排除
	        //throw new AopException("xxx);
	 }
	public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error("获取ip异常：{}" ,e.getMessage());
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                                                                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }    
}
