package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.security.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kelurahan")
public class KelurahanController {
    private final KelurahanService kelurahanService;

    @GetMapping
    public ModelAndView get() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/index");
        List<KelurahanRes> kelurahanList = this.kelurahanService.get();
        view.addObject("data", kelurahanList);
        return view;
    }
}
