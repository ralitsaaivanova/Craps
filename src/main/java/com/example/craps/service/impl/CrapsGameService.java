package com.example.craps.service.impl;
import com.example.craps.entity.CrapsGameEntity;
import com.example.craps.entity.SubsequentRollsEntity;
import com.example.craps.entity.enums.OutcomeEnum;
import com.example.craps.repository.CrapsGameRepository;
import com.example.craps.repository.SubsequentRollsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.craps.service.ICrapsGameService;
import com.example.craps.view.in.ManyRoundsInView;
import com.example.craps.view.in.SingleRoundInView;
import com.example.craps.view.out.ManyRoundsOutView;
import com.example.craps.view.out.SingleRoundOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class CrapsGameService implements ICrapsGameService {
    private final Random random = new Random();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);  // Thread pool
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CrapsGameRepository crapsGameRepository;



    @Override
    public SingleRoundOutView playRound(SingleRoundInView singleRoundInView) {

        String logId = UUID.randomUUID().toString();
        log.info("{} : One round starts", logId);
        log.debug("{} : params: Body: {}", logId,singleRoundInView);
        try{
            CrapsGameEntity crapsGameEntity = modelMapper.map(singleRoundInView,new TypeToken<CrapsGameEntity>(){}.getType());

            int diceResult = rollDice();
            crapsGameEntity.setInitialRoll(diceResult);

            if(diceResult==7 || diceResult ==11){
                //case when the player wins
                crapsGameEntity.setOutcome(OutcomeEnum.WIN.toString());
                crapsGameEntity.setTotalWin(singleRoundInView.getStake()
                        .add(singleRoundInView.getStake()));
                crapsGameEntity.setFinalRoll(diceResult); //repeat

            }else if(diceResult==2 || diceResult==3 || diceResult==12){
                //case when the player loses
                crapsGameEntity.setOutcome(OutcomeEnum.LOSE.toString());
                crapsGameEntity.setTotalWin(BigDecimal.valueOf(0));
                crapsGameEntity.setFinalRoll(diceResult); //repeat
            }else{
                //throw dices again
                crapsGameEntity.setPlayerPoint(diceResult);
                int playerPoint = diceResult;
                diceResult=rollDice();
                List<SubsequentRollsEntity> subsequentRollsEntityList = new ArrayList<>();
                while(true){
                    if(diceResult==7){
                        //the player lost
                        crapsGameEntity.setOutcome(OutcomeEnum.LOSE.toString());
                        crapsGameEntity.setTotalWin(BigDecimal.valueOf(0));
                        break;
                    }
                    if(diceResult==playerPoint){
                        //the player won
                        crapsGameEntity.setOutcome((OutcomeEnum.WIN.toString()));
                        crapsGameEntity.setTotalWin(singleRoundInView.getStake()
                                .add(singleRoundInView.getStake()));
                        break;
                    }
                    SubsequentRollsEntity subsequentRollsEntity = new SubsequentRollsEntity();
                    subsequentRollsEntity.setCrapsGame(crapsGameEntity);
                    subsequentRollsEntity.setRoll(diceResult);

                    subsequentRollsEntityList.add(subsequentRollsEntity);

                    diceResult=rollDice();
                }
                crapsGameEntity.setSubsequentRolls(subsequentRollsEntityList);
                crapsGameEntity.setFinalRoll(diceResult);
            }

            crapsGameEntity = crapsGameRepository.save(crapsGameEntity);
            SingleRoundOutView res = modelMapper.map(crapsGameEntity,new TypeToken<SingleRoundOutView>(){}.getType());

            List<Integer>subsequentRolls=new ArrayList<>();
            if (crapsGameEntity.getSubsequentRolls() != null && !crapsGameEntity.getSubsequentRolls().isEmpty()) {
                // Iterate through the subsequent rolls and add them to the response
                crapsGameEntity.getSubsequentRolls().forEach(e -> subsequentRolls.add(e.getRoll()));
                res.setSubsequentRollsList(subsequentRolls);
            } else {
                // Handle the case when there are no subsequent rolls (optional)
                log.info("{}: No subsequent rolls for this round", logId);
                res.setSubsequentRollsList(subsequentRolls); // This will be an empty list in the response
            }

            return res;

        }catch (Exception e){
            log.error("{}: One round error", logId,e);
            throw e;
        }finally {
            log.info("{}: One round finished", logId);
        }
    }

    @Override
    public ManyRoundsOutView playManyRounds(ManyRoundsInView manyRoundsInView) {

        Long numberOfRounds = manyRoundsInView.getRounds();
        BigDecimal stake = manyRoundsInView.getStake();
        String gameType = manyRoundsInView.getGameType();

        List<Future<SingleRoundOutView>> futures = new ArrayList<>();
        BigDecimal allStakes = BigDecimal.ZERO;
        BigDecimal allWins = BigDecimal.ZERO;

        // Submit tasks for playing rounds in parallel
        for (int i = 0; i < numberOfRounds; i++) {
            SingleRoundInView singleRoundInView = new SingleRoundInView();
            singleRoundInView.setGameType(gameType);
            singleRoundInView.setStake(stake);
            allStakes = allStakes.add(stake);  // Track total stakes
            Future<SingleRoundOutView> future = executorService.submit(() ->playRound(singleRoundInView));
            futures.add(future);
        }

        // Collect results from futures
        for (Future<SingleRoundOutView> future : futures) {
            try {
                SingleRoundOutView roundResult = future.get();
                allWins = allWins.add(roundResult.getTotalWin());  // Aggregate wins
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();  // Handle exception
            }
        }

        // Create response object with the calculated statistics
        ManyRoundsOutView output = new ManyRoundsOutView();
        output.setAllStakes(allStakes);
        output.setAllWins(allWins);
        output.setWinsDividedByStakes(allWins.divide(allStakes,2, RoundingMode.HALF_UP));

        return output;
    }

    //roll two dice
    private int rollDice() {
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }

}
