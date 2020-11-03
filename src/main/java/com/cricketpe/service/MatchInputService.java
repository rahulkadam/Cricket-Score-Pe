package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Player;
import com.cricketpe.dto.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
            String playerName = acceptBatsmanName(match, team);
            playerService.addPlayerToTeam(match, team, playerName);
        }
    }

    public String acceptBatsmanName(Match match, int team) {
        if (match.isAutoInput()) {
            int size = 0;
            if (team ==1) {
                if (match.getTeam1()!= null) {
                    size = match.getTeam1().getPlayerList().size();
                }
            }
            if (team == 2) {
                if (match.getTeam2()!= null) {
                    size = match.getTeam2().getPlayerList().size();
                }
            }
            String pName = "Team-" + team + "-" + size;
            System.out.println(pName);
            sleep(500);
            return pName;
        } else {
            return acceptString();
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
            String ball = acceptString(match);
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

    public String acceptString(Match match) {
        if (match.isAutoInput()) {
            String ball = pickOneValidBall();
            System.out.println(ball);
            sleep(500);
            return ball;
        }
        Scanner sc= new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }

    public String pickOneValidBall() {
        Random random = new Random();
        int number = random.nextInt(1000);
        int index = number % 9;
        List<String> ballList = getBallInput();
        return  ballList.get(index);
    }

    public List<String> getBallInput() {
        List<String> list = new ArrayList<String>();
        list.add("nb");
        list.add("wd");
        list.add("w");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        return list;
    }

    public static String acceptString() {
        Scanner sc= new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }

    public void sleep(int second) {
        try {
            Thread.sleep(second);
        } catch (Exception e) {

        }
    }
}
