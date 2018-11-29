package monash.sydney.edu.au.typeracing;

public class LItem {

    Integer rank;
    Long points;
    String userName;
    Integer level;

    public LItem(Integer rank, Long points, String userName, Integer level) {
        this.rank = rank;
        this.points = points;
        this.userName = userName;
        this.level = level;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
