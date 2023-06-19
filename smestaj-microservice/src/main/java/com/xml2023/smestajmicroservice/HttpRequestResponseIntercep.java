//package com.xml2023.smestajmicroservice;
//
//import io.micrometer.core.instrument.DistributionSummary;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Component
//public class HttpRequestResponseIntercep implements HandlerInterceptor {
//
//    private static final Logger logger = LoggerFactory.getLogger(HttpRequestResponseIntercep.class);
//
//    @Autowired
//    private DistributionSummary httpReqSizeDistribution;
//
//    @Autowired
//    private DistributionSummary httpResSizeDistribution;
//
//	@Override
//	public boolean preHandle(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
//		return true;
//	}
//
//	@Override
//	public void postHandle(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView)
//			throws Exception {
//		try {
//            int reqSize = request.getContentLength();
//            if (reqSize > 0) {
//                httpReqSizeDistribution.record(reqSize);
//            }
//        } catch (Exception e) {
//            logger.warn("Cannot get request content length", e);
//        }
//
//        if (response.getBufferSize() > 0) {
//            httpResSizeDistribution.record(response.getBufferSize());
//        }
//	}
//
//	@Override
//	public void afterCompletion(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//	}
//
//   
//}
