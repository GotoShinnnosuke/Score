package jp.ac.ohara.score.repositories;

import java.io.Serializable;
import java.util.List;

import jp.ac.ohara.score.models.StudentModel;

public interface StudentDataDao extends Serializable{
	
	public List<StudentModel> search(String studentNo, 
			Integer entYear,String classNum,Boolean isAttend);
}
	 
