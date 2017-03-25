package org.hackingweb.gw2compagnon.model.account;

import java.util.List;

/**
 * Created by cyril on 15/08/2015.
 */
public class Account {

    private String id;

    private String name;

    private int world;

    private List<String> listGuilds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorld() {
        return world;
    }

    public void setWorld(int world) {
        this.world = world;
    }

    public List<String> getListGuilds() {
        return listGuilds;
    }

    public void setListGuilds(List<String> listGuilds) {
        this.listGuilds = listGuilds;
    }
}
