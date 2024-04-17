package jp.ac.ohara.score.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.score.models.StudentModel;


@Repository
public class StudentDataDaoImpl implements StudentDataDao{
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Session session;


	
	public StudentDataDaoImpl() {
		super();
	}
	
	public StudentDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> search(String studentNo, 
			Integer entYear,String classNum,Boolean isAttend){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM student WHERE ");
		
		boolean stunumFlg  = false;
        boolean entyearFlg  = false;
        boolean classnumFlg    = false;
        boolean isattendFlg = false;
        boolean andFlg = false;
        
        
        
        
        //studentNoがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!("").equals(studentNo)) {
            sql.append(" student_no LIKE studentNo");
            stunumFlg = true;
            andFlg   = true;
        }

        
//        //genreがブランクではなかった場合、sql変数にappendする
//        //フラグをtrueにしとく
//        if(entYear != null) {
//            sql.append(" AND entYear LIKE entYear");
//            System.out.println(sql);
//            entyearFlg = true;
//            andFlg   = true;
//        }
//        
//        //genreがブランクではなかった場合、sql変数にappendする
//        //フラグをtrueにしとく
//        if(!"".equals(classNum)) {
//            sql.append(" AND classNum LIKE classNum");
//            System.out.println(sql);
//            classnumFlg = true;
//            andFlg   = true;
//        }
//        
//        //genreがブランクではなかった場合、sql変数にappendする
//        //フラグをtrueにしとく
//        if(!isAttend) {
//            sql.append("b.isAttend LIKE :isAttend");
//            isattendFlg = true;
//            andFlg   = true;
//        }
        
        /*
        QueryはSQLでデータを問い合わせるためのクエリ文に相当する機能を持つ
        entityManagerのcreateQueryメソッドを使用する
        sql変数を引数に渡す
        */
        System.out.println(sql);
		Query query = session.createNativeQuery("SELECT * FROM student WHERE student_no=?studentNo");
        query.setParameter(studentNo,"student_no");

        //上記のif文でtrueになった場合は、各変数に値をセットする
        //今回、あいまい検索したいのでlike句を使用する
        if (stunumFlg) query.setParameter("student_no", "%" + studentNo + "%");
//        if (entyearFlg) query.setParameter("entYear", "%" + entYear + "%");
//        if (classnumFlg) query.setParameter("classNum", "%" + classNum + "%");
//        if (isattendFlg) query.setParameter("isAttend", "%" + isAttend + "%");
        
        
        return query.getResultList();
        
	}
}
