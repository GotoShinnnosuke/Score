package jp.ac.ohara.score.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.score.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long>{
//	JpaSpecificationExecutor<StudentModel>,StudentRepositoryCustom{
//	// 独自条件生成の関数　userNameに値が設定されている場合のみ、条件を生成する。
//	static Specification<StudentModel> isAttendContains(Boolean isAttend) {
//	  return isAttend ? null : new Specification<StudentModel>() {
//	      @Override
//	      public Predicate toPredicate(Root<StudentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//	        return cb.like(root.get("isAttend"), "%" + isAttend + "%");
//	      }
//	   };
	}


	
