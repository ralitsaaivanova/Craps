package com.example.craps.service;

import com.example.craps.view.in.ManyRoundsInView;
import com.example.craps.view.in.SingleRoundInView;
import com.example.craps.view.out.ManyRoundsOutView;
import com.example.craps.view.out.SingleRoundOutView;

public interface ICrapsGameService {
    SingleRoundOutView playRound(SingleRoundInView singleRoundInView);

    ManyRoundsOutView playManyRounds(ManyRoundsInView manyRoundsInView);
}
