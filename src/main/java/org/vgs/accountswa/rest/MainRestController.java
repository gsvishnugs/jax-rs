/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vgs.accountswa.rest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.vgs.accountswa.model.Entry;
import org.vgs.accountswa.util.AccountService;

@Path("/")
public class MainRestController {

	@Inject
	AccountService accountService;

	@GET
	@Path("/entries")
	@Produces({ "application/json" })
	public Response getAllEntries() throws Exception {
		return Response.ok(accountService.getAllEntries(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/autocomplete/descriptions")
	@Produces({ "application/json" })
	public Response autocompleteDesc(@QueryParam("text") @Size(min = 4) String text) throws Exception {
		return Response.ok(accountService.getAutocompleteDesc(text), MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path("/entry")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response createEntry(@Valid Entry entry) throws Exception {
		accountService.createEntry(entry);
		return Response.ok("Success", MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/entry/{id}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response deleteEntry(@PathParam(value="id") Long id) throws Exception {
		accountService.deleteEntry(id);
		return Response.ok(null, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/insights/debits")
	@Produces({ "application/json" })
	public Response getDebitsInsight() throws Exception {
		return Response.ok(accountService.getDebitsInsight(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/insights/debits/{txName}")
	@Produces({ "application/json" })
	public Response getDebitsDetaisInsight(@PathParam(value = "txName") String txName) throws Exception {
		return Response.ok(accountService.getDebitsDetailsInsight(txName), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/insights/credits")
	@Produces({ "application/json" })
	public Response getCreditsInsight() throws Exception {
		return Response.ok(accountService.getCreditsInsight(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/insights/credits/{txName}")
	@Produces({ "application/json" })
	public Response getCreditsDetaisInsight(@PathParam(value = "txName") String txName) throws Exception {
		return Response.ok(accountService.getCreditsDetailsInsight(txName), MediaType.APPLICATION_JSON).build();
	}

}
