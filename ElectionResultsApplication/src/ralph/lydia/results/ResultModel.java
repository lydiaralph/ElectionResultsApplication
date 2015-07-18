package ralph.lydia.results;

import ralph.lydia.processor.FileValidatorException;

public class ResultModel {

	private PartyCode partyCode;
	
	private int votes;
	private float share;
	
	public PartyCode getPartyCode(){
		return this.partyCode;
	}
	
	public int getVotes(){
		return this.votes;
	}
	
	public float getShare(){
		return this.share;
	}
	
	public void setPartyCode(String partyCode) throws IllegalArgumentException{
		this.partyCode = PartyCode.valueOf(partyCode);
	}
	
	public void setPartyCode(PartyCode partyCode) {
		this.partyCode = partyCode;
	}
	
	public void setVotes(int votes){
		this.votes = votes;
	}
	
	public void setShare(float share){
		this.share = share;
	}
	
	public void addVotes(int votes){
		this.votes += votes;
	}
	
	public void addShare(float share){
		this.share += share;
	}
	
	public void printAllValues(){
		System.out.println("PartyCode: " + this.getPartyCode() + "\n");
		System.out.println("Votes: " + this.getVotes() + "\n");
		System.out.println("Share: " + this.getShare() + "\n");
	}
	
}
