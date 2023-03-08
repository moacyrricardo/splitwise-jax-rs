package com.iskeru.splitwise.expenses.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iskeru.splitwise.expenses.model.SplitWiseExpense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitWiseExpensesResponse {
	private List<SplitWiseExpense> expenses;
	
	private Map<String, Object> errors;
}