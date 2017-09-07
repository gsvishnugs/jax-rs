package org.vgs.accountswa.model;

public class AccountsResponse {

	private Object entity;

	private String message = "Success";

	public AccountsResponse() {
	}

	public AccountsResponse(Object entity, String message) {
		super();
		this.setEntity(entity);
		this.setMessage(message);
	}

	/**
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
