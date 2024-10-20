package com.example.craps.view.in;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
public class SingleRoundInView {

//    the request details should contain
//    the stake
//    the game type

    @NotNull
    private BigDecimal stake;

    @NotNull
    @Length(max = 255)
    private String gameType;

    public SingleRoundInView() {
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
