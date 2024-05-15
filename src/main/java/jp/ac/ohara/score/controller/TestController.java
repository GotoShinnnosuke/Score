package jp.ac.ohara.score.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.ohara.score.models.StudentModel;
import jp.ac.ohara.score.models.StudentScoreModel;
import jp.ac.ohara.score.models.SubjectModel;
import jp.ac.ohara.score.models.TeacherModel;
import jp.ac.ohara.score.models.TestModel;
import jp.ac.ohara.score.service.StudentService;
import jp.ac.ohara.score.service.SubjectService;
import jp.ac.ohara.score.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService testservice;
	
	@Autowired
	private SubjectService subjectservice;
	
	@Autowired
	private StudentService studentservice;
	

	@GetMapping("/scoreImport")
	public String score(Model model,StudentScoreModel studentscoremodel) {
		model.addAttribute("subject", this.subjectservice.getSubjectList());
		model.addAttribute("data", studentscoremodel);
		return "tests/scoreImport";
	}
	
	@PostMapping("/scoreImport")
	public String scorePage(@RequestParam("entYear") Integer entyear,
							@RequestParam("classNum") String classNum,
							@RequestParam("subject") String subject,
							@RequestParam("count") Integer count,
							@AuthenticationPrincipal TeacherModel teachermodel,
							Model model) {
		String counttext = count + "回目";
		model.addAttribute("object", this.testservice.getList(classNum, entyear));
		model.addAttribute("subName", subject);
		model.addAttribute("subject", this.subjectservice.getSubjectList());
		model.addAttribute("subjectcd", this.subjectservice.getSubjectCd(subject));
		model.addAttribute("counttext", counttext);
		model.addAttribute("count", count);
		model.addAttribute("schoolCd", teachermodel.getSchoolCd());
		return "tests/scoreImport";
	}
	
	@PostMapping("/scoreImportSuccess")
	public String scoreImport(@ModelAttribute("TestModel") TestModel testmodel,HttpServletRequest request) {
		System.out.println(testmodel);
		String [] studentNolist = testmodel.getStudentNo().split(",");
		String [] subjectCdlist = testmodel.getSubjectCd().split(",");
		String [] schoolCdlist = testmodel.getSchoolCd().split(",");
		String [] classNumlist = testmodel.getClassNum().split(",");
		String [] countlist = request.getParameterValues("count");
		String [] scorelist = request.getParameterValues("score");
		for (int i = 0;studentNolist.length > i;i++) {
			System.out.println(i);
			TestModel newtestmodel = new TestModel();
			newtestmodel.setStudentNo(studentNolist[i]);
			newtestmodel.setSubjectCd(subjectCdlist[i]);
			newtestmodel.setSchoolCd(schoolCdlist[i]);
			newtestmodel.setClassNum(classNumlist[i]);
			newtestmodel.setCount(Integer.parseInt(countlist[i]));
			newtestmodel.setScore(Integer.parseInt(scorelist[i]));
			this.testservice.scoreSave(newtestmodel);
		}
		return "tests/scoreImportSuccess";
	}
	
	@GetMapping("/scoreReference")
	public String scoreRef(Model model,TestModel testmodel) {
		model.addAttribute("data", testmodel);
		model.addAttribute("subject", this.subjectservice.getSubjectList());
		return "tests/scoreReference";
	}
	
	@PostMapping("/scoreReference1")
	public String scoreFilter(Model model,StudentModel studentmodel,SubjectModel subjectmodel,
					@RequestParam("entYear") Integer entYear,
					@RequestParam("classNum") String ClassNum,
					@RequestParam("subject") String subject) {
		String subjectCd = this.subjectservice.getSubjectCd(subject);
		System.out.println(subjectCd);
		List<StudentModel> students = this.studentservice.getStudent(entYear, ClassNum);
		System.out.println(students);
		List<String> studentNos = this.studentservice.getStudentNolist(students);
		System.out.println(studentNos);
		List<String> studentNames = this.studentservice.getStudentName(studentNos);
		System.out.println(studentNames);
		List<TestModel> testlist = this.testservice.testFilter(studentNos, subjectCd);
		System.out.println(testlist);	
		model.addAttribute("subjectName",subject);
		model.addAttribute("studentName",studentNames);
		model.addAttribute("testlist", testlist);
		model.addAttribute("entyear",entYear);
		model.addAttribute("classnum", ClassNum);

		return "tests/scoreReference";
	}
	
	@PostMapping("/scoreReference2")
	//学生番号での成績参照
	public String scoreStuNumFilter(Model model,TestModel testmodel,SubjectModel subjectmodel,
									@RequestParam("studentNo") String studentNum) {
		List<String> list = new ArrayList<>();
		//学生番号で絞り込んだTestModelからSubjectCdのみを取りだし科目コードのリストを作る。
		list = this.testservice.getSubjectCd(this.testservice.getscoreList(studentNum));
		//ここは消してもＯＫ
		System.out.println(list);
		System.out.println(this.testservice. getSubjectName(list));
		model.addAttribute("datalist",this.testservice.getscoreList(studentNum));
		//上で作ったlistを科目名のリストに変えて渡す
		model.addAttribute("kamokulist",this.testservice. getSubjectName(list));
		model.addAttribute("subject", this.subjectservice.getSubjectList());
		return "tests/scoreReference";
	}
}
