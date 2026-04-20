package com.expense.tracker.repositories;

import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.SearchCriteriaParmeters;
import com.expense.tracker.dtos.MonthlyExpenseReportProjection;
import com.expense.tracker.utilities.DBOperations;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportsRepository {
    private final DBOperations db;

    public ReportsRepository(DBOperations db) {
        this.db = db;
    }

    public List<MonthlyExpenseReportProjection> getMonthlyExpenseReport(SearchCriteria requestCriteria) {
        String sql = "Select g.group_id,g.name group_name,Sum(e.amount) total_expense, " +
                "            Cast((select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false) as Integer)total_members " +
                "            from expenses e " +
                "            join groups g on g.group_id = e.group_id " +
                "            left join group_members gm on gm.group_id = g.group_id AND gm.is_deleted = false " +
                "            where e.is_deleted = false and Date(e.expense_date) BETWEEN Date(:dateFrom) AND Date(:dateTo) and e.user_id = :userId" +
                "            and (CAST(e.group_id AS VARCHAR) = :groupId or :groupId = '-1') " +
                "            group by g.group_id,g.name ";

        Map<String, Object> paramMap = new LinkedHashMap<>();
        paramMap.put("userId", requestCriteria.getId());
        for (SearchCriteriaParmeters parameter : requestCriteria.getParameters()) {
            switch (parameter.getParamName()) {
                case "dateFrom" -> paramMap.put("dateFrom", parameter.getParamValue());
                case "dateTo" -> paramMap.put("dateTo", parameter.getParamValue());
                case "groupId" -> paramMap.put("groupId", parameter.getParamValue());

            }
        }

        return db.fetchList(sql, MonthlyExpenseReportProjection.class, paramMap);
    }
}
