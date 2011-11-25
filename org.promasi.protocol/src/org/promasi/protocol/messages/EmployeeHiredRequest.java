/**
 * 
 */
package org.promasi.protocol.messages;

import org.promasi.game.company.CompanyMemento;
import org.promasi.game.company.EmployeeMemento;
import org.promasi.game.company.MarketPlaceMemento;
import org.promasi.utilities.serialization.SerializableObject;

/**
 * @author m1cRo
 *
 */
public class EmployeeHiredRequest extends SerializableObject 
{
	/**
	 * 
	 */
	private CompanyMemento _company;
	
	/**
	 * 
	 */
	private MarketPlaceMemento _marketPlace;

	/**
	 * 
	 */
	private EmployeeMemento _employee;
	
	/**
	 * 
	 */
	private String _dateTime;
	
	/**
	 * 
	 */
	public EmployeeHiredRequest(){}
	
	/**
	 * 
	 * @param company
	 * @param marketPlace
	 * @param dateTime
	 */
	public EmployeeHiredRequest( MarketPlaceMemento marketPlace, CompanyMemento company, EmployeeMemento employee, String dateTime){
		_company=company;
		_marketPlace=marketPlace;
		_dateTime=dateTime;
		_employee=employee;
	}
	
	/**
	 * @param company the company to set
	 */
	public void setCompany(CompanyMemento company) {
		_company = company;
	}

	/**
	 * @return the company
	 */
	public CompanyMemento getCompany() {
		return _company;
	}

	/**
	 * @param marketPlace the marketPlace to set
	 */
	public void setMarketPlace(MarketPlaceMemento marketPlace) {
		_marketPlace = marketPlace;
	}

	/**
	 * @return the marketPlace
	 */
	public MarketPlaceMemento getMarketPlace() {
		return _marketPlace;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		_dateTime = dateTime;
	}

	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return _dateTime;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeMemento employee) {
		_employee = employee;
	}

	/**
	 * @return the employee
	 */
	public EmployeeMemento getEmployee() {
		return _employee;
	}
}
