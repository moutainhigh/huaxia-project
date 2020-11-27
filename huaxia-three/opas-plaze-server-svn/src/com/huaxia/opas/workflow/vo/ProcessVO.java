
package com.huaxia.opas.workflow.vo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ProcessVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deadLine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="endActivityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="executionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="initiatorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="initiatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="initiatorRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overdue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ownerUnitId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="processId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="superProcessId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templateId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templeteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templeteVersion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessVO", propOrder = {
    "deadLine",
    "duration",
    "endActivityName",
    "endTime",
    "executionId",
    "initiatorId",
    "initiatorName",
    "initiatorRole",
    "overdue",
    "ownerUnitId",
    "packageId",
    "priority",
    "processId",
    "startTime",
    "status",
    "subject",
    "superProcessId",
    "templateId",
    "templeteName",
    "templeteVersion"
})
public class ProcessVO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deadLine;
    @XmlElementRef(name = "duration", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> duration;
    @XmlElementRef(name = "endActivityName", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> endActivityName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    @XmlElementRef(name = "executionId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> executionId;
    @XmlElementRef(name = "initiatorId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> initiatorId;
    @XmlElementRef(name = "initiatorName", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> initiatorName;
    @XmlElementRef(name = "initiatorRole", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> initiatorRole;
    @XmlElementRef(name = "overdue", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> overdue;
    @XmlElementRef(name = "ownerUnitId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ownerUnitId;
    @XmlElementRef(name = "packageId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> packageId;
    protected Integer priority;
    @XmlElementRef(name = "processId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> processId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlElementRef(name = "status", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> status;
    @XmlElementRef(name = "subject", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subject;
    @XmlElementRef(name = "superProcessId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> superProcessId;
    @XmlElementRef(name = "templateId", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> templateId;
    @XmlElementRef(name = "templeteName", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> templeteName;
    @XmlElementRef(name = "templeteVersion", namespace = "http://vo.flowmodel.engine.topbpm.huateng.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> templeteVersion;

    /**
     * Gets the value of the deadLine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeadLine() {
        return deadLine;
    }

    /**
     * Sets the value of the deadLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeadLine(XMLGregorianCalendar value) {
        this.deadLine = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setDuration(JAXBElement<Long> value) {
        this.duration = value;
    }

    /**
     * Gets the value of the endActivityName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEndActivityName() {
        return endActivityName;
    }

    /**
     * Sets the value of the endActivityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEndActivityName(JAXBElement<String> value) {
        this.endActivityName = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the executionId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExecutionId() {
        return executionId;
    }

    /**
     * Sets the value of the executionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExecutionId(JAXBElement<String> value) {
        this.executionId = value;
    }

    /**
     * Gets the value of the initiatorId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInitiatorId() {
        return initiatorId;
    }

    /**
     * Sets the value of the initiatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInitiatorId(JAXBElement<String> value) {
        this.initiatorId = value;
    }

    /**
     * Gets the value of the initiatorName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInitiatorName() {
        return initiatorName;
    }

    /**
     * Sets the value of the initiatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInitiatorName(JAXBElement<String> value) {
        this.initiatorName = value;
    }

    /**
     * Gets the value of the initiatorRole property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInitiatorRole() {
        return initiatorRole;
    }

    /**
     * Sets the value of the initiatorRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInitiatorRole(JAXBElement<String> value) {
        this.initiatorRole = value;
    }

    /**
     * Gets the value of the overdue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getOverdue() {
        return overdue;
    }

    /**
     * Sets the value of the overdue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setOverdue(JAXBElement<Integer> value) {
        this.overdue = value;
    }

    /**
     * Gets the value of the ownerUnitId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOwnerUnitId() {
        return ownerUnitId;
    }

    /**
     * Sets the value of the ownerUnitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOwnerUnitId(JAXBElement<String> value) {
        this.ownerUnitId = value;
    }

    /**
     * Gets the value of the packageId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPackageId() {
        return packageId;
    }

    /**
     * Sets the value of the packageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPackageId(JAXBElement<String> value) {
        this.packageId = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriority(Integer value) {
        this.priority = value;
    }

    /**
     * Gets the value of the processId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProcessId() {
        return processId;
    }

    /**
     * Sets the value of the processId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProcessId(JAXBElement<String> value) {
        this.processId = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setStatus(JAXBElement<Integer> value) {
        this.status = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubject(JAXBElement<String> value) {
        this.subject = value;
    }

    /**
     * Gets the value of the superProcessId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSuperProcessId() {
        return superProcessId;
    }

    /**
     * Sets the value of the superProcessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSuperProcessId(JAXBElement<String> value) {
        this.superProcessId = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTemplateId() {
        return templateId;
    }

    /**
     * Sets the value of the templateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTemplateId(JAXBElement<String> value) {
        this.templateId = value;
    }

    /**
     * Gets the value of the templeteName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTempleteName() {
        return templeteName;
    }

    /**
     * Sets the value of the templeteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTempleteName(JAXBElement<String> value) {
        this.templeteName = value;
    }

    /**
     * Gets the value of the templeteVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getTempleteVersion() {
        return templeteVersion;
    }

    /**
     * Sets the value of the templeteVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setTempleteVersion(JAXBElement<Integer> value) {
        this.templeteVersion = value;
    }

}
