package me.yeojoy.bowlingscoreboard.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Frame> frames;

    public Player() {
        this(null);
    }

    public Player(String name) {
        if (name == null) {
            this.name = "player_" + hashCode();
        } else {
            this.name = name;
        }

        frames = new ArrayList<>(10);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeFrames() {
        frames.clear();
    }

    @Override
    public boolean equals(Object player) {
        return this.name == ((Player) player).getName() || (this.name.equals(((Player) player).getName()));
    }
}
