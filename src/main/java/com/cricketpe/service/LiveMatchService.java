package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Player;
import com.cricketpe.dto.Team;
import com.cricketpe.dto.TeamScore;

import java.util.ArrayList;
import java.util.List;

public class LiveMatchService {

    public Match recordBall(Match match, String ball) {
        match = addBall(match, ball);
        match = addRuns(match, ball);
        match = addWicket(match, ball);
        match = addChangeStrike(match, ball);
        // match = afterInningsTask(match, ball);
        match = afterMatchTask(match, ball);
        return match;
    }

    public Match afterInningsTask(Match match, String ball) {
        if (isInningOver(match)) {
            Team team = match.getBattingTeam();
            team.setBatting(false);
        }
        return match;
    }

    public Match afterMatchTask(Match match, String ball) {
        if (isMatchOver(match)) {
            TeamScore team1Score = match.getTeam1Score();
            TeamScore team2Score = match.getTeam2Score();

            int runsDiff = team1Score.getRuns() - team2Score.getRuns();
            int wicketDiff = match.getPlayerCount() - team2Score.getWickets() - 1;
            if (runsDiff > 0) {
                match.setResult("Team 1 won By " + runsDiff + " Runs");
            } else {
                match.setResult("Team 2 won By " + wicketDiff + " Wickets");
            }

        }
        return match;
    }

    public Match addChangeStrike(Match match, String ball) {

        if (shouldChangeStrike(match, ball)) {
            Team team = match.getBattingTeam();
            List<Player> batsman = team.getCurrentBatsman();
            Player first = batsman.get(0);
            Player second = batsman.get(1);
            if (first.isOnStrike()) {
                second.setOnStrike(true);
                first.setOnStrike(false);
            } else {
                first.setOnStrike(true);
                second.setOnStrike(false);
            }
        }
        return match;
    }

    public Match addWicket(Match match, String ball) {
        if (isWicket(ball)) {
            Team team = match.getBattingTeam();
            List<Player> batsman = team.getCurrentBatsman();
            Player first = batsman.get(0);
            Player second = batsman.get(1);
            if (first.isOnStrike()) {
                first.addOut();
            } else {
                second.addOut();
            }
            team.addNextPlayerForBatting();
            TeamScore teamScore = match.getBattingTeamScore();
            teamScore.setWickets(teamScore.getWickets() + 1);
        }
        return match;
    }

    public Match addRuns(Match match, String ball) {

        if (!isWicket(ball) && isBallValid(ball)) {
            int runs = getRuns(ball);
            Team team = match.getBattingTeam();
            List<Player> batsman = team.getCurrentBatsman();
            Player first = batsman.get(0);
            Player second = batsman.get(1);
            if (first.isOnStrike()) {
                first.addScore(runs);
            } else {
                second.addScore(runs);
            }

            TeamScore teamScore = match.getBattingTeamScore();
            teamScore.setRuns(teamScore.getRuns() + runs);
        }
        return match;
    }

    /**
     * add Ball to Team score
     *
     * @param match
     * @param ball
     * @return
     */
    public Match addBall(Match match, String ball) {
        TeamScore teamScore = match.getBattingTeamScore();
        if (isBallValid(ball)) {
            int balls = teamScore.getBalls();
            if (balls < 5) {
                teamScore.setBalls(balls + 1);
            } else {
                teamScore.setOvers(teamScore.getOvers() + 1);
                teamScore.setBalls(0);
            }
        } else {
            teamScore.setRuns(teamScore.getRuns() + 1);
        }
        return match;
    }

    public boolean isInningOver(Match match) {
        int playerCount = match.getPlayerCount();
        int overCount = match.getPlayerCount();
        TeamScore teamScore = match.getBattingTeamScore();
        if (overCount == teamScore.getOvers()) {
            return true;
        }

        if (playerCount == teamScore.getWickets() + 1) {
            return true;
        }

        return false;
    }

    public boolean isMatchOver(Match match) {
        int playerCount = match.getPlayerCount();
        int overCount = match.getPlayerCount();
        TeamScore team1Score = match.getTeam1Score();
        if (overCount != team1Score.getOvers() && playerCount != team1Score.getWickets() + 1) {
            return false;
        }

        TeamScore team2Score = match.getTeam2Score();
        if (team1Score.getRuns() < team2Score.getRuns()) {
            return true;
        }
        if (overCount != team2Score.getOvers() && playerCount != team2Score.getWickets() + 1) {
            return false;
        }
        return true;
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

    public int getRuns(String ball) {
        try {
            return Integer.parseInt(ball);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isWicket(String ball) {
        if ("w".equalsIgnoreCase(ball)) {
            return true;
        }
        return false;
    }

    public boolean shouldChangeStrike(Match match, String ball) {
        TeamScore teamScore = match.getBattingTeamScore();
        if (teamScore.isOverBreak()) {
            return true;
        }

        int i = getRuns(ball);
        if (i < 4 && i % 2 == 1) {
            return true;
        }
        return false;
    }

    public String calculateResult(Match match) {
        TeamScore team1Score = match.getTeam1Score();
        TeamScore team2Score = match.getTeam2Score();

        int runsDiff = team1Score.getRuns() - team2Score.getRuns();
        int wicketDiff = match.getPlayerCount() - team2Score.getWickets() - 1;
        if (runsDiff > 0) {
            return "Team 1 won By " + runsDiff + " Runs";
        } else {
            return "Team 2 won By " + wicketDiff + " Wickets";
        }
    }
}
