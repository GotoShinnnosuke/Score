//package jp.ac.ohara.score.repositories;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.JpaContext;
//import org.springframework.stereotype.Repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Root;
//import jp.ac.ohara.score.models.StudentModel;
//
//@Repository
//public interface StudentRepositoryCustom {
//	  @Autowired
//	  private JpaContext context;
//
//	  public List<String> findBySpec(Specification<StudentModel> spec) {
//	    final EntityManager em = context.getEntityManagerByManagedType(StudentModel.class);
//	    // CriteriaBuilderでクエリを作成
//	    final CriteriaBuilder cb = em.getCriteriaBuilder();
//	    // CriteriaQueryに設定するObjectを指定　※戻り値になる。
//	    CriteriaQuery<String> query = cb.createQuery(String.class);
//	    // FROM句
//	    Root<StudentModel> root = query.from(StudentModel.class);
//	    // select句
//	    query.select(root.get("id")).distinct(true);
//	    // where句　※外部指定可能にしている
//	    query.where(spec.toPredicate(root, query, cb));
//	    final List<String> resultList = em.createQuery(query).getResultList();
//
//	    return resultList;
//	  }
//	}
