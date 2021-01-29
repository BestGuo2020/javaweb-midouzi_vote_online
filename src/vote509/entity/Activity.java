package vote509.entity;

import java.util.Date;

public class Activity {

	private Integer id;

    private String name;

    private Integer status;

    private Integer countvotes;

    private Integer contestantcount;

    private Date starttime;

    private Date stoptime;

    private Integer userid;

    private Integer times;

    private Integer looks;

    private String introduece;
    
    private String image;
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCountvotes() {
        return countvotes;
    }

    public void setCountvotes(Integer countvotes) {
        this.countvotes = countvotes;
    }

    public Integer getContestantcount() {
        return contestantcount;
    }

    public void setContestantcount(Integer contestantcount) {
        this.contestantcount = contestantcount;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getLooks() {
        return looks;
    }

    public void setLooks(Integer looks) {
        this.looks = looks;
    }

    public String getIntroduece() {
        return introduece;
    }

    public void setIntroduece(String introduece) {
        this.introduece = introduece == null ? null : introduece.trim();
    }
}