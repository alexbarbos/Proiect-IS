package model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
	
	private List<Match> ticket;
	private float result;
	private float bet = 1.0f;
	private float gainValue;
	private String code;
	private SecureRandom random = new SecureRandom();
	private boolean ticketStatus;
	
	public boolean getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String generateCode(int len) {	
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( random.nextInt(AB.length()) ) );
		return code = sb.toString();
	 }
	 
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public float getGainValue() {
		return gainValue;
	}

	public void setGainValue(float gainValue) {
		this.gainValue = gainValue;
	}

	public float getBet() {
		return bet;
	}

	public void setBet(float bet) {
		this.bet = bet;
	}

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}

	public Ticket() {
		ticket = new ArrayList<Match>();
	}
	
	public List<Match> getTicket() {
		return ticket;
	}

	public void setTicket(List<Match> ticket) {
		this.ticket = ticket;
	}
	
	public void calculateFinalShare() {
		
		float finalShare = 1.0f;
		for (Match mat: ticket)
			finalShare *= mat.getShareValue();
		result = Float.parseFloat(String.format("%.2f", finalShare));
	}
	
	public void calculateGain() {
		gainValue = Float.parseFloat(String.format("%.2f", bet * result ));	
	}
	
}
