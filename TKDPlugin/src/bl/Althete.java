package bl;

public class Althete implements Comparable<Althete>{
    private String name;
    private int rank;
    private String team;
    private String levelName;
    

    public int compareTo(Althete o) {
        return Integer.compare(rank, o.rank);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getTeam() {
        return team;
    }


    public void setTeam(String team) {
        this.team = team;
    }


    public String getLevelName() {
        return levelName;
    }


    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }


    public int getRank() {
        return rank;
    }


    public void setRank(int rank) {
        this.rank = rank;
    }
}
