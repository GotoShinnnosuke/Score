package jp.ac.ohara.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.ohara.score.models.TeacherModel;
import jp.ac.ohara.score.service.TeacherLoginService;
import jp.ac.ohara.score.service.TeacherService;

@Controller
public class TeacherController {
	
	@Autowired
	TeacherService teacherservice;
	
	@Autowired
	TeacherLoginService teacherloginservice;
	
	@GetMapping("/signup")
	public ModelAndView signup(ModelAndView model,TeacherModel teachermodel) {
		model.addObject("data", new TeacherModel());
		model.setViewName("teachers/signup");
		return model;
	}
	
	@PostMapping("/signup")
	public String signup(Model model,TeacherModel teachermodel) {
		teachermodel.setSchoolCd("AAA");
		teacherservice.teacherSave(teachermodel);
		return "/teachers/login";
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView model, TeacherModel teachermodel) {
		model.addObject("data", teachermodel);
		model.setViewName("teachers/login");
		return model;
	}
	
	@PostMapping("/login")
	public String postLogin(Model model, TeacherModel teachermodel) {
		this.teacherloginservice.loadUserByUsername(teachermodel.getTeacherId());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/teachers/login"; 
	}
}
