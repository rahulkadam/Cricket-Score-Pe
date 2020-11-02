package com.cricketpe;

import com.cricketpe.dto.*;
import com.cricketpe.service.LiveMatchService;
import com.cricketpe.service.MatchService;
import com.cricketpe.service.PlayerService;

import java.util.List;
import java.util.Scanner;

public class CricketScorePeApplication {

    public static MatchService matchService;
    public static PlayerService playerService;
    public static LiveMatchService liveMatchService;

    public static void main(String args[]) {
        initializeObj();
        Match match = initializeMatchWithInput();
        acceptBattingOrderForTeam(match, 1);
        initBattingForTeam(match, 1);
        startBowlingForTeam(match);
        showScoreCard(match, 1);
        System.out.println("Match Player Count:" + match);
        acceptBattingOrderForTeam(match, 2);
        initBattingForTeam(match, 2);
        startBowlingForTeam(match);
        System.out.println("Match Player Count:" + match);
    }

    public static void initializeObj() {
        matchService = new MatchService();
        playerService = new PlayerService();
        liveMatchService = new LiveMatchService();
    }

    public static Match initializeMatchWithInput() {
        int playerCount = acceptInt("No. of players for each team");
        int overs = acceptInt("No. of overs");
        Match match = matchService.initMatch(playerCount);
        matchService.setOverCount(match, overs);
        return match;
    }

    public static void acceptBattingOrderForTeam(Match match, int team) {
        int playerCount = match.getPlayerCount();
        System.out.println("Batting Order for team " + team + " : ");
        for(int i=0; i< playerCount; i++) {
            String playerName = acceptString();
            playerService.addPlayerToTeam(match, team, playerName);
        }
    }

    public static void initBattingForTeam(Match match, int teamNumber) {
        Team team = match.getTeam1();
        if (teamNumber == 2) {
            team = match.getTeam2();
        }
        team.setBatting(true);
        List<Player> playerList = team.getPlayerList();
        playerList.get(0).setPlaying(true);
        playerList.get(0).setOnStrike(true);
        playerList.get(1).setPlaying(true);
    }

    public static void startBowlingForTeam(Match match) {
        int maxballs = match.getOverCount() * 6;
        for(int i =0 ; i < maxballs; i++) {
            if (i % 6 == 0) {
                System.out.println("Over " + (i/6 + 1) + ":");
            }
            String ball = acceptString();
            liveMatchService.addBall(match, ball);
            if (!liveMatchService.isBallValid(ball)) {
                i--;
            }
            if (liveMatchService.isInningOver(match)) {
                return;
            }
            if((i+1) %6 == 0 && (i+1)/6 > 0) {
                if(match.getTeam1().isBatting()) {
                    showScoreCard(match, 1);
                } else {
                    showScoreCard(match, 2);
                }
            }
        }
    }

    public static void showScoreCard(Match match , int teamNumber) {
        Team team = match.getTeam1();
        TeamScore teamScore = match.getTeam1Score();
        if (teamNumber == 2) {
            team = match.getTeam2();
            teamScore = match.getTeam2Score();
        }

        List<Player> players = team.getPlayerList();
        System.out.println("\t");
        System.out.println("ScoreCard for Team "+ teamNumber + ":");
        System.out.println("PlayerName  \t Score \t 4s \t6s \t Balls");
        players.forEach(player -> {
            PlayerScore playerScore = player.getPlayerScore();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(player.getName());
            if (playerScore.isPlaying()) {
                stringBuilder.append('*');
            }
            stringBuilder.append("\t\t\t\t");
            stringBuilder.append(playerScore.getScore());
            stringBuilder.append("\t\t");
            stringBuilder.append(playerScore.getFours());
            stringBuilder.append("\t\t");
            stringBuilder.append(playerScore.getSixes());
            stringBuilder.append("\t\t");
            stringBuilder.append(playerScore.getBalls());
            stringBuilder.append("\t\t");
            System.out.println(stringBuilder);
        });

        System.out.println("Total : " + teamScore.getRuns() + "/" + teamScore.getWickets());
        System.out.println("Overs : " + teamScore.getOvers());
    }

    public static int acceptInt(String message){
        Scanner sc= new Scanner(System.in);
        System.out.println(message + " : ");
        int number = sc.nextInt();
        return number;
    }

    public static String acceptString() {
        Scanner sc= new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }

    public static String acceptString(String message){
        Scanner sc= new Scanner(System.in);
        System.out.println(message + " : ");
        String line = sc.nextLine();
        return line;
    }
}
