package com.example.craps.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity(name = "craps_game")
public class CrapsGameEntity extends BaseEntity{

    @Column(name="stake",precision = 15,scale = 2,nullable = false)
    private BigDecimal stake;

    @Column(name="game_type",length = 255,nullable = false)
    private String gameType;

    @Column(name="outcome",nullable = true)
    private String outcome;

    @Column(name="total_win",precision = 15,scale = 2,nullable = true)
    private BigDecimal totalWin;

    @Column(name="initial_roll",nullable = true)
    private Integer initialRoll;

    @Column(name="player_point",nullable = true)
    private Integer playerPoint;

    @Column(name="final_roll",nullable = true)
    private Integer finalRoll;

    @OneToMany(mappedBy = "crapsGame", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubsequentRollsEntity> subsequentRolls;

    public CrapsGameEntity() {
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public void setTotalWin(BigDecimal totalWin) {
        this.totalWin = totalWin;
    }

    public void setInitialRoll(Integer initialRoll) {
        this.initialRoll = initialRoll;
    }

    public void setPlayerPoint(Integer playerPoint) {
        this.playerPoint = playerPoint;
    }

    public void setFinalRoll(Integer finalRoll) {
        this.finalRoll = finalRoll;
    }

    public void setSubsequentRolls(List<SubsequentRollsEntity> subsequentRolls) {
        this.subsequentRolls = subsequentRolls;
    }
}
