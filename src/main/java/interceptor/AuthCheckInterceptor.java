package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthCheckInterceptor implements HandlerInterceptor{
	 
	 @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 System.out.println("preHandle()");
		 
		 HttpSession session = request.getSession(false);//세션이 존재하지않으면 null을 반환
		 if(session != null) {
			 Object object = session.getAttribute("authInfo");
			 if(object != null) {
				 return true; // 컨트롤러 메서드로 진행시킴 
			 }
		 }
		 
		 response.sendRedirect(request.getContextPath() + "/login");
		 return false; // 진행시키지 않고 종료 
		 
	}
	 
	 @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		 System.out.println("postHandle");
		 HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	 @Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		 System.out.println("afterCompletion");
		 HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}
