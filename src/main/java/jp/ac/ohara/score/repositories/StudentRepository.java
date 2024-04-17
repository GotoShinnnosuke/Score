package jp.ac.ohara.score.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.score.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long>{

}


	
