package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Team;
import com.cricketpe.dto.TeamScore;

public class MatchService {

    public Match initMatch() {
        return initMatch(11);
    }

    /**
     * Init match
     * @param playerCount
     * @return
     */
    public Match initMatch(int playerCount) {
        Match match = new Match();
        match.setPlayerCount(playerCount);
        match.setDescription("Team 1 vs Team 2");
        match.setTeam1(new Team("Team 1"));
        match.setTeam2(new Team("Team 2"));
        match.setTeam1Score(new TeamScore());
        match.setTeam2Score(new TeamScore());
        return match;
    }

    public Match setOverCount(Match match, int over) {
        match.setOverCount(over);
        return match;
    }
}
