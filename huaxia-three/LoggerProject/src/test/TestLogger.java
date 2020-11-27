package test;
import org.apache.log4j.Logger;
/**
 * 测试Logger的日志输出
 * @author liuwei
 */
public class TestLogger {
	public static Logger logger = Logger.getLogger(TestLogger.class);
	public static void main(String args[]){
		logger.trace("Hello World,trace");
		logger.debug("Hello World,debug");
		logger.info("Hello World,info");
		logger.warn("Hello World,warn");
		logger.error("Hello World,error");
		logger.fatal("Hello World,fatal");
	}
}
