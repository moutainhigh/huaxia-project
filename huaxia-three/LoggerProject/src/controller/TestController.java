package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import test.TestLogger;
/*测试日志和页面的跳转*/
@Controller
@RequestMapping("test")
public class TestController {
	public static Logger logger = Logger.getLogger(TestController.class);
	/**测试日志 */
	@RequestMapping("abc")
	public  String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		for(int i=0;i<100000;i++){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hello World!"+i);
			logger.trace("Hello World,trace"+i);
			logger.debug("Hello World,debug"+i);
			logger.info("Hello World,info"+i);
			logger.warn("Hello World,warn"+i);
			logger.error("Hello World,error"+i);
			logger.fatal("Hello World,fatal"+i);
			if(i==(100000-1) )
			{
				i=0;
			}
		}
		return "abc";	
	}
}
