
package com.huaxia.opas.workflow.vo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.huateng.topbpm.engine.flowmodel.vo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessVOTempleteName_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "templeteName");
    private final static QName _ProcessVOExecutionId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "executionId");
    private final static QName _ProcessVOPackageId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "packageId");
    private final static QName _ProcessVOInitiatorId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "initiatorId");
    private final static QName _ProcessVOEndActivityName_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "endActivityName");
    private final static QName _ProcessVOSuperProcessId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "superProcessId");
    private final static QName _ProcessVOInitiatorRole_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "initiatorRole");
    private final static QName _ProcessVOProcessId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "processId");
    private final static QName _ProcessVOStatus_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "status");
    private final static QName _ProcessVOSubject_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "subject");
    private final static QName _ProcessVOOwnerUnitId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "ownerUnitId");
    private final static QName _ProcessVOTemplateId_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "templateId");
    private final static QName _ProcessVODuration_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "duration");
    private final static QName _ProcessVOTempleteVersion_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "templeteVersion");
    private final static QName _ProcessVOOverdue_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "overdue");
    private final static QName _ProcessVOInitiatorName_QNAME = new QName("http://vo.flowmodel.engine.topbpm.huateng.com", "initiatorName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.huateng.topbpm.engine.flowmodel.vo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessVO }
     * 
     */
    public ProcessVO createProcessVO() {
        return new ProcessVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "templeteName", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOTempleteName(String value) {
        return new JAXBElement<String>(_ProcessVOTempleteName_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "executionId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOExecutionId(String value) {
        return new JAXBElement<String>(_ProcessVOExecutionId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "packageId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOPackageId(String value) {
        return new JAXBElement<String>(_ProcessVOPackageId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "initiatorId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOInitiatorId(String value) {
        return new JAXBElement<String>(_ProcessVOInitiatorId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "endActivityName", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOEndActivityName(String value) {
        return new JAXBElement<String>(_ProcessVOEndActivityName_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "superProcessId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOSuperProcessId(String value) {
        return new JAXBElement<String>(_ProcessVOSuperProcessId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "initiatorRole", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOInitiatorRole(String value) {
        return new JAXBElement<String>(_ProcessVOInitiatorRole_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "processId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOProcessId(String value) {
        return new JAXBElement<String>(_ProcessVOProcessId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "status", scope = ProcessVO.class)
    public JAXBElement<Integer> createProcessVOStatus(Integer value) {
        return new JAXBElement<Integer>(_ProcessVOStatus_QNAME, Integer.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "subject", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOSubject(String value) {
        return new JAXBElement<String>(_ProcessVOSubject_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "ownerUnitId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOOwnerUnitId(String value) {
        return new JAXBElement<String>(_ProcessVOOwnerUnitId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "templateId", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOTemplateId(String value) {
        return new JAXBElement<String>(_ProcessVOTemplateId_QNAME, String.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "duration", scope = ProcessVO.class)
    public JAXBElement<Long> createProcessVODuration(Long value) {
        return new JAXBElement<Long>(_ProcessVODuration_QNAME, Long.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "templeteVersion", scope = ProcessVO.class)
    public JAXBElement<Integer> createProcessVOTempleteVersion(Integer value) {
        return new JAXBElement<Integer>(_ProcessVOTempleteVersion_QNAME, Integer.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "overdue", scope = ProcessVO.class)
    public JAXBElement<Integer> createProcessVOOverdue(Integer value) {
        return new JAXBElement<Integer>(_ProcessVOOverdue_QNAME, Integer.class, ProcessVO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", name = "initiatorName", scope = ProcessVO.class)
    public JAXBElement<String> createProcessVOInitiatorName(String value) {
        return new JAXBElement<String>(_ProcessVOInitiatorName_QNAME, String.class, ProcessVO.class, value);
    }

}
