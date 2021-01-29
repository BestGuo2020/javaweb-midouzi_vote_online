package vote509.vo;

import java.util.Date;

/**
 * ªÓ∂Ø¿‡
 * @author He Guo
 *
 */
public class ActivityVo {
	
	private int aid;
	private int uid;
	private String name; 
	private String username;
	private String introduce;
	private int status;
	private int countVotes;
	private int contestantCount;
	private Date startTime, stopTime;
	private String image;
	private int times;
	private int looks;
	
	
	public int getLooks() {
		return looks;
	}
	public void setLooks(int looks) {
		this.looks = looks;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCountVotes() {
		return countVotes;
	}
	public void setCountVotes(int countVotes) {
		this.countVotes = countVotes;
	}
	public int getContestantCount() {
		return contestantCount;
	}
	public void setContestantCount(int contestantCount) {
		this.contestantCount = contestantCount;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "ActivityVo [aid=" + aid + ", uid=" + uid + ", name=" + name + ", username=" + username + ", introduce="
				+ introduce + ", status=" + status + ", countVotes=" + countVotes + ", contestantCount="
				+ contestantCount + ", startTime=" + startTime + ", stopTime=" + stopTime + ", image=" + image
				+ ", times=" + times + "]";
	}
	
	
	
}
