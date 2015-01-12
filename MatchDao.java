package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Match;
import model.Ticket;
import util.DbUtil;

public class MatchDao {

    private Connection connection;
 
	HashMap<String,Float> asoc = new HashMap<>();
 
    public MatchDao() {
        connection = DbUtil.getConnection();
    }
    
    public void addTicketMatches(Ticket ticket) throws IOException {
    	
    	try {
    		for (int i = 0; i < ticket.getTicket().size(); i++)
    		{
    			PreparedStatement preparedStatement = connection
    					.prepareStatement("insert into tickets (ticketCode, matchid, shareType, shareValue, status) "
    							+ "values (?, ?, ?, ?, ?)");
    			// Parameters start with 1
    			preparedStatement.setString(1, ticket.getCode() );
    			preparedStatement.setInt(2, ticket.getTicket().get(i).getMatchid() );
    			preparedStatement.setString(3, ticket.getTicket().get(i).getShareType() );
    			preparedStatement.setFloat(4, ticket.getTicket().get(i).getShareValue() );
    			preparedStatement.setInt(5, ticket.getTicket().get(i).getStatus() );
    			preparedStatement.executeUpdate();
    		}
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    }
    
    public void deleteAllTicketMatches() {
    	
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from tickets");
            // Parameters start with 1
            preparedStatement.executeUpdate();      
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public void deleteTicketMatchesBy() {
    	
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from tickets where status=?");
            // Parameters start with 1
            preparedStatement.setInt(1, 0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public ArrayList<Ticket> getTicketCodes() {
    	
        ArrayList<Ticket> ticketCodes = new ArrayList<Ticket>(); 
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tickets");
            while (rs.next()) {
                Ticket ticket = new Ticket();
                
                ticket.setCode(rs.getString("ticketCode") );
                
                ticketCodes.add(ticket);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketCodes;
    }

    public void addScores(Match match) throws IOException {
    	
    	String score = generateScore();
    	try {
    			PreparedStatement preparedStatement = connection
    					.prepareStatement("update matches set finalScore=? where matchid=?");
    			// Parameters start with 1
    			preparedStatement.setString(1, score );
    			preparedStatement.setInt(2, match.getMatchid() );
    			preparedStatement.executeUpdate();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	match.setFinalScore(score);
    }
    
    public String generateScore() {
    	
    	int first = (int)(Math.random() * (7 + 1));
    	int second = (int)(Math.random() * (7 + 1));
    	String finalScore = "" + first + "-" + second;
    	return finalScore;
    }
    
    public ArrayList<Match> getTicketById(String code) {
    	
    	ArrayList<Match> matchList = new ArrayList<Match>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from tickets where ticketCode=?");
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {  
            	Match match1 = new Match();
            	
            	match1.setMatchid(rs.getInt("matchid"));
            	match1.setShareType(rs.getString("shareType"));
            	match1.setShareValue(rs.getFloat("shareValue"));
            	match1.setStatus(rs.getInt("status"));
            	
            	matchList.add(match1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchList;
    }
    
    public ArrayList<Match> getTicketMatches() {
    	
        ArrayList<Match> ticketMatches = new ArrayList<Match>(); 
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tickets");
            while (rs.next()) {
                Match match = new Match();
                
                match.setMatchid(rs.getInt("matchid") );
                match.setShareType(rs.getString("shareType") );
                match.setShareValue(rs.getFloat("shareValue") );
                match.setStatus(rs.getInt("status"));
                
                ticketMatches.add(match);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketMatches;
    } 
    
    public void addMatch(Match match) throws IOException {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into matches (matchid, hostTeam, awayTeam, share1, shareX, share2, "
                    		+ "share1X, shareX2, share12) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, match.getMatchid());
            preparedStatement.setString(2, match.getHostTeam());
            preparedStatement.setString(3, match.getAwayTeam());
            preparedStatement.setFloat(4, match.getShare1());
            preparedStatement.setFloat(5, match.getShareX());
            preparedStatement.setFloat(6, match.getShare2());
            preparedStatement.setFloat(7, match.getShare1X());
            preparedStatement.setFloat(8, match.getShareX2());
            preparedStatement.setFloat(9, match.getShare12());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteMatch(int matchId) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from matches where matchid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, matchId);
            preparedStatement.executeUpdate();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMatch(Match match) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update matches set hostTeam=?, awayTeam=?, share1=?, shareX=?, share2=?, "
                    		+ "share1X=?, shareX2=?, share12=? where matchid=?");
            // Parameters start with 1
            preparedStatement.setString(1, match.getHostTeam() );
            preparedStatement.setString(2, match.getAwayTeam() );
            preparedStatement.setFloat(3, match.getShare1() );
            preparedStatement.setFloat(4, match.getShareX() );
            preparedStatement.setFloat(5, match.getShare2() );
            preparedStatement.setFloat(6, match.getShare1X() );
            preparedStatement.setFloat(7, match.getShareX2() );
            preparedStatement.setFloat(8, match.getShare12() );
            preparedStatement.setInt(9, match.getMatchid() );
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Match> getAllMatches() {
    	
        List<Match> matches = new ArrayList<Match>(); 
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from matches");
            while (rs.next()) {
                Match match = new Match();
                match.setMatchid(rs.getInt("matchid"));
                match.setHostTeam(rs.getString("hostTeam"));
                match.setAwayTeam(rs.getString("awayTeam"));
                
                match.setShare1(rs.getFloat("share1"));
                asoc.put("1",rs.getFloat("share1"));
                match.setShareX(rs.getFloat("shareX"));
                asoc.put("X",rs.getFloat("shareX"));
                match.setShare2(rs.getFloat("share2"));
                asoc.put("2",rs.getFloat("share2"));
                match.setShare1X(rs.getFloat("share1X"));
                asoc.put("1X",rs.getFloat("share1X"));
                match.setShareX2(rs.getFloat("shareX2"));
                asoc.put("X2",rs.getFloat("shareX2"));
                match.setShare12(rs.getFloat("share12"));
                asoc.put("12",rs.getFloat("share12"));
                match.setFinalScore(rs.getString("finalScore") );
                
                match.setAsoc(asoc);
                matches.add(match);
                //printMap(matchAsoc.getAsoc());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }
    
    public void printMap(Map<String, Float> mp) {
        Iterator<?> it = mp.entrySet().iterator();
        System.out.println("\n Meciul are cotele: ");
        while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public Match getMatchById(int matchId) {
        Match match1 = new Match();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from matches where matchid=?");
            preparedStatement.setInt(1, matchId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	match1.setMatchid(rs.getInt("matchid"));
            	match1.setHostTeam(rs.getString("hostTeam"));
            	match1.setAwayTeam(rs.getString("awayTeam"));
            	match1.setShare1(rs.getFloat("share1"));
            	asoc.put("1", rs.getFloat("share1"));
            	match1.setShareX(rs.getFloat("shareX"));
            	asoc.put("X", rs.getFloat("shareX"));
            	match1.setShare2(rs.getFloat("share2"));
            	asoc.put("2", rs.getFloat("share2"));
            	match1.setShare1X(rs.getFloat("share1X"));
            	asoc.put("1X", rs.getFloat("share1X"));
            	match1.setShareX2(rs.getFloat("shareX2"));
            	asoc.put("X2", rs.getFloat("shareX2"));
            	match1.setShare12(rs.getFloat("share12"));
            	asoc.put("12", rs.getFloat("share12"));
            	match1.setAsoc(asoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return match1;
    }
    
}

