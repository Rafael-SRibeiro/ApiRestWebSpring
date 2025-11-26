package com.rafael.ribeiro.demoprojetoapirest.Configuration;

import jakarta.annotation.PostConstruct;

import java.util.*;

public class TimezoneConfiguration {

    @PostConstruct
    public void TimeZoneConfig (){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_paulo"));



    }



}
