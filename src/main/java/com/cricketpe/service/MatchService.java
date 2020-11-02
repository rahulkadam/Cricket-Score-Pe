package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Team;
import com.cricketpe.dto.TeamScore;

public class MatchService {

    public Match initMatch() {
        return initMatch(11);
    }

    public Match initMatch(int playerCount) {
        Match match = new Match();
        match.setPlayerCount(playerCount);
        match.setTeam1(new Team());
        match.setTeam2(new Team());
        match.setTeam1Score(new TeamScore());
        match.setTeam2Score(new TeamScore());
        return match;
    }

    public Match setOverCount(Match match, int over) {
        match.setOverCount(over);
        return match;
    }
}
