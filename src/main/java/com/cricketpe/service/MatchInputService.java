package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Player;
import com.cricketpe.dto.Team;

import java.util.List;
import java.util.Scanner;

public class MatchInputService {

    public PlayerService playerService;
    public LiveMatchService liveMatchService;
    public MatchOutputService matchOutputService;

    public MatchInputService() {
        playerService = new PlayerService();
        liveMatchService = new LiveMatchService();
        matchOutputService = new MatchOutputService();
    }

    /**
     * Accept batsman name and order for Batting
     * @param match
     * @param team
     */
    public void acceptBattingOrderForTeam(Match match, int team) {
        int playerCount = match.getPlayerCount();
        System.out.println("Batting Order for team " + team + " : ");
        for(int i=0; i< playerCount; i++) {
            String playerName = acceptString();
            playerService.addPlayerToTeam(match, team, playerName);
        }
    }

    /**
     * Function to Initialize batting for team, set striker, and playing batsman
     * @param match
     * @param teamNumber
     */
    public void initBattingForTeam(Match match, int teamNumber) {
        Team team = null;
        if (teamNumber == 2) {
            team = match.getTeam2();
        } else {
            team = match.getTeam1();
        }
        team.setBatting(true);
        List<Player> playerList = team.getPlayerList();
        playerList.get(0).setPlaying(true);
        playerList.get(0).setOnStrike(true);
        playerList.get(1).setPlaying(true);
    }

    /**
     * Start bowling to Batting team , ball by ball
     * @param match
     */
    public void startBowlingForTeam(Match match) {
        int maxballs = match.getOverCount() * 6;
        for(int i =0 ; i < maxballs; i++) {
            if (i % 6 == 0) {
                System.out.println("Over " + (i/6 + 1) + ":");
            }
            String ball = acceptString();
            liveMatchService.recordBall(match, ball);
            if (!liveMatchService.isBallValid(ball)) {
                i--;
            }
            if (liveMatchService.isInningOver(match) || match.getResult() != null) {
                liveMatchService.afterInningsTask(match);
                return;
            }
            if((i+1) %6 == 0 && (i+1)/6 > 0) {
                if(match.getTeam1().isBatting()) {
                    matchOutputService.showScoreCard(match, 1);
                } else {
                    matchOutputService.showScoreCard(match, 2);
                }
            }
        }
    }

    public static String acceptString() {
        Scanner sc= new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }
}
