package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.response.RWRes;
import org.security.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rw")
public class RwController {
    private final RWService rwService;

    @GetMapping
    public ModelAndView get() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/rw/index");
        List<RWRes> result = this.rwService.get();
        view.addObject("data", result);
        return view;
    }
}
