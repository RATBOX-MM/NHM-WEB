package com.rbx.nhm.web.interceptors;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

@MessageHandler
@Interceptor
public class MessageHandlerInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Properties properties;

	public MessageHandlerInterceptor() {
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream("/VerificationMessage.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		Object obj = null;
		try {
			obj = ic.proceed();
		} catch (NHMException e) {
			
			List<Message> messages = e.getMessages();
			
			if(null == messages || messages.size() == 0) {
				FacesMessage message = getMessage(e.getMessageId(), e.getParams());
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				for(Message m : messages) {
					FacesContext.getCurrentInstance().addMessage(null, getMessage(m));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	private FacesMessage getMessage(Message message) {
		return getMessage(message.getMessageId(), message.getParams());
	}

	private FacesMessage getMessage(String messageId, Object [] params) {
		return new FacesMessage("", MessageFormat.format(properties.getProperty(messageId), params));
	}
}
