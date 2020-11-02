package com.cricketpe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseObj {
    protected int id;

    public BaseObj(int id) {
        this.id = id;
    }
}
