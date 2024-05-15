package jp.ac.ohara.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.StudentModel;
import jp.ac.ohara.score.repositories.StudentRepository;


@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudentRepository repository;

	
	public List<StudentModel> getStudentList(String schoolCd) {
		List<StudentModel> entityList = this.repository.findBySchoolCd(schoolCd);
//		this.repository.
		return entityList;
	}
	//idで指定した学生情報を取得
	public StudentModel getStudent(Long id) {
		StudentModel student = this.repository.findById(id).orElse(null);
		return student;
	}
	
	public StudentModel getStudentNo(String studentNum) {
		StudentModel student = this.repository.findByStudentNo(studentNum);
		return student;
	}
	
	public List<StudentModel> getStudent(Integer entYear,String classNum) {
		List<StudentModel> student = this.repository.findByEntYearAndClassNum(entYear,classNum);
		return student;
	}
	
	public void update(StudentModel studentmodel) {
		this.repository.save(studentmodel);
	}


	public void save(StudentModel studentmodel) {
		studentmodel.setIsAttend(true);
		this.repository.save(studentmodel);
	}
	
	public void delete(StudentModel studentmodel) {
		studentmodel.setIsAttend(false);
		this.repository.save(studentmodel);
	}

	public List<StudentModel> getList(String studentNo,String classNum,Integer entYear, Boolean isAttend,String schoolCd) {
		List<StudentModel> list = this.repository.findAll(Specification
	    .where(StudentRepository.studentNoContains(studentNo))
	    .and(StudentRepository.classNumContains(classNum))
	    .and(StudentRepository.entYearContains(entYear))
		.and(StudentRepository.isAttendContains(isAttend))
		.and(StudentRepository.schoolCdContains(schoolCd)));
		System.out.print(list);
	    // 複数項目の場合は.and(),.or()でつなげる	  
	  return list;
	}
	
	public ArrayList<Object> searchList(String studentNo,String classNum,Integer entYear){
		ArrayList<Object> list = new ArrayList<>();
		if (studentNo.equals("")){
			list.add("学生番号は指定されていません");
		}else {
			list.add("学生番号："+ studentNo);
		}
		if (classNum == null) {
			list.add("クラス番号は指定されていません");
		}else {
			list.add("クラス番号:"+ classNum);
		}
		if (entYear == null) {
			list.add("入学年度は指定されていません");
		}else {
			list.add("入学年度："+ entYear);
		}		
		return list;
	}
	
	public List<String> getStudentNolist(List<StudentModel> list) {
		List<String> studentNos = new ArrayList<>();
		for (StudentModel studentlist : list) {
			String subjectcd = studentlist.getStudentNo();
			studentNos.add(subjectcd);
			}
		return studentNos;
	}
	
	public List<String> getStudentName(List<String> list){
		List<String> stuname = new ArrayList<>();
		for (String students: list) {
			StudentModel aaa = this.repository.findByStudentNo(students);
			stuname.add(aaa.getName());
		}
		return stuname;
	}


}
