package com.example.craps.view.in;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
public class ManyRoundsInView {

//    the request details should contain
//    the stake
//    the game type
//    the number of rounds to be played

    @NotNull
    private BigDecimal stake;

    @NotNull
    @Length(max = 255)
    private String gameType;

    @NotNull
    private Long rounds;

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setRounds(Long rounds) {
        this.rounds = rounds;
    }
}
