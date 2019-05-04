# WhereAbouts - Where u @ fam? 
**Project Idea for CYBR8480 - Secure Mobile Development class at the University of Nebraska Omaha**

## Installation Instructions
The whereabouts project is an android application that was designed to put a friendlier face on location tracking.

#### Project Structure
The contents of the application architecture can be found in the android folder of the repository. Cloning the project repository to a local location, 
or downloading the repository in a zip folder should download all of the contents of the application. The contents of the android folder can then be imported
as an android project in Android Studio 

#### Dependencies and API keys
Since this project relies on Google Maps, a Maps API key needs to applied in the [Android Manifest file](https://github.com/ppeters0502/whereabouts/blob/master/android/app/src/main/AndroidManifest.xml)
 and in the [google_maps_api.xml](https://github.com/ppeters0502/whereabouts/blob/master/android/app/src/main/res/values/google_maps_api.xml) file. You can register for and obtain an API key from the [Google API Console](https://console.developers.google.com/getting-started)

This project also relies on the [Eclipse Paho MQTT Android Client](https://www.eclipse.org/paho/clients/java/) 
which is included in the [android/app/libs folder](https://github.com/ppeters0502/whereabouts/tree/master/android/app/libs) of this project.


