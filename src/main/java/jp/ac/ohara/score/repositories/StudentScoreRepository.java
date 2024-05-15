package jp.ac.ohara.score.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jp.ac.ohara.score.models.StudentScoreModel;
@Repository
	public interface StudentScoreRepository  extends JpaRepository<StudentScoreModel,Long>,
	JpaSpecificationExecutor<StudentScoreModel>{
	
	
		//独自条件生成の関数　studentNoに値が設定されている場合のみ、条件を生成する。
			//独自条件生成の関数　classNumに値が設定されている場合のみ、条件を生成する。
				static Specification<StudentScoreModel> classNumContains(String classNum) {
				  return StringUtils.isEmpty(classNum) ? null : new Specification<StudentScoreModel>() {
				      @Override
				      public Predicate toPredicate(Root<StudentScoreModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				        return cb.like(root.get("classNum"), "%" + classNum + "%");
				   }
				};
			}
				
			//独自条件生成の関数　entYearに値が設定さている場合のみ、条件を生成する。
			static Specification<StudentScoreModel> entYearContains(Integer entYear) {
			  return entYear == null ? null : new Specification<StudentScoreModel>() {
			      @Override
			      public Predicate toPredicate(Root<StudentScoreModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			        return cb.equal(root.get("entYear"),entYear);
			      }
			  	};
			}
			
			//独自条件生成の関数　classNumに値が設定されている場合のみ、条件を生成する。
			static Specification<StudentScoreModel> subjectCdContains(String subjectCd) {
			  return StringUtils.isEmpty(subjectCd) ? null : new Specification<StudentScoreModel>() {
			      @Override
			      public Predicate toPredicate(Root<StudentScoreModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			        return cb.like(root.get("subjectCd"),subjectCd);
			   }
			};

		}
	}
