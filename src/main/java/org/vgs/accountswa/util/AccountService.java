package org.vgs.accountswa.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.vgs.accountswa.model.ChartDataModel;
import org.vgs.accountswa.model.Entry;
import org.vgs.accountswa.model.Scenario;

@Stateless
public class AccountService {

	private static int insightRows = 10;

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
		if (entry.getUpdatedDate() != null) {
			entry.setUpdatedDate(new DateTime(DateTimeZone.UTC).toLocalDateTime().toDate());
		}
		em.persist(entry);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, List<Object>>> getTopDebitsInsight() {
		List<ChartDataModel> resultList = em
				.createNativeQuery("SELECT tx_name as label, SUM(amount) as value from entry GROUP BY tx_name limit :rows", "ChartResults")
				.setParameter("rows", insightRows).getResultList();
		List<Object> insightXLabels = new ArrayList<Object>();
		List<Object> insightYValues = new ArrayList<Object>();
		for (ChartDataModel chartDataModel : resultList) {
			insightXLabels.add(chartDataModel.getLabel());
			insightYValues.add(chartDataModel.getValue());
		}
		Map<String, List<Object>> topTenDebitsMap = new HashMap<String, List<Object>>();
		topTenDebitsMap.put("labels", insightXLabels);
		topTenDebitsMap.put("values", insightYValues);
		Map<String, Map<String, List<Object>>> insightResultMap = new HashMap<String, Map<String, List<Object>>>();
		insightResultMap.put("topTenDebits", topTenDebitsMap);
		return insightResultMap;
	}

}
