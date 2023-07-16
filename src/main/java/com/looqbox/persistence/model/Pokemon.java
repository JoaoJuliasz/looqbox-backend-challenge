package com.looqbox.persistence.model;

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

    public int compareTo(Pokemon other) {
        // Implement the comparison logic based on your requirements
        return this.getName().compareTo(other.getName());
    }
}
