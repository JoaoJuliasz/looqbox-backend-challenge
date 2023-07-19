package com.looqbox.persistence.model.dto;

import java.util.Objects;

public class Pokemon implements Comparable<Pokemon>{

    private String name;
    private String highlight;

    public Pokemon() {}

    public Pokemon(String name) {
        this.name = name;
    }

    public Pokemon(String name, String highlight) {
        this.name = name;
        this.highlight = highlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name) && Objects.equals(highlight, pokemon.highlight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, highlight);
    }

    public int compareTo(Pokemon other) {
        // Implement the comparison logic based on your requirements
        return this.getName().compareTo(other.getName());
    }
}
