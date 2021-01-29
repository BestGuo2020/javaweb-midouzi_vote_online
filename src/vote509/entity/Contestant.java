package vote509.entity;

public class Contestant {
    private Integer id;

    private Integer contestantid;

    private String name;

    private String image;

    private String introduce;
    
    private Integer giveup;

    public Integer getGiveup() {
		return giveup;
	}

	public void setGiveup(Integer giveup) {
		this.giveup = giveup;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContestantid() {
        return contestantid;
    }

    public void setContestantid(Integer contestantid) {
        this.contestantid = contestantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}