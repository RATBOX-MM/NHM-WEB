package com.hxo.nhm.web.utilities;

import java.util.List;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NHMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String messageId;

	private Object[] params;

	private List<Message> messages;

	public NHMException (List<Message> messages) {
		super();
		this.messages = messages;
	}

	public NHMException (String message, Throwable cause) {
		super(cause);
		this.messageId = message;
	}

	public NHMException (String message) {
		super();
		this.messageId = message;
	}

	public String getMessageId() {
		return messageId;
	}

	public NHMException (String messageId, Object... params) {
		super();
		this.messageId = messageId;
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public static class Message {
		private String messageId;
		private Object[] params;

		public Message() {
		}

		public Message(String messageId, Object ... params) {
			super();
			this.messageId = messageId;
			this.params = params;
		}

		public String getMessageId() {
			return messageId;
		}

		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}

		public Object[] getParams() {
			return params;
		}

		public void setParams(Object[] params) {
			this.params = params;
		}

	}
	
}
