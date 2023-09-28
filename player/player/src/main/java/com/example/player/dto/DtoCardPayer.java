package com.example.player.dto;

import java.util.HashMap;
import java.util.Map;

public class DtoCardPayer {
    private Map<PlaySoccerDto,Integer> map = new HashMap<>();

    public DtoCardPayer() {
    }

    public DtoCardPayer(Map<PlaySoccerDto, Integer> map) {
        this.map = map;
    }

    public Map<PlaySoccerDto, Integer> getMap() {
        return map;
    }

    public void setMap(Map<PlaySoccerDto, Integer> map) {
        this.map = map;
    }
    public void addPlayer(PlaySoccerDto dtoPlayer){
        if (map.containsKey(dtoPlayer)){
            Integer currentValue = map.get(dtoPlayer);
            map.replace(dtoPlayer,currentValue + 1);
        }else {
            map.put(dtoPlayer, 1);
        }
    }
}
