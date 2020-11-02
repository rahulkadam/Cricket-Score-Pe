package com.cricketpe.service;

import com.cricketpe.dto.Match;
import com.cricketpe.dto.Team;

public class PlayerService {

    public Match addPlayerToTeam1(Match match, String name) {
        Team team = match.getTeam1();
        team.addPlayer(name);
        match.setTeam1(team);
        return match;
    }

    public Match addPlayerToTeam2(Match match, String name) {
        Team team = match.getTeam2();
        team.addPlayer(name);
        match.setTeam1(team);
        return match;
    }
}
