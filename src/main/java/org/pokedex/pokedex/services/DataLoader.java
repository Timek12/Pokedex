package org.pokedex.pokedex.services;

import org.pokedex.pokedex.models.PokemonSpecies;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

// This class converts json objects (in data.json file) to Java objects
public class DataLoader {
    public static List<PokemonSpecies> loadPokemonData(String filePath) throws IOException {
        // Create a tool for converting between Java objects and JSON
        ObjectMapper mapper = new ObjectMapper();

        // Use the tool to read the JSON file and convert it into a list of PokemonSpecies objects
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructCollectionType(List.class, PokemonSpecies.class));
    }
}