package org.security.kelurahanacademy.constant;

import java.util.UUID;

public class GenerateUUIDs {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }
}
