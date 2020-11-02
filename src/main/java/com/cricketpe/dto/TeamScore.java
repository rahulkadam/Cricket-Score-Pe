package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TeamScore extends BaseObj {
    private int runs;
    private  int wickets;
    private int overs;
    private int balls;
}
