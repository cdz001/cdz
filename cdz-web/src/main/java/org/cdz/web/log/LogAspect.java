package org.cdz.web.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.cdz.iservice.LogService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.cdz.Log;

@Aspect
@Component
public class LogAspect {
	
	@Reference
	private LogService logService;

	@Pointcut("@annotation(org.cdz.web.log.Logg)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveLog(point, time);
		return result;
	}

	private void saveLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Logg syslog = method.getAnnotation(Logg.class);
		Log log = new Log();
		if (log != null) {	
			// 注解上的描述
			log.setContent(syslog.value());
		}
		// 请求的方法名
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = signature.getName();
		log.setClassName(className);
		log.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
//        }
		StringBuilder params = new StringBuilder();  
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		params.append("请求方法："+request.getMethod()+" ");
        if (args !=  null && args.length > 0) {  
            for ( int i = 0; i < args.length; i++) {
            	if(args[i] instanceof HttpServletRequest){
                	params.append("参数："+request.getQueryString());
            	}else{
            		
            		params.append("参数："+JSONObject.toJSONString(args[i])); 
            	}
               
           }  
       }  
        log.setParam(params.toString());
        log.setUsername("zhangsan");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentTime = sdf.format(new Date());
		log.setCreateTime(currentTime);
		// 保存系统日志
		logService.save(log);
	}
}
