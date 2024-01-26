package storage;

import application.model.Player;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Player> bots = new ArrayList<>();

    public static ArrayList<Player> getBots() {return new ArrayList<>(bots);}
    public static void addBot(Player bot) {
        bots.add(bot);
    }
 }
