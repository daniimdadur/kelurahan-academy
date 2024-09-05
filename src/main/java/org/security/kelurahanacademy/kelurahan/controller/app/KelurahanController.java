package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;
import org.security.kelurahanacademy.kelurahan.model.response.RWRes;
import org.security.kelurahanacademy.kelurahan.service.DusunService;
import org.security.kelurahanacademy.kelurahan.service.KelurahanService;
import org.security.kelurahanacademy.kelurahan.service.RTService;
import org.security.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kelurahan")
public class KelurahanController {
    private final KelurahanService kelurahanService;
    private final DusunService dusunService;
    private final RWService rwService;
    private final RTService rtService;

    @GetMapping
    public ModelAndView get() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/index");

        List<KelurahanRes> kelurahanList = this.kelurahanService.get();
        view.addObject("data", kelurahanList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-kelurahan");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        view.addObject("kelurahan", kelurahan.get());
        return view;
    }

    @GetMapping("/{id}/dusun/{dusunId}")
    public ModelAndView dusunDetail(@PathVariable("id") String id,
                              @PathVariable("dusunId") String dusunId) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-dusun");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        if (kelurahan.isPresent()) {
            Optional<DusunRes> dusun = this.dusunService.getById(dusunId);
            view.addObject("kelurahan", kelurahan.get());
            view.addObject("dusun", dusun.get());
            return view;
        }
        return new ModelAndView("redirect:/kelurahan/{id}");
    }

   @GetMapping("/{id}/dusun/{dusunId}/rw/{rwId}")
   public ModelAndView rwDetail(@PathVariable("id") String id,
                                @PathVariable("dusunId") String dusunId,
                                @PathVariable("rwId") String rwId) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-rw");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        if (kelurahan.isPresent()) {
            Optional<DusunRes> dusun = this.dusunService.getById(dusunId);
            if (dusun.isPresent()) {
                Optional<RWRes> rw = this.rwService.getById(rwId);
                view.addObject("kelurahan", kelurahan.get());
                view.addObject("dusun", dusun.get());
                view.addObject("rw", rw.get());
                return view;
            }
        }
        return new ModelAndView("redirect:/kelurahan/{id}/dusun/{dusunId}");
   }

   @GetMapping("/{id}/dusun/{dusunId}/rw/{rwId}/rt/{rtId}")
   public ModelAndView rtDetail(@PathVariable("id") String id,
                                @PathVariable("dusunId") String dusunId,
                                @PathVariable("rwId") String rwId,
                                @PathVariable("rtId") String rtId) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-rt");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        if (kelurahan.isPresent()) {
            Optional<DusunRes> dusun = this.dusunService.getById(dusunId);
            if (dusun.isPresent()) {
                Optional<RWRes> rw = this.rwService.getById(rwId);
                if (rw.isPresent()) {
                    Optional<RTRes> rt = this.rtService.getById(rtId);
                    view.addObject("kelurahan", kelurahan.get());
                    view.addObject("dusun", dusun.get());
                    view.addObject("rw", rw.get());
                    view.addObject("rt", rt.get());
                    return view;
                }
            }
        }
        return new ModelAndView("redirect:/kelurahan/{id}/dusun/{dusunId}/rw/{rwId}");
   }
}
