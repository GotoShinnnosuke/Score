package jp.ac.ohara.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.SubjectModel;
import jp.ac.ohara.score.repositories.SubjectRepository;

@Service
@Transactional
public class SubjectService {
	
	@Autowired
	private SubjectRepository repository;
	
	public void subjectSave(SubjectModel subjectmodel) {
		subjectmodel.setSchoolCd("AAA");
		System.out.print("a");
		this.repository.save(subjectmodel);
		System.out.print("ab");
		}
	
	public List<SubjectModel> getSubjectList() {
		List<SubjectModel> entityList = this.repository.findAll();
		return entityList;
	}
	
	public SubjectModel getSubject(Long id) {
		SubjectModel subject = this.repository.findById(id).orElse(null);
		return subject;
	}
	
//	public List<SubjectModel> getSubject(String subjectName) {
//		List<SubjectModel> subject = this.repository.findBySubjectName(subjectName);
//		return subject;
//	}
	
	public String getSubjectCd(String subjectName) {
		SubjectModel subject = this.repository.findBySubjectName(subjectName);
		return subject.getSubjectCd();
	}

	
	
	public void update(SubjectModel subjectmodel) {
		this.repository.save(subjectmodel);
	}
	
	public void delete(SubjectModel subjectmodel) {
		subjectmodel.setIsDelete(false);
		this.repository.save(subjectmodel);
	}
	

}
