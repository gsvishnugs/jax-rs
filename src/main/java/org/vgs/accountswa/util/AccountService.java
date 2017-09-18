package org.vgs.accountswa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.vgs.accountswa.model.ChartDataModel;
import org.vgs.accountswa.model.Entry;
import org.vgs.accountswa.model.Scenario;

@Stateless
public class AccountService {

	private static int insightRows = 1000;

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
	public Map<String, Map<String, List<Object>>> getDebitsInsight() {
		List<ChartDataModel> resultList = em.createNativeQuery(
				"SELECT tx_name as label, SUM(amount) as value from entry WHERE tx_type = 'DEBIT' GROUP BY tx_name limit :rows",
				"ChartResults").setParameter("rows", insightRows).getResultList();
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

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, List<Object>>> getDebitsDetailsInsight(String txName) {
		List<ChartDataModel> resultList = em.createNativeQuery(
				"SELECT to_char(date_trunc('day', updated_date), 'yyyy-MM-dd') as label, "
						+ "sum(amount) as value FROM entry WHERE tx_type = 'DEBIT' AND tx_name = :txName GROUP BY 1 ORDER BY 1",
				"ChartResults").setParameter("txName", txName).getResultList();
		List<Object> insightXLabels = new ArrayList<Object>();
		List<Object> insightYValues = new ArrayList<Object>();
		for (ChartDataModel chartDataModel : resultList) {
			insightXLabels.add(chartDataModel.getLabel());
			insightYValues.add(chartDataModel.getValue());
		}
		Map<String, List<Object>> debitsDetaisMap = new HashMap<String, List<Object>>();
		debitsDetaisMap.put("labels", insightXLabels);
		debitsDetaisMap.put("values", insightYValues);
		Map<String, Map<String, List<Object>>> insightResultMap = new HashMap<String, Map<String, List<Object>>>();
		insightResultMap.put("debitsDetails", debitsDetaisMap);
		return insightResultMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, List<Object>>> getCreditsInsight() {
		List<ChartDataModel> resultList = em.createNativeQuery(
				"SELECT tx_name as label, SUM(amount) as value from entry WHERE tx_type = 'CREDIT' GROUP BY tx_name limit :rows",
				"ChartResults").setParameter("rows", insightRows).getResultList();
		List<Object> insightXLabels = new ArrayList<Object>();
		List<Object> insightYValues = new ArrayList<Object>();
		for (ChartDataModel chartDataModel : resultList) {
			insightXLabels.add(chartDataModel.getLabel());
			insightYValues.add(chartDataModel.getValue());
		}
		Map<String, List<Object>> topTenCreditsMap = new HashMap<String, List<Object>>();
		topTenCreditsMap.put("labels", insightXLabels);
		topTenCreditsMap.put("values", insightYValues);
		Map<String, Map<String, List<Object>>> insightResultMap = new HashMap<String, Map<String, List<Object>>>();
		insightResultMap.put("topTenCredits", topTenCreditsMap);
		return insightResultMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, List<Object>>> getCreditsDetailsInsight(String txName) {
		List<ChartDataModel> resultList = em.createNativeQuery(
				"SELECT to_char(date_trunc('day', updated_date), 'yyyy-MM-dd') as label, "
						+ "sum(amount) as value FROM entry WHERE tx_type = 'CREDIT' AND tx_name = :txName GROUP BY 1 ORDER BY 1",
				"ChartResults").setParameter("txName", txName).getResultList();
		List<Object> insightXLabels = new ArrayList<Object>();
		List<Object> insightYValues = new ArrayList<Object>();
		for (ChartDataModel chartDataModel : resultList) {
			insightXLabels.add(chartDataModel.getLabel());
			insightYValues.add(chartDataModel.getValue());
		}
		Map<String, List<Object>> creditsDetaisMap = new HashMap<String, List<Object>>();
		creditsDetaisMap.put("labels", insightXLabels);
		creditsDetaisMap.put("values", insightYValues);
		Map<String, Map<String, List<Object>>> insightResultMap = new HashMap<String, Map<String, List<Object>>>();
		insightResultMap.put("creditsDetails", creditsDetaisMap);
		return insightResultMap;
	}

}
