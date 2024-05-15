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

import jp.ac.ohara.score.models.SubjectModel;
import jp.ac.ohara.score.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectservice;
	
	
	@GetMapping("/SubjectIndex")
	  public String index(Model model) {
		model.addAttribute("Subjectlist", this.subjectservice.getSubjectList());
		return "subjects/subjectIndex";
	}
	
	@GetMapping("/CreateSubject")
	public ModelAndView createSubject(ModelAndView model,SubjectModel subjectmodel) {
		model.addObject("item",subjectmodel);
		model.setViewName("subjects/subjectCreate");
		return model;
	}
	
	@PostMapping("/CreateSubject")
	public String createSubject(Model model,SubjectModel subjectmodel) {
		subjectmodel.setIsDelete(true);
		this.subjectservice.subjectSave(subjectmodel);
		return "subjects/subjectCreateSuccess";
	}
	
	@GetMapping("/subjectUpdate/{id}")
	public ModelAndView updateSubject(@PathVariable(name="id")Long id,ModelAndView model){
		model.addObject("data",this.subjectservice.getSubject(id));
		System.out.print(this.subjectservice.getSubject(id));
		model.setViewName("subjects/subjectUpdate");
		return model;
		  }
	@PostMapping("/subjectUpdate/{id}")
	public String updateSubject(@PathVariable(name="id")@ModelAttribute @Validated Long id,Model model, SubjectModel subjectmodel, BindingResult result) {
		  // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "redirect:/studentUpdate";
	    }
		subjectmodel.setIsDelete(true);
	    this.subjectservice.update(subjectmodel);
	    
		return "subjects/subjectUpdateSuccess";
	}
	
	@GetMapping("/subjectDelete/{id}")
	public ModelAndView deleteSubject(@PathVariable(name="id")Long id,ModelAndView model) {
		model.addObject("data", this.subjectservice.getSubject(id));
		model.setViewName("subjects/subjectDelete");
		return model;
	}
	
	@PostMapping("/subjectDelete/{id}")
	public String deleteSubject(@PathVariable(name="id")@ModelAttribute @Validated Long id,Model model,SubjectModel subjectmodel) {
		this.subjectservice.delete(subjectmodel);
		return "subjects/subjectDeleteSuccess";
	}
	
	
}
