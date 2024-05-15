package jp.ac.ohara.score.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.score.models.SubjectModel;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel,Long>{
	
	SubjectModel findBySubjectName(String subjectName);
	SubjectModel findBySubjectCd(String subjectlist);
}
