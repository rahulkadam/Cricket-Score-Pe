package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BaseObj {
    protected int id;

    public BaseObj(int id) {
        this.id = id;
    }
}
