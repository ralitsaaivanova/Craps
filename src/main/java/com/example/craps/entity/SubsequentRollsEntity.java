package com.example.craps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity(name = "subsequent_rolls")
public class SubsequentRollsEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false) // Foreign key to CrapsGameEntity
    private CrapsGameEntity crapsGame;

    @Column(name = "roll", nullable = false)
    private Integer roll;

    public SubsequentRollsEntity() {
    }

    public void setCrapsGame(CrapsGameEntity crapsGame) {
        this.crapsGame = crapsGame;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }
}
