package vote509.entity;

public class Participate extends ParticipateKey {
    private Integer votes;

    private Integer looks;

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getLooks() {
        return looks;
    }

    public void setLooks(Integer looks) {
        this.looks = looks;
    }
}