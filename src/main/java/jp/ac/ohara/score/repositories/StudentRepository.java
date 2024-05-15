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
import jp.ac.ohara.score.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long>,
	JpaSpecificationExecutor<StudentModel>{

//独自条件生成の関数　studentNoに値が設定されている場合のみ、条件を生成する。
	static Specification<StudentModel> studentNoContains(String studentNo) {
	  return StringUtils.isEmpty(studentNo) ? null : new Specification<StudentModel>() {
	      @Override
	      public Predicate toPredicate(Root<StudentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.like(root.get("studentNo"), "%" + studentNo + "%");
	      }
	   };
	}
	
	//独自条件生成の関数　classNumに値が設定されている場合のみ、条件を生成する。
		static Specification<StudentModel> classNumContains(String classNum) {
		  return StringUtils.isEmpty(classNum) ? null : new Specification<StudentModel>() {
		      @Override
		      public Predicate toPredicate(Root<StudentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.like(root.get("classNum"), "%" + classNum + "%");
		   }
		};
	}
		
	//独自条件生成の関数　entYearに値が設定さている場合のみ、条件を生成する。
	static Specification<StudentModel> entYearContains(Integer entYear) {
	  return entYear == null ? null : new Specification<StudentModel>() {
	      @Override
	      public Predicate toPredicate(Root<StudentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.equal(root.get("entYear"),entYear);
	      }
	  	};
	}
	
	//独自条件生成の関数　isAttendに値が設定されている場合のみ、条件を生成する。
	static Specification<StudentModel> isAttendContains(Boolean isAttend) {
        return (root, query, cb) -> {
            if (isAttend == null) {
                // isAttendがnullの場合、isAttendがfalseであるレコードを非表示にする条件を追加
                return cb.or(cb.isNull(root.get("isAttend")), cb.isTrue(root.get("isAttend")));
            }
            // isAttendが指定されている場合は、その値に基づいてクエリを生成する
            return null;
        };
    }
	
	//独自条件生成の関数　classNumに値が設定されている場合のみ、条件を生成する。
	static Specification<StudentModel> schoolCdContains(String schoolCd) {
	  return StringUtils.isEmpty(schoolCd) ? null : new Specification<StudentModel>() {
	      @Override
	      public Predicate toPredicate(Root<StudentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.like(root.get("schoolCd"),schoolCd);
	   }
	};
}
	
	
	List<StudentModel> findBySchoolCd(String schoolCd);

	StudentModel findByStudentNo(String studentNum);

	List<StudentModel> findByEntYearAndClassNum(Integer entYear, String classNum);

	StudentModel findByName(String students);
	
}
