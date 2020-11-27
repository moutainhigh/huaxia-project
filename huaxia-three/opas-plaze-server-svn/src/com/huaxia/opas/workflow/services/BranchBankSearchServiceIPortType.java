
package com.huaxia.opas.workflow.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.huaxia.opas.workflow.vo.ProcessVO;



/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BranchBankSearchServiceIPortType", targetNamespace = "http://services")
@XmlSeeAlso({
	com.huaxia.opas.workflow.vo.ObjectFactory.class,
	com.huaxia.opas.workflow.services.ObjectFactory.class
})
public interface BranchBankSearchServiceIPortType {


    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "committs", targetNamespace = "http://services", className = "services.Committs")
    @ResponseWrapper(localName = "committsResponse", targetNamespace = "http://services", className = "services.CommittsResponse")
    public String committs(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "searchTask", targetNamespace = "http://services", className = "services.SearchTask")
    @ResponseWrapper(localName = "searchTaskResponse", targetNamespace = "http://services", className = "services.SearchTaskResponse")
    public String searchTask(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "signal", targetNamespace = "http://services", className = "services.Signal")
    @ResponseWrapper(localName = "signalResponse", targetNamespace = "http://services", className = "services.SignalResponse")
    public String signal(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "peopleTask", targetNamespace = "http://services", className = "services.PeopleTask")
    @ResponseWrapper(localName = "peopleTaskResponse", targetNamespace = "http://services", className = "services.PeopleTaskResponse")
    public String peopleTask(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "takeTasks", targetNamespace = "http://services", className = "services.TakeTasks")
    @ResponseWrapper(localName = "takeTasksResponse", targetNamespace = "http://services", className = "services.TakeTasksResponse")
    public String takeTasks(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     */
    @WebMethod
    @RequestWrapper(localName = "getRejectableTargets", targetNamespace = "http://services", className = "services.GetRejectableTargets")
    @ResponseWrapper(localName = "getRejectableTargetsResponse", targetNamespace = "http://services", className = "services.GetRejectableTargetsResponse")
    public void getRejectableTargets(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     */
    @WebMethod
    @RequestWrapper(localName = "rejectTask", targetNamespace = "http://services", className = "services.RejectTask")
    @ResponseWrapper(localName = "rejectTaskResponse", targetNamespace = "http://services", className = "services.RejectTaskResponse")
    public void rejectTask(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "commitTask", targetNamespace = "http://services", className = "services.CommitTask")
    @ResponseWrapper(localName = "commitTaskResponse", targetNamespace = "http://services", className = "services.CommitTaskResponse")
    public String commitTask(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "waibao", targetNamespace = "http://services", className = "services.Waibao")
    @ResponseWrapper(localName = "waibaoResponse", targetNamespace = "http://services", className = "services.WaibaoResponse")
    public String waibao(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns com.huateng.topbpm.engine.flowmodel.vo.ProcessVO
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "startProcess1", targetNamespace = "http://services", className = "services.StartProcess1")
    @ResponseWrapper(localName = "startProcess1Response", targetNamespace = "http://services", className = "services.StartProcess1Response")
    public ProcessVO startProcess1(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns long
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "searchTaskCount", targetNamespace = "http://services", className = "services.SearchTaskCount")
    @ResponseWrapper(localName = "searchTaskCountResponse", targetNamespace = "http://services", className = "services.SearchTaskCountResponse")
    public long searchTaskCount(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "takeTask", targetNamespace = "http://services", className = "services.TakeTask")
    @ResponseWrapper(localName = "takeTaskResponse", targetNamespace = "http://services", className = "services.TakeTaskResponse")
    public String takeTask(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

    /**
     * 
     * @param in0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://services")
    @RequestWrapper(localName = "getProcessStatus", targetNamespace = "http://services", className = "services.GetProcessStatus")
    @ResponseWrapper(localName = "getProcessStatusResponse", targetNamespace = "http://services", className = "services.GetProcessStatusResponse")
    public String getProcessStatus(
        @WebParam(name = "in0", targetNamespace = "http://services")
        String in0);

}
