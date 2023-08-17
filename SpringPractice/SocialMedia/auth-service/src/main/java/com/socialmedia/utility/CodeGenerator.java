package com.socialmedia.utility;

import java.util.UUID;

public class CodeGenerator {

    // random bir kod oluşturacağız
    // random bir uuid oluşturup
    // afc1fe6-d669-414e-b066-e0733f0de7a8
    // ad4be
    // metotda bize string olarak kodu döndürsün

    public static String generatedCode() {
        String uuid = UUID.randomUUID().toString();
        String[] data = uuid.split("-");
        String newCode = "";
        int i = 0;

        while (i < data.length) {
            newCode += data[i].charAt(0);
            i++;
        }
        return newCode;

    }

}
