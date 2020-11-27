package MyService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MyServiceInterface  {

	@WebMethod
	String invoke();
}
