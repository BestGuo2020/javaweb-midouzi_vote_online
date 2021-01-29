package vote509.vo;

import vote509.entity.Contestant;

public class ContestantVo extends Contestant {
	
	private int votes;

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
