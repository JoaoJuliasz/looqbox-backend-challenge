package com.looqbox.persistence.model.dto;

import java.util.List;

public class PokemonRequest {
    private int count;
    private String next;
    private String previous;
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }

}
