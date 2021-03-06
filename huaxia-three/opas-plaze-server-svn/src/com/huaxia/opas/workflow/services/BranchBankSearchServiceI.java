
package com.huaxia.opas.workflow.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BranchBankSearchServiceI", targetNamespace = "http://services", wsdlLocation = "http://106.128.30.108:8888/topbpm_test/services/BranchBankSearchServiceI?wsdl")
public class BranchBankSearchServiceI
    extends Service
{

    private final static URL BRANCHBANKSEARCHSERVICEI_WSDL_LOCATION;
    private final static WebServiceException BRANCHBANKSEARCHSERVICEI_EXCEPTION;
    private final static QName BRANCHBANKSEARCHSERVICEI_QNAME = new QName("http://services", "BranchBankSearchServiceI");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://106.128.30.108:8888/topbpm_test/services/BranchBankSearchServiceI?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BRANCHBANKSEARCHSERVICEI_WSDL_LOCATION = url;
        BRANCHBANKSEARCHSERVICEI_EXCEPTION = e;
    }

    public BranchBankSearchServiceI() {
        super(__getWsdlLocation(), BRANCHBANKSEARCHSERVICEI_QNAME);
    }

    public BranchBankSearchServiceI(WebServiceFeature... features) {
        super(__getWsdlLocation(), BRANCHBANKSEARCHSERVICEI_QNAME, features);
    }

    public BranchBankSearchServiceI(URL wsdlLocation) {
        super(wsdlLocation, BRANCHBANKSEARCHSERVICEI_QNAME);
    }

    public BranchBankSearchServiceI(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BRANCHBANKSEARCHSERVICEI_QNAME, features);
    }

    public BranchBankSearchServiceI(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BranchBankSearchServiceI(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BranchBankSearchServiceIPortType
     */
    @WebEndpoint(name = "BranchBankSearchServiceIHttpPort")
    public BranchBankSearchServiceIPortType getBranchBankSearchServiceIHttpPort() {
        return super.getPort(new QName("http://services", "BranchBankSearchServiceIHttpPort"), BranchBankSearchServiceIPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BranchBankSearchServiceIPortType
     */
    @WebEndpoint(name = "BranchBankSearchServiceIHttpPort")
    public BranchBankSearchServiceIPortType getBranchBankSearchServiceIHttpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services", "BranchBankSearchServiceIHttpPort"), BranchBankSearchServiceIPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BRANCHBANKSEARCHSERVICEI_EXCEPTION!= null) {
            throw BRANCHBANKSEARCHSERVICEI_EXCEPTION;
        }
        return BRANCHBANKSEARCHSERVICEI_WSDL_LOCATION;
    }

}
