package JavaMultiThreadProgramming.ReentrantLock.Condition.ConditionTest;

import java.util.concurrent.locks.ReentrantLock;

public class Service {
	private ReentrantLock lock = new ReentrantLock();
    public void serviceMethod1(){
    	try{
    		lock.lock();
    		System.out.println("ServiceMethod1 getHoldCounnt="+lock.getHoldCount()) ;
    		serviceMethod2();
    	}finally{
    		lock.unlock();
    	}
    }
    public void serviceMethod2(){
    	try{
    		lock.lock();
    		System.out.println("ServiceMethod2 getHoldCounnt="+lock.getHoldCount()) ;
    	}finally{
    		lock.unlock();
    	}
    }
    public static  void main(String args[]){
    	Service service = new Service();
    	service.serviceMethod1();
    }
}
