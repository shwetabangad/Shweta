package model;

import java.sql.Time;
import java.util.Date;

public class Event {

	private Integer event_id;
	private String event_title;
	private String event_type_code;
	private String event_description;
	private String address;
	private Date event_start_date;
	private Date event_end_date;
	private Time event_start_time;
	private Time event_end_time;
	
	
	public Event(String eventtitle, Time eventstTime, Time eventenTime, String eventtype,  String eventdesc, String address,Date eventstDt, Date eventenDT )
	{
		
		this.event_type_code = eventtype;
		this.event_title = eventtitle;
		this.event_description = eventdesc;
		this.address = address;
		this.event_start_date = eventstDt;
		this.event_end_date = eventenDT;
		this.event_start_time = eventstTime;
		this.event_end_time = eventenTime;
	}

	public Event(int eventId, String eventTitle, String eventTypeCode, String eventDescription, String address) {
		this.event_id = eventId;
		this.event_title = eventTitle;
		this.event_type_code = eventTypeCode;
		this.event_description = eventDescription;
		this.address = address;
	}
	
	public Event(int eventid,String eventtype, String eventdesc, String address, Date eventstDt, Date eventenDT ) {
		
		this.event_id = eventid;
		this.event_type_code = eventtype;
		this.event_description = eventdesc;
		this.address = address;
		this.event_start_date = eventstDt;
		this.event_end_date = eventenDT;
	}



	public Event(int eventid, String eventtitle, String eventtype,  String eventdesc, String address, Date eventstDt, Date eventenDT )
	{
		this.event_id=eventid;
		this.event_type_code = eventtype;
		this.event_title = eventtitle;
		this.event_description = eventdesc;
		this.address = address;
		this.event_start_date = eventstDt;
		this.event_end_date = eventenDT;
	
	}
	

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	
	public String getEvent_title() {
		return event_title;
	}

	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}

	public String getEvent_type_code() {
		return event_type_code;
	}

	public void setEvent_type_code(String event_type_code) {
		this.event_type_code = event_type_code;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEvent_start_date() {
		return event_start_date;
	}

	public void setEvent_start_date(Date event_start_date) {
		this.event_start_date = event_start_date;
	}

	public Date getEvent_end_date() {
		return event_end_date;
	}

	public void setEvent_end_date(Date event_end_date) {
		this.event_end_date = event_end_date;
	}

	public Time getEvent_start_time() {
		return event_start_time;
	}

	public void setEvent_start_time(Time event_start_time) {
		this.event_start_time = event_start_time;
	}

	public Time getEvent_end_time() {
		return event_end_time;
	}

	public void setEvent_end_time(Time event_end_time) {
		this.event_end_time = event_end_time;
	}
	
	
}
