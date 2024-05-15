package jp.ac.ohara.score.repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jp.ac.ohara.score.models.TestModel;

@Repository
public interface TestRepository extends JpaRepository<TestModel,Long>,
	JpaSpecificationExecutor<TestModel>{
	
	TestModel findByStudentNo(String StudentNo);
	
	
	static Specification<TestModel> studentNoContains(String studentNo) {
		  return StringUtils.isEmpty(studentNo) ? null : new Specification<TestModel>() {
		      @Override
		      public Predicate toPredicate(Root<TestModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.like(root.get("studentNo"), "%" + studentNo + "%");
		      }
		   };
		}
	List<TestModel> findByStudentNoInAndSubjectCd(List<String> studentNo,String subjectCd);
}
