# Use Cases
The purpose of these Use Cases are to try to identify the base classes, class attributes, and methods that will make up the core functionality of the application. I start by first elaborating on the User Stories that are already published in the readme, and convert them into use cases that walk through the application workflow (at least, how I imagine it should go). From there, I go back to the newly written use case, find all of the nouns and all of the verbs, and put them together in a list that combines from all of the use cases I generate. Typically, the most used nouns that apply to this specific application (so avoiding nouns like "system" and "application") are later worked into base classes in the application architecture, and the most used verbs pertaining to these nouns are actionable methods that are contained in these classes.

### Use Case 1 - Connect to Broker and publish location

User opens application for first time. User creates profile with username and password. User adds new MQTT broker. User adds Username, Password and TLS certificate to broker settings. User connects to broker. Application grabs phone GPS location and posts location to MQTT topic. User closes application.

### Use Case 1 with nouns in italics and verbs in bold

*User* **opens** *application* for first time. *User* **creates** *profile* with *username* and *password*. *User* **adds** new *MQTT broker*. *User* **adds** *Username*, *Password* and *TLS certificate* to *broker settings*. *User* **connects** to *broker*. *Application* **grabs** phone *GPS location* and **posts location** to *MQTT topic*. *User* **closes** *application*.

### Use Case 2 - Connect to Broker and subscribe to user
User logs in to application. Application is already connected to MQTT broker with stored username, password, and TLS Certificate. User browses other users that are on the broker they are connected to. User subscribes to different user to receive location updates. User is now subscribed for updates. User closes application

### Use Case 2 with nouns in italics and verbs in bold
*User* **logs in** to *application*. *Application* is already **connected** to *MQTT broker* with stored *username, password, and TLS Certificate*. *User* **browses** other *users* that are on the *broker* they are connected to. *User* **subscribes** to different *user* to **receive** location *updates*. *User* is now **subscribed** for *updates*. *User* **closes** *application*

### Use Case 3 - Receive Notification from subscribed user
Application connects to MQTT Broker with stored credentials and TLS Certificate. User that application owner is subscribed to publishes location data. MQTT Broker sends post data to application. Application alerts User that subscribed user posted location data.  

### Use Case 3 with nouns in italics and verbs in bold
*Application* **connects** to *MQTT Broker* with stored *credentials* and *TLS Certificate*. *User* that *application owner* is **subscribed** to **publishes** *location data*. *MQTT Broker* **sends** *post data* to *application*. *Application* **alerts** *User* that *subscribed user* **posted** *location data*. 

### Use Case 4 - Set Geofence Waypoints
User opens application and logs into profile. User opens current list of waypoints. User selects to add a new waypoint. User is prompted to either grab current location, or let user select different location. User opts to select different location and a google maps view is displayed. User finds location in google maps, and selects new location. Latitude and Longitude coordinates are sent back to application and user is brought back to waypoint config screen. User supplies radius of new waypoint and names the new waypoint. User saves new waypoint. User publishes new notification when they enter new waypoint geofence.

### Use Case 4 with nouns in italics and verbs in bold
*User* **opens** *application* and **logs into profile**. *User* **opens** current *list* of *waypoints*. *User* **selects** to **add** a *new waypoint*. *User* is **prompted** to either **grab current location**, or let user **select different location**. *User* **opts** to **select different location** and a google maps *view* is **displayed**. *User* **finds location** in *google maps*, and **selects** new *location*. *Latitude and Longitude coordinates* are **sent back** to *application* and *user* is brought back to *waypoint* config screen. *User* **supplies** *radius* of new *waypoint* and **names** the new *waypoint*. *User* **saves** new *waypoint*. *User* **publishes new notification** when they **enter** new *waypoint geofence*.

### Noun and Verb Lists
From the Use Cases identified above, here is list of most common nouns and verbs that are used to describe the application workflow for whereabouts. Since the majority of the verbs are kind of ambiguos out of context (add, open and create, for example) I expand the list of verbs with that they are acting against, noun-wise, to make the methods start to shine through the list a little easier

| Common Nouns in Use Cases |
| ------------------------- |
| User |
| Application | 
| Profile |
| Username |
| Password |
| TLS Certificate |
| MQTT Broker |
| MQTT Topic |
| Location Updates |
| Application Owner |
| Subscribed User |
| Waypoints |
| Current Location |
| Different Location |
| Latitude Coordinates |
| Longitude Coordinates |
| Waypoint Radius |
| Notification Event |
| Waypoint Geofence  |

| Common Verbs in Use Cases | 
| ------------------------- |
| Opens Application |
| Creates Profile |
| Adds Broker |
| Adds User Credentials |
| Connects to Broker |
| Grabs GPS Location |
| Posts Location |
| Closes Application |
| Logs in to Application |
| Browses other broker users |
| Subscribes to User |
| Receives location updates |
| Publishes location updates |
| Alerts User |
| Opens list of waypoints |
| Adds new waypoint |
| Selects new location |
| Supplies radius of new waypoint |
| Names waypoint |
| Saves new waypoint |
| Publishes New Notification |
| Enters Waypoint Geofence |



