package model;

public class BookedTickets {

	private int Booking_id;
	private int event_id;
	private int  customer_id;
	private int pass_num;
	private Float Booking_amt;
	private int ticket_qty;
	
	
	public int getBooking_id() {
		return Booking_id;
	}
	
	public BookedTickets()
	{
		
	}
	
	public BookedTickets(int eventId,int customerId, int passnum,float booking_amt,int ticket_qty, int bookingId )
	{
		this.event_id = eventId;
		this.customer_id = customerId;
		this.pass_num = passnum;
		this.Booking_amt = booking_amt;
		this.ticket_qty = ticket_qty;
		this.Booking_id = bookingId;
	}

	public BookedTickets(Float fare,int eventid, int passnum){
		
		this.Booking_amt = fare;
		this.event_id = eventid;
		this.pass_num = passnum;
	}
	
	public void setBooking_id(int booking_id) {
		Booking_id = booking_id;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getPass_num() {
		return pass_num;
	}

	public void setPass_num(int pass_num) {
		this.pass_num = pass_num;
	}

	public Float getBooking_amt() {
		return Booking_amt;
	}

	public void setBooking_amt(Float booking_amt) {
		Booking_amt = booking_amt;
	}

	public int getTicket_qty() {
		return ticket_qty;
	}

	public void setTicket_qty(int ticket_qty) {
		this.ticket_qty = ticket_qty;
	}

	
	
	
	
	
}
