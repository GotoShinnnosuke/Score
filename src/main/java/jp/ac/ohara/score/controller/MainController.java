package jp.ac.ohara.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.ohara.score.models.StudentModel;
import jp.ac.ohara.score.service.StudentService;


@Controller
public class MainController {
	
	@Autowired
	private StudentService studentservice;
	
	@GetMapping("/")
	  public String index(Model model) {
		return "base";
	}
	
	@GetMapping("/student")
	  public ModelAndView create(StudentModel studentmodel,ModelAndView model) {
		model.addObject("data",studentmodel);
		model.setViewName("create");
		return model;
	}
	
	@PostMapping("/student")
	  public String create(StudentModel studentmodel,Model model,RedirectAttributes redirectattributes) {
		  try {
			  this.studentservice.save(studentmodel);
			  redirectattributes.addFlashAttribute("exception","");
		}catch(Exception e){
			  redirectattributes.addFlashAttribute("exception",e.getMessage());
			  }
		  return "/createSuccess";
	}
	
	@GetMapping("/index")
	  public String show(Model model ,StudentModel studentmodel) {
			this.studentservice.getStudentList();
			model.addAttribute("studentlist",studentservice.getStudentList());
			return "index";
		}
	
	@GetMapping("/createSuccess")
	  public ModelAndView viewCreateSuccess(ModelAndView model) {
			model.setViewName("createsuccess");
			return model;
		}
	

	
	@GetMapping("/studentUpdate/{id}")
	  public ModelAndView studentUpdate(@PathVariable(name="id")Long id,ModelAndView model) {
		model.addObject("data",this.studentservice.getStudent(id));
		model.setViewName("studentUpdate");
		return model;
	}
	
	@PostMapping("/studentUpdate/{id}")
	  public String studentUpdate(@PathVariable(name="id")@ModelAttribute @Validated Long id,Model model, StudentModel studentmodel, BindingResult result) {
		  // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "redirect:/studentUpdate";
	    }
		
	    this.studentservice.update(studentmodel);
	    
	    return "redirect:/updateSuccess";
		}
	
	@GetMapping("/updateSuccess")
	  public ModelAndView viewUpdateSuccess(ModelAndView model) {
			model.setViewName("updatesuccess");
			return model;
		}
	
	@GetMapping("/studentDelete/{id}")
	  public ModelAndView studentdelete(@PathVariable(name="id")Long id,ModelAndView model) {
		model.addObject("data",this.studentservice.getStudent(id));
		model.setViewName("studentDelete");
		return model;
		}
	
	@PostMapping("/studentDelete/{id}")
	  public String studentdelete(@PathVariable(name="id")Long id,Model model,StudentModel studentmodel) {
		this.studentservice.delete(studentmodel);
		return "redirect:/deleteSuccess";
	}
	
	@GetMapping("/deleteSuccess")
	  public ModelAndView viewDeleteSuccess(ModelAndView model) {
			model.setViewName("deletesuccess");
			return model;
		}
}
