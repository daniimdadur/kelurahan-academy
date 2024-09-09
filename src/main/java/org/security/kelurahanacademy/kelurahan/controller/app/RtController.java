package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;
import org.security.kelurahanacademy.kelurahan.model.response.RWRes;
import org.security.kelurahanacademy.kelurahan.service.RTService;
import org.security.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rt")
public class RtController {
    private final RTService rtService;
    private final RWService rwService;

    private void addObject(ModelAndView view) {
        List<RWRes> rw = this.rwService.get();
        view.addObject("rw", rw);
    }

    @GetMapping
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("pages/master/kelurahan/rt/index");
        List<RTRes> rt = this.rtService.get();
        mav.addObject("data", rt);
        return mav;
    }
}
