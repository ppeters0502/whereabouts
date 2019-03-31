# Use Cases
### Use Case 1 - Connect to Broker and publish location

User opens application for first time. User creates profile with username and password. User adds new MQTT broker. User adds Username, Password and TLS certificate to broker settings. User connects to broker. Application grabs phone GPS location and posts location to MQTT topic. User closes application.

### Use Case 1 with nouns in italics and verbs in bold

*User* **opens** *application* for first time. *User* **creates** *profile* with *username* and *password*. *User* **adds** new *MQTT broker*. *User* **adds** *Username*, *Password* and *TLS certificate* to *broker settings*. *User* **connects** to *broker*. *Application* **grabs** phone *GPS location* and **posts location** to *MQTT topic*. *User* **closes** *application*.

### Use Case 2 - Connect to Broker and subscribe to user
User logs in to application. Application is already connected to MQTT broker with stored username, password, and TLS Certificate. User browses other users that are on the broker they are connected to. User subscribes to different user to receive location updates. User is now subscribed for updates. User closes application

### Use Case 2 with nouns in italics and verbs in bold
*User* **logs in** to *application*. *Application* is already **connected** to *MQTT broker* with stored *username, password, and TLS Certificate*. *User* **browses** other *users* that are on the *broker* they are connected to. *User* **subscribes** to different *user* to **receive** location *updates*. *User* is now **subscribed** for *updates*. *User* **closes** *application*

### Use Case 3 - Receive Notification from subscribed user
Application connects to MQTT Broker. Different user publishes location data. 




