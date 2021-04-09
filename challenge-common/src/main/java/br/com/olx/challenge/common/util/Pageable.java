package br.com.olx.challenge.common.util;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Pageable<T> {

    private Integer page;
    private Integer size;
    private List<T> fullList;

    public List<T> getPageOfFullList(){
        try{
            return fullList.subList(getFirstItemPage(page, size), getLastItemPage(page, size));
        }catch(IndexOutOfBoundsException e){
            return new ArrayList<>();
        }
    }

    private Integer getFirstItemPage(Integer page, Integer size){
        return page * size;
    }

    private Integer getLastItemPage(Integer page, Integer size){
        return (page * size) + size;
    }

}
