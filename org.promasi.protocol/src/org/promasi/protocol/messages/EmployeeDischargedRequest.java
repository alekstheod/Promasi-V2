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
import org.promasi.game.model.generated.EmployeeModel;
import org.promasi.game.model.generated.MarketPlaceModel;
import org.promasi.protocol.client.Protocol;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeDischargedRequest", propOrder = {
    "company",
    "dateTime",
    "employee",
    "marketPlace"
})
public class EmployeeDischargedRequest
        extends Message {

    protected CompanyModel company;
    protected String dateTime;
    protected EmployeeModel employee;
    protected MarketPlaceModel marketPlace;

    public EmployeeDischargedRequest() {
    }

    public EmployeeDischargedRequest(MarketPlaceModel marketPlace, CompanyModel company, EmployeeModel employee, String dateTime) {
        this.company = company;
        this.marketPlace = marketPlace;
        this.employee = employee;
        this.dateTime = dateTime;
    }

    @Override
    public Message dispatch(Protocol protocol) {
        return protocol.dispatch(this);
    }

    /**
     * Gets the value of the company property.
     *
     * @return possible object is {@link EmployeeDischargedRequest.Company }
     *
     */
    public CompanyModel getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     *
     * @param value allowed object is {@link EmployeeDischargedRequest.Company }
     *
     */
    public void setCompany(CompanyModel value) {
        this.company = value;
    }

    /**
     * Gets the value of the dateTime property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDateTime(String value) {
        this.dateTime = value;
    }

    /**
     * Gets the value of the employee property.
     *
     * @return possible object is {@link EmployeeModel }
     *
     */
    public EmployeeModel getEmployee() {
        return employee;
    }

    /**
     * Sets the value of the employee property.
     *
     * @param value allowed object is {@link EmployeeModel }
     *
     */
    public void setEmployee(EmployeeModel value) {
        this.employee = value;
    }

    /**
     * Gets the value of the marketPlace property.
     *
     * @return possible object is {@link EmployeeDischargedRequest.MarketPlace }
     *
     */
    public MarketPlaceModel getMarketPlace() {
        return marketPlace;
    }

    /**
     * Sets the value of the marketPlace property.
     *
     * @param value allowed object is
     *     {@link EmployeeDischargedRequest.MarketPlace }
     *
     */
    public void setMarketPlace(MarketPlaceModel value) {
        this.marketPlace = value;
    }
}
