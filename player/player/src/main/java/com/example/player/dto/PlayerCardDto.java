package com.example.player.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardDto {

    private List<PlaySoccerDto> list = new ArrayList<>();



    public PlayerCardDto() {
    }

    public PlayerCardDto(List<PlaySoccerDto> list) {
        this.list = list;
    }

    public List<PlaySoccerDto> getList() {
        return list;
    }

    public void setList(List<PlaySoccerDto> list) {
        this.list = list;
    }

    public boolean addPlayer(PlaySoccerDto dtoPlayer){
//        if (map.containsKey(dtoPlayer)){
//            Integer currentValue = map.get(dtoPlayer);
//            map.replace(dtoPlayer,currentValue + 1);
//        }else {
//            map.put(dtoPlayer, 1);
//        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(dtoPlayer)){
                return false;
            }
        }
        list.add(dtoPlayer);
        return true;
    }
}
