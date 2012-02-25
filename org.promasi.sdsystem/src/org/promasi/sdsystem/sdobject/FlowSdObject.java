/**
 * 
 */
package org.promasi.sdsystem.sdobject;

import java.util.Map;
import java.util.TreeMap;

import org.promasi.sdsystem.SdSystemException;
import org.promasi.sdsystem.sdobject.equation.IEquation;
import org.promasi.sdsystem.serialization.ISerializableSdObject;
import org.promasi.utilities.exceptions.NullArgumentException;


/**
 * @author m1cRo
 *
 */
public class FlowSdObject implements ISdObject 
{
	/**
	 * 
	 */
	protected IEquation _equation;
	
	/**
	 * 
	 */
	protected Double _value=0.0;
	
	/**
	 * 
	 * @param equation
	 * @throws NullArgumentException
	 */
	public FlowSdObject(final IEquation equation)throws NullArgumentException
	{
		if(equation==null)
		{
			throw new NullArgumentException("Wrong argument equation==null");
		}

		_equation=equation;
	}

	
	/* (non-Javadoc)
	 * @see org.promasi.sdsystem.sdobject.ISdObject#executeStep(java.util.Map)
	 */
	@Override
	public boolean executeStep(Map<String, ISdObject> systemSdObjects) {
		try{
			Map<String, Double> systemValues=new TreeMap<String, Double>();
			for(Map.Entry<String, ISdObject> entry : systemSdObjects.entrySet())
			{
				systemValues.put(entry.getKey(), entry.getValue().getValue());
			}
			
			_value=_equation.calculateEquation(systemValues);
		}catch(SdSystemException e){
			return false;
		}
		
		return true;
	}

	
	/* (non-Javadoc)
	 * @see org.promasi.sdsystem.sdobject.ISdObject#getValue()
	 */
	@Override
	public Double getValue() {
		return _value;
	}


	@Override
	public ISerializableSdObject getMemento() {
		return new FlowSdObjectMemento(this);
	}
}
