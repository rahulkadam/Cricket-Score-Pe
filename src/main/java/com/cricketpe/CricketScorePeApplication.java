package com.cricketpe;

import com.cricketpe.dto.*;
import com.cricketpe.service.*;

import java.util.Scanner;

public class CricketScorePeApplication {

    // tie
    // strike change
    public static void main(String args[]) {
        initializeObj();
        Match match = initializeMatchWithInput();
        processInning(match, 1);
        match.getTeam1().setBatting(false);
        processInning(match, 2);
        System.out.println("Result :" + liveMatchService.calculateResult(match));
    }

    public static void processInning(Match match, int teamNumber) {
        matchInputService.acceptBattingOrderForTeam(match, teamNumber);
        matchInputService.initBattingForTeam(match, teamNumber);
        matchInputService.startBowlingForTeam(match);
        matchOutputService.showScoreCard(match, teamNumber);
    }

    public static Match initializeMatchWithInput() {
        int playerCount = acceptInt("No. of players for each team");
        int overs = acceptInt("No. of overs");
        int autoInput = acceptInt("Do you want to take input in Auto Mode : Press (1)");
        Match match = matchService.initMatch(playerCount);
        if (autoInput == 1) {
            match.setAutoInput(true);
        }
        matchService.setOverCount(match, overs);
        return match;
    }

    public static int acceptInt(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message + " : ");
        int number = sc.nextInt();
        return number;
    }

    public static MatchService matchService;
    public static PlayerService playerService;
    public static LiveMatchService liveMatchService;
    public static MatchOutputService matchOutputService;
    public static MatchInputService matchInputService;

    public static void initializeObj() {
        matchService = new MatchService();
        playerService = new PlayerService();
        liveMatchService = new LiveMatchService();
        matchInputService = new MatchInputService();
        matchOutputService = new MatchOutputService();
    }

}
