package com.stash.application.data.models;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Data model for general details to be shared within activities
 */
@Getter
@Setter
public  class StashGeneral {
    private String homeDisplayMessage;
    private Map<String,Integer> statesIdmap;


}
