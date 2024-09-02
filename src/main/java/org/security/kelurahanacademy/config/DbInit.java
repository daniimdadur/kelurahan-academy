package org.security.kelurahanacademy.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.RWEntity;
import org.security.kelurahanacademy.kelurahan.repo.KelurahanRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final KelurahanRepo kelurahanRepo;

    @Override
    public void run(String... args) throws Exception {
        initKeluraha();
    }
    private void initKeluraha() {
        if (!kelurahanRepo.findAll().isEmpty()) {
            return;
        }

        List<KelurahanEntity> kelurahanEntities = new ArrayList<>();
        KelurahanEntity sukahurip = new KelurahanEntity("19045a79-3482-4f10-8ed9-0b95bd213085", "Sukahurip", "Pamarican");
        DusunEntity ciparakan = new DusunEntity("4426e286-be84-4e8f-8109-8bc98d7c7193", "Ciparakan", "Bpk Masrur");
        sukahurip.addDusun(ciparakan);
        RWEntity rw01 = new RWEntity("305ce1b8-909c-4f47-bc21-5efbbcfe5ed4", "RW 01", "Bpk Tori");
        ciparakan.addRW(rw01);

        KelurahanEntity pamarican = new KelurahanEntity("1e6189b9-24cc-44cf-b59c-966cf5b3f2bb", "Pamarican", "Pamarican");
        DusunEntity kebonTengah = new DusunEntity("82006a11-3e31-4664-a0d8-cb4d40cd364c", "Kebon Tengah", "Bpk Dudu");
        pamarican.addDusun(kebonTengah);
        RWEntity rw02 = new RWEntity("2a9ed7f6-ddd1-4831-8b7d-5b1b34f4f3ae", "RW 02", "Bpk Ardi");
        kebonTengah.addRW(rw02);

        //generate
        kelurahanEntities.add(sukahurip);
        kelurahanEntities.add(pamarican);

        try {
            this.kelurahanRepo.saveAll(kelurahanEntities);
            log.info("save kelurahan successfully");
        } catch (Exception e) {
            log.error("save kelurahan failed, error: {}", e.getMessage());
        }
    }
}
