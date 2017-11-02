# HeidiSQL Dump 
#
# --------------------------------------------------------
# Host:                 127.0.0.1
# Database:             vehicle_towing_db
# Server version:       5.0.27-community-nt
# Server OS:            Win32
# Target-Compatibility: Standard ANSI SQL
# HeidiSQL version:     3.2 Revision: 1129
# --------------------------------------------------------

/*!40100 SET CHARACTER SET latin1;*/
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ANSI';*/
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;*/


#
# Database structure for database 'vehicle_towing_db'
#

CREATE DATABASE /*!32312 IF NOT EXISTS*/ "vehicle_towing_db" /*!40100 DEFAULT CHARACTER SET latin1 */;

USE "vehicle_towing_db";


#
# Table structure for table 'incidente_table'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "incidente_table" (
  "incidente_id" int(10) unsigned NOT NULL auto_increment,
  "incidente_description" varchar(45) default NULL,
  "incidente_vehicle_no_image" longblob,
  "no_plate_text" varchar(50) default NULL,
  "incidente_user_id" int(10) unsigned default NULL,
  "incidente_towing_agent_id" int(10) unsigned default NULL,
  "incidente_fine_amount" int(10) unsigned default NULL,
  "incidente_date" date default NULL,
  "incidente_status" int(10) default '0',
  "incident_police_station_id" int(10) unsigned default '0',
  "sms_sent_status" varchar(50) default '0',
  "incidente_lat" double default NULL,
  "incidente_long" double default NULL,
  PRIMARY KEY  ("incidente_id")
) /*!40100 DEFAULT CHARSET=latin1*/;



#
# Table structure for table 'police_station_table'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "police_station_table" (
  "police_station_id" int(10) unsigned NOT NULL auto_increment,
  "police_station_name" varchar(50) default NULL,
  "police_station_address" varchar(100) default NULL,
  "police_station_lat" double unsigned default NULL,
  "police_station_long" double unsigned default NULL,
  PRIMARY KEY  ("police_station_id")
) /*!40100 DEFAULT CHARSET=latin1*/;



#
# Table structure for table 'towing_agent_table'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "towing_agent_table" (
  "towing_agent_id" int(11) NOT NULL auto_increment,
  "towing_agent_firstname" varchar(50) default NULL,
  "towing_agent_lastname" varchar(50) default NULL,
  "towing_agent_gender" varchar(50) default NULL,
  "towing_agent_address" varchar(50) default NULL,
  "towing_agent_mobileno" varchar(50) default NULL,
  "towing_agent_emailid" varchar(50) default NULL,
  "towing_agent_username" varchar(50) default NULL,
  "towing_agent_password" varchar(50) default NULL,
  "towing_agent_register_lat" double default NULL,
  "towing_agent_register_long" double default NULL,
  "towing_agent_current_lat" double default NULL,
  "towing_agent_current_long" varchar(50) default NULL,
  PRIMARY KEY  ("towing_agent_id")
) /*!40100 DEFAULT CHARSET=latin1*/;



#
# Table structure for table 'user_table'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "user_table" (
  "user_id" int(11) NOT NULL auto_increment,
  "user_firstname" varchar(50) default NULL,
  "user_lastname" varchar(50) default NULL,
  "user_age" varchar(50) default NULL,
  "user_gender" varchar(50) default NULL,
  "user_address" varchar(50) default NULL,
  "user_emailid" varchar(50) default NULL,
  "user_mobileno" varchar(50) default NULL,
  PRIMARY KEY  ("user_id")
) /*!40100 DEFAULT CHARSET=latin1*/;



#
# Table structure for table 'vehicle_no_table'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "vehicle_no_table" (
  "vehicle_id" int(10) unsigned NOT NULL auto_increment,
  "vehicle_number" varchar(45) NOT NULL default '',
  "vehicle_user_id" int(10) unsigned NOT NULL,
  "vehicle_type" varchar(45) default NULL,
  PRIMARY KEY  ("vehicle_id")
) /*!40100 DEFAULT CHARSET=latin1*/;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE;*/
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;*/
