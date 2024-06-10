package org.pokedex.pokedex.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.pokedex.pokedex.models.Attack;


/**
 * This controller is managing the individual attack.
 */
public class AttackItemController {
    @FXML
    private Label attackName;
    @FXML
    private Label attackDescription;

    public void setAttack(Attack attack) {
        attackName.setText(attack.getName());
        attackDescription.setText(attack.getDescription());
    }
}