package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.model.School;
import pl.edu.agh.ki.mwo.model.SchoolClass;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;

@Controller //konieczna adnotacja oznaczjaca obiekt kontrolera od strony widoku
public class SchoolClassesController {

    @RequestMapping(value="/SchoolsClasses")
    public String listSchoolClasses(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

    	model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolClasses());
    	
        return "schoolsClassesList";    
    }
    
	@RequestMapping(value="/AddSchoolClass")
    public String displayAddSchoolClassForm(Model model, HttpSession session) {    	
		if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

       	model.addAttribute("schools", DatabaseConnector.getInstance().getSchools());
       	
        return "schoolClassForm";    
    }
    
    @RequestMapping(value="/CreateSchoolClass", method=RequestMethod.POST)
    public String createSchoolClass(@RequestParam(value="schoolClassStartYear", required=false) String startYear,
    		@RequestParam(value="schoolClassCurrentYear", required=false) String currentYear,
    		@RequestParam(value="schoolClassProfile", required=false) String profile,
    		@RequestParam(value="schoolClassSchool", required=false) String schoolId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	//School school= DatabaseConnector.getInstance().getSchool(schoolId);
    	SchoolClass schoolClass = new SchoolClass();
    	schoolClass.setStartYear(Integer.valueOf(startYear));
    	schoolClass.setCurrentYear(Integer.valueOf(currentYear));
    	schoolClass.setProfile(profile);
    	//schoolClass.setSchool(school);

    	
    	DatabaseConnector.getInstance().addSchoolClass(schoolClass, schoolId);    	
       	model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolClasses());
    	model.addAttribute("message", "Nowa klasa została dodana");
         	
    	return "schoolsClassesList";
    }
    

    @RequestMapping(value="/DeleteSchoolClass", method=RequestMethod.POST)
    public String deleteSchoolClass(@RequestParam(value="schoolClassId", required=true) String schoolClassId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	DatabaseConnector.getInstance().deleteSchoolClass(schoolClassId);    	
       	model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolClasses());
    	model.addAttribute("message", "klasa została usunięta");
         	
    	return "schoolsClassesList";
    }
    
    @RequestMapping(value="/EditSchoolClass", method=RequestMethod.POST)
    public String editSchool(@RequestParam(value="schoolClassId", required=false) String schoolClassId,
    	   Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	model.addAttribute("schoolClassId", schoolClassId);
    	model.addAttribute("schools", DatabaseConnector.getInstance().getSchools());
        return "editSchoolClassForm";
    }
    
    @RequestMapping(value="/CorrectSchoolClassData", method=RequestMethod.POST)
    public String correctSchoolClassData(@RequestParam(value="schoolClassId", required=true) String schoolClassId,
    		@RequestParam(value="schoolClassProfile", required=false) String schoolClassProfile,
    		@RequestParam(value="schoolClassStartYear", required=false) String schoolClassStartYear,
    		@RequestParam(value="schoolClassCurrentYear", required=false) String schoolClassCurrentYear,
    		@RequestParam(value="schoolClassSchool", required=false) String schoolClassSchool,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	 	
    	DatabaseConnector.getInstance().editSchoolClass(schoolClassId, schoolClassProfile, schoolClassStartYear, schoolClassCurrentYear, schoolClassSchool);
    	model.addAttribute("schoolsClasses", DatabaseConnector.getInstance().getSchoolClasses());
    	model.addAttribute("message", "Dane klasy zostały zmienione");
         	
    	return "schoolsClassesList";
    }

}