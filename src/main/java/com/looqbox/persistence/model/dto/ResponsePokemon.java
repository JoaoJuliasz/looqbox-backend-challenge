package com.looqbox.persistence.model.dto;

import java.util.List;

public class ResponsePokemon<T> {

    private List<T> results;

    public ResponsePokemon() {
    }

    public ResponsePokemon(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
