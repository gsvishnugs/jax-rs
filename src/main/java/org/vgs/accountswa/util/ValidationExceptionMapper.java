package org.vgs.accountswa.util;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.vgs.accountswa.model.AccountsResponse;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

	@Override
	public Response toResponse(ResteasyViolationException exception) {
		final StringBuilder strBuilder = new StringBuilder();
		for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			if (strBuilder.length() > 0) {
				strBuilder.append("<br/>");
			}
			strBuilder.append(cv.getMessage());
		}
		return Response.status(Status.BAD_REQUEST.getStatusCode())
				.entity(new AccountsResponse(null, strBuilder.toString())).build();
	}
}