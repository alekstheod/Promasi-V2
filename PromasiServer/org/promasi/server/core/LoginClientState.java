/**
 *
 */
package org.promasi.server.core;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.net.ProtocolException;
import org.apache.commons.lang.NullArgumentException;
import org.promasi.protocol.request.LoginRequest;
import org.promasi.protocol.response.LoginResponse;


/**
 * @author m1cRo
 *
 */
public class LoginClientState implements IClientState
{
	/**
	 *
	 */
	private ProMaSi _promasi;

	/**
	 *
	 * @param promasi
	 */
	public LoginClientState(ProMaSi promasi)
	{
		if(promasi==null)
		{
			throw new NullArgumentException("Wrong argument promasi");
		}
		_promasi=promasi;
	}

	/**
	 *
	 */
	@Override
	public void onReceive(ProMaSiClient client, String recData)throws ProtocolException
	{
		XMLDecoder decoder=new XMLDecoder(new ByteArrayInputStream(recData.getBytes()));
		try
		{
			Object object=decoder.readObject();
			decoder.close();
			if(object instanceof LoginRequest)
			{
				LoginRequest loginRequest=(LoginRequest)object;
				client.setClientId(loginRequest.getUserName());
				_promasi.addUser(client);
				client.sendData(new LoginResponse().toXML());
				client.changeState(new SelectGameClientState(_promasi));
			}
			else
			{
				throw new ProtocolException("Wrong protocol");
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			client.sendData(new LoginResponse(false,e.getMessage()).toXML());
			throw new ProtocolException("Wrong protocol - " + e.getMessage());
		}
		catch(NullArgumentException e)
		{
			client.sendData(new LoginResponse(false,e.getMessage()).toXML());
			throw new ProtocolException("Wrong protocol - " + e.getMessage());
		}
		catch(IllegalArgumentException e)
		{
			client.sendData(new LoginResponse().toXML());
			throw new ProtocolException("Wrong protocol - " + e.getMessage());
		}
	}
}
