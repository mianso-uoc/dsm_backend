package uoc.edu.dsmantenimiento.repository;

import java.util.Date;
import java.util.List;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Issue;

public interface IssueRepositoryCustom {

	List<Issue> find(Company company, Date startDate, Date endDate);

}
