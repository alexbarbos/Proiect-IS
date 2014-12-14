package model;

import java.util.HashMap;

public class Match {

    private int matchid;
    private String hostTeam;
    private String awayTeam;
    private float share1, shareX, share2, share1X, shareX2, share12;
    private HashMap<String,Float> asoc; 
    
    public Match() {
    	asoc = new HashMap<String,Float>();
    }
    
    public HashMap<String, Float> getAsoc() {
		return asoc;
	}
	public void setAsoc(HashMap<String, Float> asoc) {
		this.asoc = asoc;
	}
	public int getMatchid() {
        return matchid;
    }
    public void setMatchid(int matchid) {
        this.matchid = matchid;
    }
    public String getHostTeam() {
        return hostTeam;
    }
    public void setHostTeam(String hostTeam) {
        this.hostTeam = hostTeam;
    }
    public String getAwayTeam() {
        return awayTeam;
    }
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
    public float getShare1() {
    	return share1;
    }
    public void setShare1(float share1) {
    	this.share1 = share1;
    }
    public float getShareX() {
    	return shareX;
    }
    public void setShareX(float shareX) {
    	this.shareX = shareX;
    }
    public float getShare2() {
    	return share2;
    }
    public void setShare2(float share2) {
    	this.share2 = share2;
    }
    public float getShare1X() {
    	return share1X;
    }
    public void setShare1X(float share1X) {
    	this.share1X = share1X;
    }
    public float getShareX2() {
    	return shareX2;
    }
    public void setShareX2(float shareX2) {
    	this.shareX2 = shareX2;
    }
    public float getShare12() {
    	return share12;
    }
    public void setShare12(float share12) {
    	this.share12 = share12;
    }
    @Override
    public String toString() {
        return "Match [matchid=" + matchid + ", hostTeam=" + hostTeam + ", awayTeam=" + awayTeam + ", share1=" + share1 
        		+ ", shareX=" + shareX + ", share2=" + share2 + ", share1X=" + share1X + ", shareX2=" + shareX2 
        		+ ", share12=" + share12 + "]";
    }    
}
