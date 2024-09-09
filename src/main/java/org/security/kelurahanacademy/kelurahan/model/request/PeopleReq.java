package org.security.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleReq {
    private String id;
    private Integer nik;
    private String name;
    private String gender;
    private String age;
    private String rtId;

    public PeopleReq(String id, Integer nik, String name, String gender, String age) {
        this.id = id;
        this.nik = nik;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
