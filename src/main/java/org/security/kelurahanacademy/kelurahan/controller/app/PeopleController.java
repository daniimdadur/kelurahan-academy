package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.response.PeopleRes;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;
import org.security.kelurahanacademy.kelurahan.service.PeopleService;
import org.security.kelurahanacademy.kelurahan.service.RTService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final RTService rtService;

    private void addObject(ModelAndView view) {
        List<RTRes> res = this.rtService.get();
        view.addObject("rt", res);
    }

    @GetMapping
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("pages/master/kelurahan/people/index");
        List<PeopleRes> result = this.peopleService.get();
        mav.addObject("data", result);
        return mav;
    }
}
