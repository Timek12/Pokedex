package org.pokedex.pokedex.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.pokedex.pokedex.models.Attack;
import org.pokedex.pokedex.models.PokemonSpecies;
import org.pokedex.pokedex.services.DataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokemonTableController {
    @FXML
    private TilePane tilePane;

    @FXML
    private ComboBox<String> typeFilter;
    @FXML
    private ObservableList<PokemonSpecies> pokemonData = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {
        List<PokemonSpecies> pokemons = DataLoader.loadPokemonData("src/main/resources/data.json");
        pokemonData.addAll(pokemons);

        typeFilter.getItems().add("All");

        ArrayList<String> uniqueTypes = new ArrayList<>();
        for (PokemonSpecies pokemon : pokemonData) {
            for (String type : pokemon.getTypes()) {
                if (!uniqueTypes.contains(type)) {
                    uniqueTypes.add(type);
                }
            }
        }
        typeFilter.getItems().addAll(uniqueTypes);

        typeFilter.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            filterPokemons();
        });

        displayPokemons(pokemonData);
    }

    private void filterPokemons() {
        String selectedType = typeFilter.getSelectionModel().getSelectedItem();

        List<PokemonSpecies> filteredPokemons = new ArrayList<>();
        for (PokemonSpecies pokemon : pokemonData) {
            if (selectedType.equals("All") || pokemon.getTypes().contains(selectedType)) {
                filteredPokemons.add(pokemon);
            }
        }

        try {
            displayPokemons(filteredPokemons);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayPokemons(List<PokemonSpecies> pokemons) throws IOException {
        // Clear the previous Pokemon cards
        tilePane.getChildren().clear();

        for (PokemonSpecies pokemon : pokemons) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/pokedex/pokedex/PokemonCard.fxml"));
            VBox card = loader.load();

            ImageView pokemonImage = (ImageView) card.lookup("#pokemonImage");
            Label pokemonName = (Label) card.lookup("#pokemonName");
            Label pokemonTypes = (Label) card.lookup("#pokemonTypes");
            Label pokemonHeight = (Label) card.lookup("#pokemonHeight");
            Label pokemonWeight = (Label) card.lookup("#pokemonWeight");
            Label pokemonAttacks = (Label) card.lookup("#pokemonAttacks");
            Label pokemonEvolutions = (Label) card.lookup("#pokemonEvolutions");

            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + pokemon.getName().toLowerCase() + ".png")));
            pokemonImage.setImage(image);

            pokemonName.setText(pokemon.getName());
            pokemonTypes.setText("Types: " + String.join(", ", pokemon.getTypes()));
            pokemonWeight.setText("Weight: " + pokemon.getSize().getWeight());
            pokemonHeight.setText("Height: " + pokemon.getSize().getHeight());
            pokemonEvolutions.setText("Evolutions: " + String.join(", ", pokemon.getEvolutions()));

            List<String> attackNames = new ArrayList<>();
            for (Attack attack : pokemon.getAttacks()) {
                attackNames.add(attack.getName());
            }
            pokemonAttacks.setText("Attacks: " + String.join(", ", attackNames));

            tilePane.getChildren().add(card);
        }
    }
}