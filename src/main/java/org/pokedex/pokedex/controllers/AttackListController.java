package org.pokedex.pokedex.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pokedex.pokedex.models.Attack;
import org.pokedex.pokedex.models.PokemonSpecies;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This controller is responsible for managing the list of attacks.
 * It fetches all the unique attacks from the list of Pokemon and displays them in a ListView.
 */
public class AttackListController {
    @FXML
    private ListView<VBox> attackList;

    private final List<PokemonSpecies> pokemonData;

    public AttackListController(List<PokemonSpecies> pokemonData) {
        this.pokemonData = pokemonData;
    }

    public void initialize() throws IOException {
        Set<Attack> uniqueAttacks = new HashSet<>();
        for (PokemonSpecies pokemon : pokemonData) {
            uniqueAttacks.addAll(pokemon.getAttacks());
        }

        for (Attack attack : uniqueAttacks) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/pokedex/pokedex/AttackItem.fxml"));
            VBox attackItem = loader.load();
            AttackItemController controller = loader.getController();
            controller.setAttack(attack);
            attackList.getItems().add(attackItem);
        }
    }

    public void close() {
        Stage stage = (Stage) attackList.getScene().getWindow();
        stage.close();
    }
}