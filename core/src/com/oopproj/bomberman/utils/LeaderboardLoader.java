package com.oopproj.bomberman.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LeaderboardLoader {
    public class Score implements Comparable<Score> {
        public String name;
        public long score;

        public Score(String name, long score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Score o) {
            if (this.score < o.score) {
                return 1;
            } else if (this.score > o.score) {
                return -1;
            } else {
                return this.name.compareTo(o.name);
            }
        }

        @Override
        public String toString() {
            return this.name + " " + this.score;
        }
    }

    private TreeSet<Score> leaderboard;
    private static LeaderboardLoader instance;

    private LeaderboardLoader() {
        leaderboard = new TreeSet<>();
    }

    public static LeaderboardLoader getInstance() {
        if (instance == null) {
            instance = new LeaderboardLoader();
        }
        return instance;
    }

    public void readLeaderboard() {
        try {
            FileReader fr = new FileReader("leaderboard");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\n", "");
                String[] parts = line.split(" ");
                leaderboard.add(new Score(parts[0], Long.parseLong(parts[1])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLeaderboard() {
        try {
            FileWriter file = new FileWriter("leaderboard");
            BufferedWriter bw = new BufferedWriter(file);
            for (Score s : leaderboard) {
                bw.write(s.name.replace(" ", "") + " " + s.score + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("cannot create settings file");
        }
    }

    public List<Score> get10HighestScore() {
        ArrayList<Score> result = new ArrayList<>(leaderboard);
        try {
            return result.subList(0, 10);
        } catch (IndexOutOfBoundsException e) {
            return result;
        }
    }

    public void addNewRecord(String name, long score) {
        leaderboard.add(new Score(name, score));
    }
}
