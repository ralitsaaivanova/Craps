package com.example.craps.controller;


import com.example.craps.service.ICrapsGameService;
import com.example.craps.view.in.ManyRoundsInView;
import com.example.craps.view.in.SingleRoundInView;
import com.example.craps.view.out.ManyRoundsOutView;
import com.example.craps.view.out.SingleRoundOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/craps")
public class CrapsGameController {

    @Autowired
    ICrapsGameService crapsGameService;

    @PostMapping("/play-round")
    public ResponseEntity playRound(@Validated @RequestBody SingleRoundInView singleRoundInView) {
        SingleRoundOutView  singleRoundOutView = crapsGameService.playRound(singleRoundInView);
        return ResponseEntity.ok(singleRoundOutView);
    }


    @PostMapping("/play-n-rounds")
    public ResponseEntity playNRounds(@Validated @RequestBody ManyRoundsInView manyRoundsInView) {
        ManyRoundsOutView manyRoundsOutView = crapsGameService.playManyRounds(manyRoundsInView);
        return ResponseEntity.ok(manyRoundsOutView);

    }
}
