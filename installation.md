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
which is included in the [android/app/libs folder](https://github.com/ppeters0502/whereabouts/tree/master/android/app/libs) of this project. These JAR files are included in the project, so they should work without configuration, but be aware of Eclipse Paho Client updates and possible new JAR files that are needed if this version becomes obsolete.

#### Setup Process
* Download repository to local folder
* Open Android Project (all files within the Android Folder) in Android Studio
* Obtain API key for Google Maps through [Google API Console](https://console.developers.google.com/getting-started)
* Add API key in [Android Manifest file](https://github.com/ppeters0502/whereabouts/blob/master/android/app/src/main/AndroidManifest.xml) within Android studio in the code section where the value should say "YOUR_API_KEY"
```
<!--
The API key for Google Maps-based APIs is defined as a string resource.
(See the file "res/values/google_maps_api.xml").
Note that the API key is linked to the encryption key used to sign the APK.
You need a different API key for each encryption key, including the release key that is used to
sign the APK for publishing.
You can define the keys for the debug and release targets in src/debug/ and src/release/.
-->
    <meta-data
    android:name="com.google.android.maps.v2.API_KEY"
    android:value="YOUR_API_KEY" />
```
* Add API Key in [google_maps_api.xml file](https://github.com/ppeters0502/whereabouts/blob/master/android/app/src/main/res/values/google_maps_api.xml)
```
<string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">YOUR_API_KEY</string>
```
* Sync Gradle Project and Build Project in Android Studio.
* If using an Emulator device, deploy the app to the emulator, otherwise, you can install the app through ADB to the physical device (see hardware and software requirements page) that you wish to install the app on.
```
ADB install android/app/build/outputs/apk/debug/app-debug.apk
```


