package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.TeamScore;

import java.util.ArrayList;
import java.util.List;

public class LiveMatchService {

    public Match recordBall(Match match, String ball) {
        match = addBall(match, ball);
        match = addRuns(match, ball);
        match = addWicket(match, ball);
        match = addChangeStrike(match, ball);
        match = afterInningsTask(match, ball);
        match = afterMatchTask(match, ball);

        return match;
    }

    public Match afterInningsTask(Match match, String ball) {
        return match;
    }

    public Match afterMatchTask(Match match, String ball) {
        return match;
    }

    public Match addChangeStrike(Match match, String ball) {
        return match;
    }

    public Match addWicket(Match match, String ball) {
        return match;
    }

    public Match addRuns(Match match, String ball) {
        return match;
    }


    /**
     * add Ball to Team score
     * @param match
     * @param ball
     * @return
     */
    public Match addBall(Match match, String ball) {
        if (match.getTeam1().isBatting()) {
            TeamScore teamScore = match.getTeam1Score();
            if (isBallValid(ball)) {
                int balls = teamScore.getBalls();
                if (balls < 5) {
                    teamScore.setBalls(balls+1);
                } else {
                    teamScore.setOvers(teamScore.getOvers() + 1);
                    teamScore.setBalls(0);
                }
            } else {
                teamScore.setRuns(teamScore.getRuns()+1);
            }
        }
        return match;
    }

    public boolean isBallValid(String ball) {
        return !getInvalidBallType().contains(ball);
    }

    public List<String> getInvalidBallType() {
        List<String> list = new ArrayList<String>();
        list.add("NB");
        list.add("nb");
        list.add("WD");
        list.add("wd");
        return list;
    }
}
