package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.PrintToPDF;
import dao.MatchDao;
import model.Match;
import model.Ticket;

public class MatchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/match.jsp";
    private static String LIST_MATCH = "/listMatch-admin.jsp";
    private static String LIST_TICKET_MATCH = "/listTicketMatch.jsp";
    private static String LIST_MATCH_USER = "/listMatch-user.jsp";
    float betCost = 1.0f;
    private MatchDao dao;
    Match match01 = new Match();
    Ticket ticket = new Ticket();
    List<Match> listOfMatches = new ArrayList<Match>();
    List<Match> matchesHashList = new ArrayList<Match>();
    HashMap<String,Float> hass = new HashMap<>();
    ArrayList<Ticket> allTicketCodes = new ArrayList<Ticket>();
    ArrayList<Ticket> deletedCodes = new ArrayList<Ticket>();
    ArrayList<Ticket> ourTickets = new ArrayList<Ticket>();
    ArrayList<String> codes = new ArrayList<String>();
    ArrayList<Match> ticketsAll = new ArrayList<Match>();
    ArrayList<Match> matchTicketList = new ArrayList<Match>();
    
    public MatchController() {
        super();
        dao = new MatchDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
        boolean found = false;
        
        if (action.equalsIgnoreCase("add")) {
 
        	forward = LIST_MATCH_USER;
        	int matchId = Integer.parseInt(request.getParameter("matchId"));
            Match match = dao.getMatchById(matchId);
            hass = match.getAsoc();
            
     // Try to find out the key for the clicked `share`
        	Iterator<?> it = hass.entrySet().iterator();
            while (it.hasNext()) {
                @SuppressWarnings("rawtypes")
    			Map.Entry pairs = (Map.Entry)it.next();
                if ( (float)pairs.getValue() ==  Float.parseFloat(request.getParameter("share1")) )
                {
                	match.setShareType((String) pairs.getKey() );
                	match.setShareValue((Float) pairs.getValue() );
                }
                else if ( (float)pairs.getValue() ==  Float.parseFloat(request.getParameter("shareX")) )
                {
                	match.setShareType((String) pairs.getKey() );
                	match.setShareValue((Float) pairs.getValue() );
                }
                else if ( (float)pairs.getValue() ==  Float.parseFloat(request.getParameter("share2")) )
                {
                	match.setShareType((String) pairs.getKey() );
                	match.setShareValue((Float) pairs.getValue() );
                }
                else if ( (float)pairs.getValue() ==  Float.parseFloat(request.getParameter("share1X")) )
                {
                	match.setShareType((String) pairs.getKey() );
                	match.setShareValue((Float) pairs.getValue() );
                }
                else if ( (float)pairs.getValue() ==  Float.parseFloat(request.getParameter("shareX2")) )
                {
                	match.setShareType((String) pairs.getKey() );
                	match.setShareValue((Float) pairs.getValue() );
                }
                else if ( (float)pairs.getValue() ==  Float.parseFloat(request.getParameter("share12")) )
                {
                	match.setShareType((String) pairs.getKey() );
                	match.setShareValue((Float) pairs.getValue() );
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
            
    // Never add a match if it's ID already exists in the `ticket`            
            if (ticket.getTicket().size() == 0)
            	listOfMatches.add(match);
            else {
            	for (int i = 0; i < ticket.getTicket().size(); i++)
            	{
            		if (ticket.getTicket().get(i).getMatchid() == match.getMatchid() )
            		{
            			ticket.getTicket().get(i).setShareType(match.getShareType() );
            			ticket.getTicket().get(i).setShareValue(match.getShareValue() );
            			found = true;
            		}
            	}
            	if (found == false)
            		listOfMatches.add(match);
            } 
    // --------------------------------------------------------------
            ticket.setTicket(listOfMatches);
            ticket.calculateFinalShare();
            ticket.setBet(betCost);
            request.setAttribute("matches", dao.getAllMatches() );
            request.setAttribute("ticket", ticket.getTicket() );
            request.setAttribute("bet", ticket.getBet() );
            request.setAttribute("finalShare", ticket.getResult() );
            
        } else if (action.equalsIgnoreCase("listMatch_user")){
            forward = LIST_MATCH_USER;
            request.setAttribute("matches", dao.getAllMatches());
 
        } else if (action.equalsIgnoreCase("deleteMatch")){
            forward = LIST_MATCH_USER;
            int matchIdDel = Integer.parseInt(request.getParameter("matchId"));
            int index = 0;
            
            for (int i = 0; i < ticket.getTicket().size(); i++)
            {
            	if (ticket.getTicket().get(i).getMatchid() == matchIdDel )
            	index = i;
            }
            
            ticket.getTicket().remove(index);    
            ticket.calculateFinalShare();
            ticket.setBet(betCost);
            
            request.setAttribute("matches", dao.getAllMatches());
            request.setAttribute("ticket", ticket.getTicket() );
            request.setAttribute("bet", ticket.getBet() );
            request.setAttribute("finalShare", ticket.getResult() );
        } else if (action.equalsIgnoreCase("delete")) {
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            dao.deleteMatch(matchId);
            forward = LIST_MATCH;
            request.setAttribute("matches", dao.getAllMatches());    
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int matchId = Integer.parseInt(request.getParameter("matchId"));
            Match match = dao.getMatchById(matchId);
            request.setAttribute("match", match);
        } else if (action.equalsIgnoreCase("listMatch")) {
            forward = LIST_MATCH;
            request.setAttribute("matches", dao.getAllMatches());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String forward="";
    	
    	if (request.getParameter("Ok") != null) {
        	betCost = Float.parseFloat(request.getParameter("price") );
            if (betCost != 0)
            {
            	ticket.setBet(betCost);
            	ticket.calculateGain();
            }
            forward = LIST_MATCH_USER;
        } else if (request.getParameter("Print") != null) {
        	if (ticket.getTicket().size() >=1 )
        	{
        		PrintToPDF print = new PrintToPDF();
        		print.createPDF(ticket);
        		dao.addTicketMatches(ticket);
        	}
        	forward = LIST_MATCH_USER;
        } else if (request.getParameter("delTickets") != null) {
        	dao.deleteAllTicketMatches();
        	forward = LIST_MATCH;
        } else if (request.getParameter("viewTickets") != null) {
        	forward = LIST_TICKET_MATCH;
            request.setAttribute("ticketCodes", dao.getTicketCodes() );
            request.setAttribute("allMatches", dao.getTicketMatches() );
        } else if (request.getParameter("finalScore") != null) {
        	forward = LIST_MATCH;
        	ticketsAll = dao.getTicketMatches();
        	deletedCodes = dao.getTicketCodes();
        	
        // search how many different codes do we have in the DataBase
        	for (int t = 0; t < deletedCodes.size(); t++)
        	{
        		codes.add( deletedCodes.get(t).getCode() );
        		//System.out.println("\n DB code " + (t+1) + " este: " + codes.get(t) );
        	}
        	for (int s = 0; s < codes.size() - 1; s++)
        	{
        		for( int q = 1; q < codes.size(); q++)
        		{
        			if (codes.get(s).equals(codes.get(q)) )
        				codes.remove(q);	
        		}
        		//System.out.println("\n Codul " + (s+1) + " este: " + codes.get(s) );
        	}
        	
        	
        	
        	for (int i = 0; i < dao.getAllMatches().size(); i++)
        	{
        	// write the score for each match in the DataBase table column
        		dao.addScores(dao.getAllMatches().get(i) );
        		String[] scor = dao.getAllMatches().get(i).getFinalScore().split("-");
        		int scor1 = Integer.parseInt(scor[0]);
        		int scor2 = Integer.parseInt(scor[1]);
        		System.out.println("\n Meciul " + (i+1) + " are scorul: " + scor1 + "-" + scor2);
        			
        		
        	// try to find if the ticket match is winner or not
        		if (scor1 > scor2)
        		{
        			for (int k = 0; k < ticketsAll.size(); k++)
        			{
        				if ((ticketsAll.get(k).getMatchid() == dao.getAllMatches().get(i).getMatchid()) &&  
        							((ticketsAll.get(k).getShareType().equals("1")) || 
        							(ticketsAll.get(k).getShareType().equals("1X")) || 
        							(ticketsAll.get(k).getShareType().equals("12"))))        				
        				{
        					ticketsAll.get(k).setStatus(1);
        					//dao.getTicketMatches().get(k).setStatus(1);
        					System.out.println("\n Meciul " + (k+1) + " este " + ticketsAll.get(k).getStatus() );
        					//System.out.println("\n Meciul " + (k+1) + " este " + dao.getTicketMatches().get(k).getStatus() );
        				}
                	}
      
        		} else if (scor1 == scor2)
        		{
        			for (int k = 0; k < ticketsAll.size(); k++)
        			{
        				if ((ticketsAll.get(k).getMatchid() == dao.getAllMatches().get(i).getMatchid()) &&
        							((ticketsAll.get(k).getShareType().equals("1X")) || 
        							(ticketsAll.get(k).getShareType().equals("X")) || 
        							(ticketsAll.get(k).getShareType().equals("X2"))))
        				{
        					ticketsAll.get(k).setStatus(1);
        					//dao.getTicketMatches().get(k).setStatus(1);
        					System.out.println("\n Meciul " + (k+1) + " este " + ticketsAll.get(k).getStatus() );
        					//System.out.println("\n Meciul " + (k+1) + " este " + dao.getTicketMatches().get(k).getStatus() );
        				}
        			}
        			
        		} else if (scor1 < scor2)
    			{
        			for (int k = 0; k < dao.getTicketMatches().size(); k++)
        			{
        				if ((ticketsAll.get(k).getMatchid() == dao.getAllMatches().get(i).getMatchid()) && 
        							((ticketsAll.get(k).getShareType().equals("2")) || 
        							(ticketsAll.get(k).getShareType().equals("X2")) || 
        							(ticketsAll.get(k).getShareType().equals("12"))))
        				{	
        					ticketsAll.get(k).setStatus(1);
        					//dao.getTicketMatches().get(k).setStatus(1);
        					System.out.println("\n Meciul " + (k+1) + " este " + ticketsAll.get(k).getStatus() );
        					//System.out.println("\n Meciul " + (k+1) + " este " + dao.getTicketMatches().get(k).getStatus() );	
        				}
        			}
    			}
        	}
        	
        	int meci = 0;
        	for( int s1 = 0; s1 < codes.size(); s1++)
    		{
        		Ticket ticketFINAL = new Ticket();
        		ArrayList<Match> allTicketMathes = new ArrayList<Match>();
    			matchTicketList = dao.getTicketById(codes.get(s1) );
    			for( int h = 0; h < matchTicketList.size(); h++)
    			{
    				meci++;
    				for (int k = 0; k < ticketsAll.size(); k++)
    				{
    					if( (matchTicketList.get(h).getMatchid() == ticketsAll.get(k).getMatchid() ) &&
    							(matchTicketList.get(h).getShareType().equals(ticketsAll.get(k).getShareType())) )
    						matchTicketList.get(h).setStatus( ticketsAll.get(k).getStatus() );
    				}
    				System.out.println("\n Meciul >NOU< " + meci + " are status = " + matchTicketList.get(h).getStatus() );
    				allTicketMathes.add(matchTicketList.get(h) );
    			}
    			ticketFINAL.setTicket(allTicketMathes);
    			ticketFINAL.setCode(codes.get(s1) );
    			boolean mode = true;
    			for( int e = 0; e < ticketFINAL.getTicket().size(); e++)
    			{
    				if( ticketFINAL.getTicket().get(e).getStatus() == 0)
    					mode = false;
    			}
    			if( mode == true)
    				ticketFINAL.setTicketStatus(true);
    			else
    				ticketFINAL.setTicketStatus(false);
    			dao.addTicketMatches(ticketFINAL);
    			PrintToPDF print = new PrintToPDF();
        		print.createPDF(ticketFINAL);
    		}
        	//dao.deleteTicketMatchesBy();
        	
        	int no = 0;
        	for( int f = 0; f < dao.getTicketMatches().size(); f++)
        	{
        		no++;
        		System.out.println("\n Meciul X " + no + " are asa -> " + dao.getTicketMatches().get(f).getStatus() );
        	}
        	
        	
        } else {
    	Match match = new Match();
        match.setHostTeam(request.getParameter("hostTeam"));
        match.setAwayTeam(request.getParameter("awayTeam"));
        match.setShare1(Float.parseFloat(request.getParameter("share1")) );
        match.setShareX(Float.parseFloat(request.getParameter("shareX")) );
        match.setShare2(Float.parseFloat(request.getParameter("share2")) );
        match.setShare1X(Float.parseFloat(request.getParameter("share1X")) );
        match.setShareX2(Float.parseFloat(request.getParameter("shareX2")) );
        match.setShare12(Float.parseFloat(request.getParameter("share12")) );
        
        String matchid = request.getParameter("matchid");
        if(matchid == null || matchid.isEmpty())
        {
            dao.addMatch(match);
        }
        else
        {
        	match.setMatchid(Integer.parseInt(matchid));
            dao.updateMatch(match);
        }
        forward = LIST_MATCH;
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("matches", dao.getAllMatches() );
        request.setAttribute("ticket", ticket.getTicket() );
        request.setAttribute("finalShare", ticket.getResult() );
        request.setAttribute("bet", ticket.getBet() );
        request.setAttribute("gainValue", ticket.getGainValue() );
        view.forward(request, response);
    }
}
