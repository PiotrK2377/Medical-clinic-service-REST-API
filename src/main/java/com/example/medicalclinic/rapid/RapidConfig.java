package com.example.medicalclinic.rapid;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RapidConfig {

    @Value("${rapid.doctors.api.key}")
    private String rapidKey;

    @Value("${rapid.doctors.api.host}")
    private String rapidHost;

    @Value("${rapid.doctors.api.npi}")
    private String rapidNpi;
}
