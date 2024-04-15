package jp.ac.ohara.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.StudentModel;
import jp.ac.ohara.score.repositories.StudentRepository;


@Service
@Transactional
public class StudentService {
	@Autowired
	private StudentRepository repository;
	
	public List<StudentModel> getStudentList() {
		List<StudentModel> entityList = this.repository.findAll();
//		this.repository.
		return entityList;
	}
	//idで指定した学生情報を取得
	public StudentModel getStudent(Long id) {
		StudentModel student = this.repository.findById(id).orElse(null);
		return student;
	}
	
	public void update(StudentModel studentmodel) {
		this.repository.save(studentmodel);
	}


	public void save(StudentModel studentmodel) {
		this.repository.save(studentmodel);
	}
	
	public void delete(StudentModel studentmodel) {
		studentmodel.setIsAttend(false);
		this.repository.save(studentmodel);
	}

//	public List<StudentModel> getList(String isAttend) {
//	  List<StudentModel> list = repository.findAll(Specification
//	    .where(StudentRepository.userNameContains(isAttend)));
//	    // 複数項目の場合は.and(),.or()でつなげる
//	}
	

}
