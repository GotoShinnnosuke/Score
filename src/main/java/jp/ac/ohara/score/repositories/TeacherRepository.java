package jp.ac.ohara.score.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.score.models.TeacherModel;


@Repository
public interface TeacherRepository  extends JpaRepository<TeacherModel,Long>,JpaSpecificationExecutor<TeacherModel>{
	
	public TeacherModel findByteacherIdEquals(String teacherId);
	

	
}
