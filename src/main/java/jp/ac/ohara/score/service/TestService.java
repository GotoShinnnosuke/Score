package jp.ac.ohara.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.StudentModel;
import jp.ac.ohara.score.models.StudentScoreModel;
import jp.ac.ohara.score.models.SubjectModel;
import jp.ac.ohara.score.models.TestModel;
import jp.ac.ohara.score.repositories.StudentRepository;
import jp.ac.ohara.score.repositories.StudentScoreRepository;
import jp.ac.ohara.score.repositories.SubjectRepository;
import jp.ac.ohara.score.repositories.TestRepository;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestRepository testrepository;
	
	@Autowired
	private StudentRepository studentrepository;
	
	@Autowired
	private SubjectRepository subjectrepository;
	
	@Autowired
	private StudentScoreRepository studentscorerepository;
//	
//	public List<SubjectModel> getSubjects(String subjectname){
//		List<SubjectModel> subjects = this.subjectrepository.findBySubjectName(subjectname);
//		return subjects;
//	}
	
	
	//TestTableを学生番号で絞り込み
	//絞り込みのやり方違うので補完お願いします
	public List<TestModel> getscoreList(String studentNum) {
		List<TestModel> list = this.testrepository.findAll(Specification
	    .where(TestRepository.studentNoContains(studentNum)));
		System.out.println(list);
	  return list;
	}
	
	//上で返したlistを用いて科目コードのみのリストを作る
	public List<String> getSubjectCd(List<TestModel> list) {
		List<String> subjectcds = new ArrayList<>();
		for (TestModel subjectCdlist : list) {
			String subjectcd = subjectCdlist.getSubjectCd();
			subjectcds.add(subjectcd);
			}
		return subjectcds;
	}
	
	//上の科目コードのリストを用いて科目コードに対応する科目名のリストを作る
	public List<String> getSubjectName(List<String> list){
		List<String> subname = new ArrayList<>();
		for (String subjects: list) {
			SubjectModel aaa = this.subjectrepository.findBySubjectCd(subjects);
			subname.add(aaa.getSubjectName());
		}
		return subname;
	}
 
	public List<StudentModel> getList(String classNum,Integer entYear) {
		List<StudentModel> list = this.studentrepository.findAll(Specification
	    .where(StudentRepository.classNumContains(classNum))
	    .and(StudentRepository.entYearContains(entYear)));
		System.out.print(list);
	    // 複数項目の場合は.and(),.or()でつなげる
		
	  
	  return list;
	}
	
	public List<StudentScoreModel> getList(Integer entYear,String classNum,String subjectCd){
		List<StudentScoreModel> list = this.studentscorerepository.findAll(Specification
		.where(StudentScoreRepository.entYearContains(entYear))
		.and(StudentScoreRepository.classNumContains(classNum))
		.and(StudentScoreRepository.subjectCdContains(subjectCd)));
		System.out.println(list);
		return list;
	}
	
	public void scoreSave(TestModel testmodel) {
//		List<TestModel> list = testmodel;
		System.out.println(testmodel);
		this.testrepository.save(testmodel);
	}
	
	public List<TestModel> testFilter(List<String> studentNo, String subjectCd){
		return this.testrepository.findByStudentNoInAndSubjectCd(studentNo, subjectCd);
	}
}
