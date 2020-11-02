package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamScore extends BaseObj {
    private int runs;
    private  int wickets;
    private int overs;
    private int balls;
}
