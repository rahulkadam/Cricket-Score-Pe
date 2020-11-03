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

    private int bowling_Overs;
    private int bowling_Balls;
    private int bowling_Run_Conceded;
    private int bowling_extraRuns;
    private int bowling_wickets;

    public boolean isOverBreak() {
        if (overs > 0 && balls ==0) {
            return true;
        }

        return false;
    }
}
