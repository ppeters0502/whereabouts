# WhereAbouts - Where u @ fam? 
**Project Idea for CYBR8480 - Secure Mobile Development class at the University of Nebraska Omaha**

## Executive Summary
Google Maps, Waze, Uber, GrubHub, the list of popular location based services continues to grow for smartphone users. The concept of a person or company knowing your exact location in a matter of seconds, a concept that just a decade ago sounded completely foreign to the average user, is now a tried and true business strategy for bringing the services of the internet to your exact location. When it comes to location tracking by private individuals though, many more people are less comfortable with that idea, even within the confines of family and close friends
WhereAbouts is an effort to put a friendlier face on location tracking loved ones, by putting the power of where your location data is going in your hands. 

## Project Goals
* Connect to MQTT Broker using TLS secured connection
* Publish location data on MQTT broker through secured connection
* Connect to MQTT broker (Raspberry Pi) through bluetooth to get security certificate and configure user credentials
* Establish Geofence Waypoints to trigger enter/exit reactions to trigger changes

## Application Requirements
For this project I set 3 primary user stories

### User Stories
As a ***Publisher*** I want to ***Publish location data through secured connection*** so that I can ***Tell my wife that I'm heading home***

***Acceptance Criteria:***
* A user will be able to connect to an MQTT broker through a secure TLS-encrypted connection
* Once connected, a user will automatically publish their GPS location to the secured broker

As a ***Subscriber*** I want to ***Connect and subscribe to location data from the Publisher*** so that I can ***Be notified that my husband is heading home***

***Acceptance Criteria***
* A different user than the publisher will simultaneously connect to an MQTT broker through a secure TLS-encrypted connection
* Once connected, the user will subscribe to the topic that the publisher is publishing their location to, and will receive the GPS location data automatically when the original user publishes it.

* As a ***New User*** I want to ***Set geofence waypoints*** so that I can ***Customize the notifications published to the broker***
* As a ***New User*** I want to ***Setup secure MQTT broker on the Raspberry Pi*** so that I can ***Securely publish and subscribe to location notifications*** 









