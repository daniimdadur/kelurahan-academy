package org.security.kelurahanacademy.student.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.student.model.response.StudentRes;
import org.security.kelurahanacademy.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ModelAndView get() {
        ModelAndView view = new ModelAndView("pages/master/student/index");
        List<StudentRes> result = this.studentService.get();
        view.addObject("data", result);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView get(@PathVariable String id) {
        ModelAndView view = new ModelAndView("pages/master/student/detail");
        Optional<StudentRes> result = this.studentService.getById(id);
        view.addObject("student", result.get());
        return view;
    }
}
