package com.example.craps.view.out;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class SingleRoundOutView {

//    the response should contain
//    the stake
//    the game type
//    the outcome of the game
//          total win
//          additional details needed to trace the game play
//              (in here make sure to include all details
//               that would be needed by a front end
//               to visualize the flow of the game)

    private BigDecimal stake;

    private String gameType;
    private String outcome;
    private BigDecimal totalWin;
    private Integer initialRoll;
    private Integer playerPoint;
    private Integer finalRoll;
    private List<Integer> subsequentRollsList;


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

    public void setSubsequentRollsList(List<Integer> subsequentRollsList) {
        this.subsequentRollsList = subsequentRollsList;
    }
}
