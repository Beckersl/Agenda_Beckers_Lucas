package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
	
	private List<Event> events = new LinkedList<>();
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        this.events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     * @throws Exception 
     */
    public List<Event> eventsInDay(LocalDate day) throws Exception {
    	if (this.events.isEmpty()) throw new Exception("Aucun event enregisté !");
    	List<Event> allEventInDay = new LinkedList<>();
    	for(Event e : this.events) {
    		if (e.isInDay(day)) allEventInDay.add(e);
    	}
    	return allEventInDay;
    }
    
    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     * @throws Exception 
     */
    public List<Event> findByTitle(String title) throws Exception {
    	if(this.events.isEmpty()){
            throw new Exception("Aucun evenement enregisté !");
        }
    	List<Event> sameTitle = new ArrayList<>();
    	for (Event n : events) {
    		if(n.getTitle().equals(title)){
                    sameTitle.add(n);
                }
    	}
    	return sameTitle;
    }
    
    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     * @throws Exception 
     */
    public boolean isFreeFor(Event e) throws Exception {
    	if(this.events.isEmpty()){
            throw new Exception("Aucun evenement enregisté !");
        }
    	
    	for (Event n : events) {
    		if (e.isInDay(n.getStart().toLocalDate())) {
    			 if (e.getStart().toLocalTime().isBefore(n.getStart().toLocalTime()) || e.getStart().toLocalTime().equals(n.getStart().toLocalTime())){
    		            if (e.getStart().plus(e.getDuration()).toLocalTime().isAfter(n.getStart().toLocalTime()) || e.getStart().plus(e.getDuration()).toLocalTime().equals(n.getStart().toLocalTime())) {
    		            	return false;
    		            }
    			 }
    		}
    	}
    	return true;
    }
}
