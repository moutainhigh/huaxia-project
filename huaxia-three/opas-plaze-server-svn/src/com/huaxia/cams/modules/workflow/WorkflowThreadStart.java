package com.huaxia.cams.modules.workflow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WorkflowThreadStart extends HttpServlet {
	
	private static final long serialVersionUID = 6728543954220936123L;

	public void init() throws ServletException{
		
		startWorkflowCallbackThreadTierNode21();
		startWorkflowCallbackThreadTierNode22();
		startWorkflowCallbackThreadTierNode23();
		
	}
	
	private void startWorkflowCallbackThreadTierNode21(){
		Thread thread = new Thread(new WorkflowCallbackThreadTierNode21());
	    thread.setDaemon(true);
	    thread.start();
	}
	
	private void startWorkflowCallbackThreadTierNode22(){
		Thread thread = new Thread(new WorkflowCallbackThreadTierNode22());
	    thread.setDaemon(true);
	    thread.start();
	}
	
	private void startWorkflowCallbackThreadTierNode23(){
		Thread thread = new Thread(new WorkflowCallbackThreadTierNode23());
	    thread.setDaemon(true);
	    thread.start();
	}

}
