package jp.ac.ohara.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.score.models.StudentModel;
import jp.ac.ohara.score.repositories.StudentDataDaoImpl;
import jp.ac.ohara.score.repositories.StudentRepository;


@Service
@Transactional
public class StudentService {
	
	private final StudentRepository repository;
	
	private final StudentDataDaoImpl studentdatadaoimpl;
	

	
    public StudentService(StudentRepository repository, StudentDataDaoImpl studentdatadaoimpl) {
        this.repository = repository;
        this.studentdatadaoimpl = studentdatadaoimpl;
    }
	
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
	
	//検索
    public List<StudentModel> search(String studentNo, 
			Integer entYear,String classNum,Boolean isAttend){

        List<StudentModel> result = new ArrayList<StudentModel>();

        //すべてブランクだった場合は全件検索する
        if ("".equals(studentNo) && "".equals(entYear) && "".equals(classNum) && "".equals(isAttend)){
            result = this.repository.findAll();
        }
        else {
            //上記以外の場合、BookDataDaoImplのメソッドを呼び出す
            result = this.studentdatadaoimpl.search(studentNo, entYear,classNum,isAttend);
        }
        return result;
    }
	

}
