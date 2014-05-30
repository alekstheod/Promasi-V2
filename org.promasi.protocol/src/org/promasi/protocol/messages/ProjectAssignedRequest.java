//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.30 at 11:14:44 PM CEST 
//


package org.promasi.protocol.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.promasi.game.model.generated.CompanyModel;
import org.promasi.game.model.generated.ProjectModel;


/**
 * <p>Java class for projectAssignedRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="projectAssignedRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}message">
 *       &lt;sequence>
 *         &lt;element name="company" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{}aGameModel">
 *                 &lt;sequence>
 *                   &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="_description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="_startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="_endTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="_itDepartment" type="{}departmentModel" minOccurs="0"/>
 *                   &lt;element name="_budget" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="_prestigePoints" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="project" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{}aGameModel">
 *                 &lt;sequence>
 *                   &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="_description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="_projectDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="_overallProgress" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="_projectPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="_difficultyLevel" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="_projectTasks">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="value" type="{}projectTaskModel" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="_taskBridges" type="{}taskBridgeModel" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "projectAssignedRequest", propOrder = {
    "company",
    "dateTime",
    "project"
})
public class ProjectAssignedRequest
    extends Message
{

    protected CompanyModel company;
    protected String dateTime;
    protected ProjectModel project;

    ProjectAssignedRequest(){}
    public ProjectAssignedRequest(CompanyModel company, ProjectModel project, String dateTime) {
       this.company = company;
       this.project = project;
       this.dateTime = dateTime;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link ProjectAssignedRequest.Company }
     *     
     */
    public CompanyModel getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectAssignedRequest.Company }
     *     
     */
    public void setCompany(CompanyModel value) {
        this.company = value;
    }

    /**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTime(String value) {
        this.dateTime = value;
    }

    /**
     * Gets the value of the project property.
     * 
     * @return
     *     possible object is
     *     {@link ProjectAssignedRequest.Project }
     *     
     */
    public ProjectModel getProject() {
        return project;
    }

    /**
     * Sets the value of the project property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectAssignedRequest.Project }
     *     
     */
    public void setProject(ProjectModel value) {
        this.project = value;
    }
}