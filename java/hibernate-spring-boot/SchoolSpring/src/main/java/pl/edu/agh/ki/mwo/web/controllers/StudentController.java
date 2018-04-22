package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.model.Student;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;

@Controller //konieczna adnotacja oznaczjaca obiekt kontrolera od strony widoku
public class StudentController {

    @RequestMapping(value="/Students")
    public String listStudents(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

    	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	
        return "studentsList";    
    }
    
    @RequestMapping(value="/AddStudent")
    public String displayAddSchoolForm(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
        return "studentForm";    
    }

    @RequestMapping(value="/CreateStudent", method=RequestMethod.POST)
    public String createStudent(@RequestParam(value="studentName", required=false) String name,
    		@RequestParam(value="studentSurname", required=false) String surname,
    		@RequestParam(value="studentPesel", required=false) String pesel,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Student student = new Student();
    	student.setName(name);
    	student.setSurname(surname);
    	student.setPesel(pesel);
    	
    	DatabaseConnector.getInstance().addStudent(student);    	
    	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	model.addAttribute("message", "Dodano nowego studenta");
         	
    	return "studentsList";
    }
      
    @RequestMapping(value="/DeleteStudent", method=RequestMethod.POST)
    public String deleteSchool(@RequestParam(value="studentId", required=false) String studentId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	DatabaseConnector.getInstance().deleteStudent(studentId);    	
    	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	model.addAttribute("message", "Student został usunięty");
         	
    	return "studentsList";
    }
    @RequestMapping(value="/EditStudent", method=RequestMethod.POST)
    public String edittudent(@RequestParam(value="studentId", required=false) String studentId,
    	   Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
 
    	model.addAttribute("studentId", studentId);
    	return "editStudentDataForm";
    }
    
    @RequestMapping(value="/CorrectStudentData", method=RequestMethod.POST)
    public String correctSchoolData(@RequestParam(value="studentId", required=true) String studentId,
    		@RequestParam(value="studentName", required=false) String name,
    		@RequestParam(value="studentSurname", required=false) String surname,
    		@RequestParam(value="studentPesel", required=false) String pesel,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
          	
    	DatabaseConnector.getInstance().editStudent(studentId, name, surname, pesel);
    	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	model.addAttribute("message", "Dane sstudenta zostały zmienione");
         	
    	return "studentsList";
    }

}