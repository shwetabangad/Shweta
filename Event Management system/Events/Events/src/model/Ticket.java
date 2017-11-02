package model;

public class Ticket {

	
	private Integer event_tickets_id;
	private Integer event_id;
	private String  ticket_type_code;
	private String ticket_type_capacity;
	private Float ticket_fare;
	
	public Ticket()
	{
		
	}
	

	public Ticket(String tickettype, String ticketcap, Float fare){
		this.ticket_type_code = tickettype;
		this.ticket_type_capacity = ticketcap;
		this.ticket_fare = fare;
	}
	
	
	
	public Integer getEvent_tickets_id() {
		return event_tickets_id;
	}
	public void setEvent_tickets_id(Integer event_tickets_id) {
		this.event_tickets_id = event_tickets_id;
	}
	public Integer getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	public String getTicket_type_code() {
		return ticket_type_code;
	}
	public void setTicket_type_code(String ticket_type_code) {
		this.ticket_type_code = ticket_type_code;
	}
	public String getTicket_type_capacity() {
		return ticket_type_capacity;
	}
	public void setTicket_type_capacity(String ticket_type_capacity) {
		this.ticket_type_capacity = ticket_type_capacity;
	}
	public Float getTicket_fare() {
		return ticket_fare;
	}
	public void setTicket_fare(Float ticket_fare) {
		this.ticket_fare = ticket_fare;
	}
	
	
	
	
	
	
}
