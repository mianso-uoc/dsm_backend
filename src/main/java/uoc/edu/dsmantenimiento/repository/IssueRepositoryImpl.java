package uoc.edu.dsmantenimiento.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import uoc.edu.dsmantenimiento.model.Company;
import uoc.edu.dsmantenimiento.model.Issue;

@Component
public class IssueRepositoryImpl implements IssueRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Issue> find(Company company, Date startDate, Date endDate) {
		StringBuilder hql = new StringBuilder("SELECT e.* FROM uoc.issue e");
		StringBuilder where = new StringBuilder(" where ");
		
		boolean isFiltered = false;
		
		if (company != null) {
			where.append("e.company_id = :company_id");
			isFiltered = true;
		}
		
		if (startDate != null) {
			if (isFiltered) {
				where.append(" and ");
			} else {
				isFiltered = true;
			}
			where.append("e.create_date >= :startDate");
			
		}
		
		if (endDate != null) {
			if (isFiltered) {
				where.append(" and ");
			} else {
				isFiltered = true;
			}
			where.append("e.create_date <= :endDate");
		}
		
		if (company != null || startDate != null || endDate != null) {
			hql.append(where);
		}
		
		Query query = em.createNativeQuery(hql.toString(), Issue.class);
//		Query query = em.createNativeQuery("SELECT em.* FROM uoc.issue as em ");
//		Query query = em.createQuery("SELECT i FROM issue i")
		if (company != null) {
			query.setParameter("company_id", company.getId());
		}
		
		if (startDate != null) {
			query.setParameter("startDate", startDate);
		}
		
		if (endDate != null) {
			query.setParameter("endDate", endDate);
		}
		
		return query.getResultList();
	}

}
