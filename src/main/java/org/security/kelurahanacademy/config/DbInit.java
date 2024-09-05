package org.security.kelurahanacademy.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.*;
import org.security.kelurahanacademy.kelurahan.repo.KelurahanRepo;
import org.security.kelurahanacademy.student.model.entity.ClassEntity;
import org.security.kelurahanacademy.student.model.entity.HistoryEntity;
import org.security.kelurahanacademy.student.model.entity.StudentEntity;
import org.security.kelurahanacademy.student.repo.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final KelurahanRepo kelurahanRepo;
    private final StudentRepo studentRepo;

    @Override
    public void run(String... args) throws Exception {
        initKelurahan();
        initStudent();
    }
    private void initKelurahan() {
        if (!kelurahanRepo.findAll().isEmpty()) {
            return;
        }

        List<KelurahanEntity> kelurahanEntities = new ArrayList<>();
        KelurahanEntity sukahurip = new KelurahanEntity("19045a79-3482-4f10-8ed9-0b95bd213085", "Sukahurip", "Pamarican");
        DusunEntity ciparakan = new DusunEntity("4426e286-be84-4e8f-8109-8bc98d7c7193", "Ciparakan", "Bpk Masrur");
        sukahurip.addDusun(ciparakan);
        RWEntity rw01 = new RWEntity("305ce1b8-909c-4f47-bc21-5efbbcfe5ed4", "RW 01", "Bpk Tori");
        ciparakan.addRW(rw01);
        RTEntity rt01 = new RTEntity("8b35cdb5-bda8-49bc-b831-c44bd9d6e7e7", "RT 01", "Bpk Emon");
        rw01.addRt(rt01);
        PeopleEntity sabil = new PeopleEntity("5d89114d-a428-4e3b-9acc-6131fe7073bf",1111 ,"Sabilla", "Wanita", "50");
        rt01.addPeople(sabil);

        KelurahanEntity pamarican = new KelurahanEntity("1e6189b9-24cc-44cf-b59c-966cf5b3f2bb", "Pamarican", "Pamarican");
        DusunEntity kebonTengah = new DusunEntity("82006a11-3e31-4664-a0d8-cb4d40cd364c", "Kebon Tengah", "Bpk Dudu");
        pamarican.addDusun(kebonTengah);
        RWEntity rw02 = new RWEntity("2a9ed7f6-ddd1-4831-8b7d-5b1b34f4f3ae", "RW 02", "Bpk Ardi");
        kebonTengah.addRW(rw02);
        RTEntity rt02 = new RTEntity("1f6be45f-78cb-4a1f-a641-a8dc6d47fe20", "Rt 02", "Bpk Ijad");
        rw02.addRt(rt02);
        PeopleEntity adit = new PeopleEntity("fd7565f6-7439-4d13-a6cb-ff59e1412bbd", 2222, "Adit", "Pria", "30");
        rt02.addPeople(adit);

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

    private void initStudent() {
        if (!studentRepo.findAll().isEmpty()) {
            return;
        }

        List<StudentEntity> studentEntities = new ArrayList<>();
        StudentEntity jahfal = new StudentEntity("37ae6ea2-f253-43a9-bb9a-1a98e30ff8a5", 1111, "Jahfal", "Hasan", "Bandung");

        HistoryEntity sd = new HistoryEntity("99988716-5908-4014-b398-e07293193973", "SDN 1 Bandung", "Jln. South Bandung", "South Bandung");

        ClassEntity sd01 = new ClassEntity("d8de831a-f5d2-4c7e-9a8a-0b44bd3be3cb", "Class 01", LocalDate.of(2010, 1, 1), LocalDate.of(2011, 1, 1), "Musinah");
        ClassEntity sd02 = new ClassEntity("9439376f-d9c2-46c2-a715-01356d8a1a4d", "Class 02", LocalDate.of(2011, 1, 1), LocalDate.of(2012, 1, 1), "Siska");
        ClassEntity sd03 = new ClassEntity("e4bc4732-2527-4915-ae4b-a2ea26b657d5", "Class 03", LocalDate.of(2012, 1, 1), LocalDate.of(2013, 1, 1), "Nurliani");
        ClassEntity sd04 = new ClassEntity("d21ad7e8-b142-478e-9733-bb39d143f6ce", "Class 04", LocalDate.of(2013, 1, 1), LocalDate.of(2014, 1, 1), "Nuaraini");
        ClassEntity sd05 = new ClassEntity("31ba3457-a85c-4dc5-83c6-4ddadaa1c05a", "Class 05", LocalDate.of(2014, 1, 1), LocalDate.of(2015, 1, 1), "Sali");
        ClassEntity sd06 = new ClassEntity("7c20b4cc-a78d-4b51-ac0d-f66c487c9108", "Class 06", LocalDate.of(2015, 1, 1), LocalDate.of(2016, 1, 1), "Siti");

        jahfal.addSchoolHistory(sd);
        sd.addSchoolClass(sd01);
        sd.addSchoolClass(sd02);
        sd.addSchoolClass(sd03);
        sd.addSchoolClass(sd04);
        sd.addSchoolClass(sd05);
        sd.addSchoolClass(sd06);

        HistoryEntity smp = new HistoryEntity("1a120d9b-ae13-4de6-8b85-090c88188aa8", "SMPN 1 Bandung", "Jln. North Bandung", "North Bandung");

        ClassEntity smp01 = new ClassEntity("8b3e7ed3-09b1-4a82-b456-7264e0b5a0f1", "Class 07", LocalDate.of(2016, 1, 1), LocalDate.of(2017, 1, 1), "Budi");
        ClassEntity smp02 = new ClassEntity("9d2f8c44-2c59-45f3-94dd-e5c7e0659c7e", "Class 08", LocalDate.of(2017, 1, 1), LocalDate.of(2018, 1, 1), "Ani");
        ClassEntity smp03 = new ClassEntity("ad5e6c54-3a48-41d7-86bd-19c7a928e7fb", "Class 09", LocalDate.of(2018, 1, 1), LocalDate.of(2019, 1, 1), "Rudi");

        jahfal.addSchoolHistory(smp);
        smp.addSchoolClass(smp01);
        smp.addSchoolClass(smp02);
        smp.addSchoolClass(smp03);

        HistoryEntity sma = new HistoryEntity("0cf8f234-a0f0-4321-a295-fdace4d79f93", "SMAN 1 Bandung", "Jln. East Bandung", "East Bandung");

        ClassEntity sma01 = new ClassEntity("bc7e8f65-4a2c-4577-87dd-21c8b928d8cb", "Class 10", LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1), "Mira");
        ClassEntity sma02 = new ClassEntity("ce9f705e-5a3f-4568-98ee-35a8c928e9dc", "Class 11", LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1), "Doni");
        ClassEntity sma03 = new ClassEntity("ea8dc4f1-7d8e-4254-af05-2534c2c9ce1c", "Class 12", LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1), "Rayhan");

        jahfal.addSchoolHistory(sma);
        sma.addSchoolClass(sma01);
        sma.addSchoolClass(sma02);
        sma.addSchoolClass(sma03);

        StudentEntity rahma = new StudentEntity("5f3d8e7a-bb3b-4f6b-bb9c-4fce1d1f3e7b", 2222, "Rahma", "Putri", "Jakarta");

        HistoryEntity sdr = new HistoryEntity("89cf4a5d-3f24-4321-a5e9-b9f3d8e7c9f7", "SDN 2 Jakarta", "Jln. South Jakarta", "South Jakarta");

        ClassEntity sd1 = new ClassEntity("f3d5b9b6-12f3-4d7a-8a1c-0f4a8f9c3b7e", "Class 01", LocalDate.of(2011, 7, 1), LocalDate.of(2012, 6, 30), "Aisyah");
        ClassEntity sd2 = new ClassEntity("d3f9b4f6-79e8-40f3-a7c3-4f2a6d7e8f9c", "Class 02", LocalDate.of(2012, 7, 1), LocalDate.of(2013, 6, 30), "Bambang");
        ClassEntity sd3 = new ClassEntity("b4c3e7d9-1a3f-4f6b-a8c1-0d3f8a5b9f6c", "Class 03", LocalDate.of(2013, 7, 1), LocalDate.of(2014, 6, 30), "Dewi");
        ClassEntity sd4 = new ClassEntity("c9d7e8a1-4f3b-4d6c-a7f9-1b5f3e6a9b2c", "Class 04", LocalDate.of(2014, 7, 1), LocalDate.of(2015, 6, 30), "Fitri");
        ClassEntity sd5 = new ClassEntity("e7f3a6b9-8d4c-4a7f-b2c1-0f9a5e3c8d7e", "Class 05", LocalDate.of(2015, 7, 1), LocalDate.of(2016, 6, 30), "Gina");
        ClassEntity sd6 = new ClassEntity("f4a3e5b7-9d2f-4c6a-b1f9-0c3f8e7a9b5d", "Class 06", LocalDate.of(2016, 7, 1), LocalDate.of(2017, 6, 30), "Hidayat");

        rahma.addSchoolHistory(sdr);
        sdr.addSchoolClass(sd1);
        sdr.addSchoolClass(sd2);
        sdr.addSchoolClass(sd3);
        sdr.addSchoolClass(sd4);
        sdr.addSchoolClass(sd5);
        sdr.addSchoolClass(sd6);

        HistoryEntity smpr = new HistoryEntity("2a9c7f6b-4f3d-4a7e-8b9c-1f5d3e7a9c8f", "SMPN 2 Jakarta", "Jln. North Jakarta", "North Jakarta");

        ClassEntity smp1 = new ClassEntity("9c8a7f5d-3f2e-4b7c-a1f9-0e3a6f5b8d7c", "Class 07", LocalDate.of(2017, 7, 1), LocalDate.of(2018, 6, 30), "Indra");
        ClassEntity smp2 = new ClassEntity("1f9b8c7d-4f2e-4a7f-b9c1-0d3f6e8a7b9c", "Class 08", LocalDate.of(2018, 7, 1), LocalDate.of(2019, 6, 30), "Joko");
        ClassEntity smp3 = new ClassEntity("3b2e7c9d-4f3a-4f7b-8c1f-0a9f7e6d5c2a", "Class 09", LocalDate.of(2019, 7, 1), LocalDate.of(2020, 6, 30), "Kartika");

        rahma.addSchoolHistory(smpr);
        smpr.addSchoolClass(smp1);
        smpr.addSchoolClass(smp2);
        smpr.addSchoolClass(smp3);

        HistoryEntity smar = new HistoryEntity("4d9a8b7c-3f1e-4f6b-a2c9-0e5d3a7b8c9f", "SMAN 2 Jakarta", "Jln. West Jakarta", "West Jakarta");

        ClassEntity sma1 = new ClassEntity("8f7a6b9c-1d3e-4a7f-b2c1-0f5d9c8a3b7e", "Class 10", LocalDate.of(2020, 7, 1), LocalDate.of(2021, 6, 30), "Lina");
        ClassEntity sma2 = new ClassEntity("6d8c7e9f-3f2a-4b7c-9f1a-0d7e8a5f9b3c", "Class 11", LocalDate.of(2021, 7, 1), LocalDate.of(2022, 6, 30), "Murni");
        ClassEntity sma3 = new ClassEntity("7b6c9d8a-4f1e-4a7f-b9c2-0f3e8a7d9b5c", "Class 12", LocalDate.of(2022, 7, 1), LocalDate.of(2023, 6, 30), "Nurul");

        rahma.addSchoolHistory(smar);
        smar.addSchoolClass(sma1);
        smar.addSchoolClass(sma2);
        smar.addSchoolClass(sma3);

        //generate data
        studentEntities.add(jahfal);
        studentEntities.add(rahma);

        try {
            this.studentRepo.saveAll(studentEntities);
            log.info("save student successfully");
        } catch (Exception e) {
            log.error("save student failed, error: {}", e.getMessage());
        }
    }
}
