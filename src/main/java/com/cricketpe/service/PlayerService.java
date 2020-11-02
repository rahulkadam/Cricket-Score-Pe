package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Team;

public class PlayerService {

    /**
     * Add Player To team
     * @param match
     * @param teamNumber
     * @param name
     * @return
     */
    public Match addPlayerToTeam(Match match, int teamNumber , String name) {
        if (teamNumber == 1) {
            return addPlayerToTeam1(match, name);
        }
        return addPlayerToTeam2(match, name);
    }

    /**
     * add Player to Team
     * @param match
     * @param name
     * @return
     */
    public Match addPlayerToTeam1(Match match, String name) {
        Team team = match.getTeam1();
        team.addPlayer(name);
        match.setTeam1(team);
        return match;
    }

    public Match addPlayerToTeam2(Match match, String name) {
        Team team = match.getTeam2();
        team.addPlayer(name);
        match.setTeam2(team);
        return match;
    }
}
