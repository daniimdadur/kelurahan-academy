package org.security.kelurahanacademy.kelurahan.controller.app;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.request.*;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;
import org.security.kelurahanacademy.kelurahan.model.response.RWRes;
import org.security.kelurahanacademy.kelurahan.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    private final PeopleService peopleService;

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
        if (kelurahan.isPresent()) {
            view.addObject("kelurahan", kelurahan.get());
            return view;
        }

        return new ModelAndView("pages/auth/error-404");
    }

    @GetMapping("/{id}/dusun/{dusunId}")
    public ModelAndView dusunDetail(@PathVariable("id") String id,
                              @PathVariable("dusunId") String dusunId) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-dusun");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        if (kelurahan.isPresent()) {
            view.addObject("kelurahan", kelurahan.get());
            Optional<DusunRes> dusun = this.dusunService.getById(dusunId);
            if (dusun.isPresent()) {
                view.addObject("dusun", dusun.get());
                return view;
            }
        }
        return new ModelAndView("pages/auth/error-404");
    }

   @GetMapping("/{id}/dusun/{dusunId}/rw/{rwId}")
   public ModelAndView rwDetail(@PathVariable("id") String id,
                                @PathVariable("dusunId") String dusunId,
                                @PathVariable("rwId") String rwId) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-rw");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        if (kelurahan.isPresent()) {
            view.addObject("kelurahan", kelurahan.get());
            Optional<DusunRes> dusun = this.dusunService.getById(dusunId);
            if (dusun.isPresent()) {
                view.addObject("dusun", dusun.get());
                Optional<RWRes> rw = this.rwService.getById(rwId);
                if (rw.isPresent()) {
                    view.addObject("rw", rw.get());
                    return view;
                }
            }
        }

        return new ModelAndView("pages/auth/error-404");
   }

   @GetMapping("/{id}/dusun/{dusunId}/rw/{rwId}/rt/{rtId}")
   public ModelAndView rtDetail(@PathVariable("id") String id,
                                @PathVariable("dusunId") String dusunId,
                                @PathVariable("rwId") String rwId,
                                @PathVariable("rtId") String rtId) {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/detail-rt");

        Optional<KelurahanRes> kelurahan = this.kelurahanService.getById(id);
        if (kelurahan.isPresent()) {
            view.addObject("kelurahan", kelurahan.get());
            Optional<DusunRes> dusun = this.dusunService.getById(dusunId);
            if (dusun.isPresent()) {
                view.addObject("dusun", dusun.get());
                Optional<RWRes> rw = this.rwService.getById(rwId);
                if (rw.isPresent()) {
                    view.addObject("rw", rw.get());
                    Optional<RTRes> rt = this.rtService.getById(rtId);
                    if (rt.isPresent()) {
                        view.addObject("rt", rt.get());
                        return view;
                    }
                }
            }
        }

        return new ModelAndView("pages/auth/error-404");
   }

   @GetMapping("/add")
   public ModelAndView add() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/kelurahan/add");

       KelurahanReq request = new KelurahanReq();
       view.addObject("kelurahan", request);
       return view;
   }

   @PostMapping("/save")
   public ModelAndView save(@ModelAttribute KelurahanReq request) {
        this.kelurahanService.save(request);
        return new ModelAndView("redirect:/kelurahan");
   }

   @GetMapping("/_add")
    public ModelAndView _add() {
        ModelAndView view = new ModelAndView("pages/master/kelurahan/kelurahan/add-one-page");

       KelurahanReq kelurahan = new KelurahanReq();

       List<DusunReq> dusunList = new ArrayList<>();

       //warga
       ArrayList<PeopleReq> warga1 = new ArrayList<>();
       warga1.add(new PeopleReq("", 0, "", "", ""));

       //rt
       ArrayList<RTReq> rt1 = new ArrayList<>();
       rt1.add(new RTReq("", "", "", warga1));

       //rw
       ArrayList<RWReq> rw1 = new ArrayList<>();
       rw1.add(new RWReq("", "", "", rt1));

       //dusun
       DusunReq dusun1 = new DusunReq("", "", "", rw1);
       dusunList.add(dusun1);

       //add to kelurahan
       kelurahan.setDusunList(dusunList);

       view.addObject("desa", kelurahan);
       return view;
   }

   @PostMapping("/_save")
    public ModelAndView _save(@ModelAttribute KelurahanReq kelurahanReq) {
        Optional<KelurahanRes> kelurahan =  this.kelurahanService.save(kelurahanReq);
        if (kelurahan.isPresent()) {
            for (DusunReq dusun : kelurahanReq.getDusunList()) {
                dusun.setKelurahanId(kelurahanReq.getId());
                this.dusunService.save(dusun);

                for (RWReq rw : dusun.getRwList()) {
                    rw.setDusunId(dusun.getId());
                    this.rwService.save(rw);

                    for (RTReq rt : rw.getRtList()) {
                        rt.setRwId(rw.getId());
                        this.rtService.save(rt);

                        for (PeopleReq people : rt.getPeopleList()) {
                            people.setRtId(rt.getId());
                            this.peopleService.save(people);
                            return new ModelAndView("redirect:/kelurahan");
                        }
                        return new ModelAndView("pages/auth/error-404");
                    }
                    return new ModelAndView("pages/auth/error-404");
                }
                return new ModelAndView("pages/auth/error-404");
            }
            return new ModelAndView("pages/auth/error-404");
        }
        return new ModelAndView("pages/auth/error-404");
   }
}
