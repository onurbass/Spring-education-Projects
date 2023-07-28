package com.onurbas.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        //dosyaları util paketine taşıdık. Spring ana paket dışındaki paketleri okuyamaz bu nedenle o paketleri elle burada tanıttık
        scanBasePackages = {"com.onurbas.springcoredemo",
                "com.onurbas.util"}
)
public class SpringcoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }

}
