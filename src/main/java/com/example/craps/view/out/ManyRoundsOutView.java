package com.example.craps.view.out;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ManyRoundsOutView {

//    the response should contain
//    the sum of all stakes
//    the sum of all wins
//    the sum of all wins/the sum of all stakes

    private BigDecimal allStakes;
    private BigDecimal allWins;

    private BigDecimal winsDividedByStakes;

    public void setAllStakes(BigDecimal allStakes) {
        this.allStakes = allStakes;
    }

    public void setAllWins(BigDecimal allWins) {
        this.allWins = allWins;
    }

    public void setWinsDividedByStakes(BigDecimal winsDividedByStakes) {
        this.winsDividedByStakes = winsDividedByStakes;
    }
}
