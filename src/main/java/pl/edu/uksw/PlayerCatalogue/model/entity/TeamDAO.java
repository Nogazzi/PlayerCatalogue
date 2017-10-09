package pl.edu.uksw.PlayerCatalogue.model.entity;

import java.io.Serializable;

public class TeamDAO implements Serializable{

    private String name;

    public TeamDAO(){

    }

    public TeamDAO(final String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
