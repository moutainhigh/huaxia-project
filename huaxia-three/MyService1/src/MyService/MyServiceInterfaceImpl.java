package MyService;

import java.net.URL;

import javax.jws.WebService;

import org.codehaus.xfire.client.Client;
import org.springframework.stereotype.Service;

@Service
@WebService(serviceName = "T001500", endpointInterface = "MyService.MyServiceInterface")
public class MyServiceInterfaceImpl implements MyServiceInterface {

	@Override
	public String invoke() {
		// TODO Auto-generated method stub
		System.out.println("Hello World1£¡£¡£¡");
		Client client = null;
		try {
			
			client = new Client(new URL("http://localhost:8080/MyClientOne/webservice/T001600?wsdl"));
			Object[] obj = new Object[] { "Hello World!" };
			Object[] result = client.invoke("invoke", obj);
			System.out.println(result[0].toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello World!1Hello World!1Hello World!1";
	}

}
