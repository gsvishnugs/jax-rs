package org.vgs.accountswa.util;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.vgs.accountswa.model.Entry;

@Stateless
public class AccountService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	public List<Entry> getAllEntries() throws Exception {
		log.info("Getting all accounting entries");
		return em.createNamedQuery("Entry.getAll", Entry.class).getResultList();
	}
}
