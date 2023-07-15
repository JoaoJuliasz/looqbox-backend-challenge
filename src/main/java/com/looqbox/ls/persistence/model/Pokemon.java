package com.looqbox.ls.persistence.model;

public class Pokemon {

    String name;
    String highlight;

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


    //
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Pokemon pokemon = (Pokemon) o;
//        return Objects.equals(name, pokemon.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
}
