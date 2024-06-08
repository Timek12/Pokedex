package org.pokedex.pokedex.models;

import java.util.List;

public class PokemonSpecies {
    private String name;
    private Size size;
    private List<String> types;
    private List<Attack> attacks;
    private List<String> evolutions;

    public String getName() {
        return this.name;
    }

    public Size getSize() {
        return this.size;
    }

    public List<String> getTypes() {
        return this.types;
    }

    public List<Attack> getAttacks() {
        return this.attacks;
    }

    public List<String> getEvolutions() {
        return this.evolutions;
    }
}