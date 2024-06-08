// Klasa PokemonCard
package org.pokedex.pokedex;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.pokedex.pokedex.models.Attack;
import org.pokedex.pokedex.models.PokemonSpecies;

import java.util.stream.Collectors;

public class PokemonCard extends VBox {
    public PokemonCard(PokemonSpecies pokemon) {
        Label nameLabel = new Label("Name: " + pokemon.getName());
        Label sizeLabel = new Label("Size: " + pokemon.getSize().getHeight() + "m, " + pokemon.getSize().getWeight() + "kg");
        Label typesLabel = new Label("Types: " + String.join(", ", pokemon.getTypes()));
        Label attacksLabel = new Label("Attacks: " + pokemon.getAttacks().stream().map(Attack::getName).collect(Collectors.joining(", ")));
        Label evolutionsLabel = new Label("Evolutions: " + String.join(", ", pokemon.getEvolutions()));

        this.getChildren().addAll(nameLabel, sizeLabel, typesLabel, attacksLabel, evolutionsLabel);
    }
}