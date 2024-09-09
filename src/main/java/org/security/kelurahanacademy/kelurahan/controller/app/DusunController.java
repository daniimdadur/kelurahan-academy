package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.security.kelurahanacademy.kelurahan.service.DusunService;
import org.security.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dusun")
public class DusunController {
    private final DusunService dusunService;
    private final KelurahanService kelurahanService;

    public void addObject(ModelAndView view) {
        List<KelurahanRes> kelurahanRes = this.kelurahanService.get();
        view.addObject("kelurahan", kelurahanRes);
    }

    @GetMapping
    public ModelAndView get() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/dusun/index");
        List<DusunRes> result = this.dusunService.get();
        view.addObject("data", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/dusun/add");
        DusunReq request = new DusunReq();
        view.addObject("dusun", request);
        addObject(view);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute DusunReq request) {
        this.dusunService.save(request);
        return new ModelAndView("redirect:/dusun");
    }
}
