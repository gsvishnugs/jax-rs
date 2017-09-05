package org.vgs.accountswa.util;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.vgs.accountswa.model.Entry;
import org.vgs.accountswa.model.Scenario;

@Stateless
public class AccountService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	public List<Entry> getAllEntries() throws Exception {
		return em.createNamedQuery("Entry.getAll", Entry.class).getResultList();
	}

	public List<Scenario> getAutocompleteDesc(String text) throws Exception {
		return em.createNamedQuery("Scenario.searchForAutocomplete", Scenario.class)
				.setParameter("text", StringUtils.lowerCase("%" + text + "%")).getResultList();
	}

	public void createEntry(Entry entry) {
		entry.setUpdatedDate(new Date());
		em.persist(entry);
	}

}
