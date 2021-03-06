# RoboApp Fall 2016
## Members
* Kurtis Davis
* Alex Vild
* Luke Shays
* Quintin Fettes
* Seattle Ruzek
* Zach Taylor


[Click here to view in the Google Play Store](https://play.google.com/store/apps/details?id=com.robodoot.dr.facetracktest)


# RoboApp readme file
## Team project for CS 4560/4561 (2016-2017)

![alt tag](https://scan.coverity.com/projects/8169/badge.svg?flat=1)

The project, led by <a href="https://www.ohio.edu/engineering/about/people/profiles.cfm?profile=zhuj">Dr. Jim Zhu</a>, has the goal of creating a sentient robotic bobcat.
The end product will be a robotic bobcat which can navigate an environment based on sight.  The cat, called Robocat from here, will be able to find food, in the form of a charging station, as well as form affinities for people it is familiar with.  Robocat, beyond visual input, will also  accept audio input.  Robocat will be able to recognize and react to  its own name as well as several other phrases and the volume of the audio.

## Current State
RoboCat is able to accurately track faces. This is done at all times while the app is at the behavior screen.
While connected to the cat, the cat will turn its "head" to center the biggest, most centered face
in its camera view. Note: this is currently set to work with the front camera of a phone shooting
a 1024x768 image at 30fps. These values can be modified in createCameraSource() in fdActivity.java.

RoboCat can respond to voice commands through the [PocketSphinx API](https://github.com/cmusphinx/pocketsphinx). Additional info regarding the installation and use of this API can be found in /doc/pocketsphinx-voice-recognition. PocketSphinx recognizes voices from a file in 'app/assets/commands.gram'. If you want to add voice commands you must add the key words to this file or the cat wont recognize the words you are saying.

Also currently available in the app are activities which allow terminal contact with the pololu, for log purposes, and to manually send values to the servos (The servos are what allow the bobcat to maneuver through the environment). Similar commands are also found in 'CatEmotion.java' and 'FdActivity'.

Robocat is also able to switch from face tracking mode to color tracking mode, by a simple button press on the main UI. The color to track and sensitivity can be changed by going to robocat's menu and selecting color tracking settings. Robocat creates a 3x3 grid and examines each subsection to determine which area has the most of the specified color, and then turns its "head" towards that area.

## Installation and Running the Application
To build and run this application, you should do the following:
1. `git clone` the master branch of this repo. The repo can be found under kd345312/RoboApp
2. Download the latest version of [Android Studio](https://developer.android.com/studio/index.html)
3. Open Android studio, and open an existing project
4. Open `RoboApp/RoboApp` inside Android Studio. Make sure the app you open in android studio is **not** just simply the RoboApp directory you just cloned, but rather the RoboApp project file within this application, as otherwise the app will not run. RoboApp the repo is composed of several different android applications that are injected into the main RoboApp app, located at RoboApp/RoboApp.
5. Build and Run the project within android studio either on to your android device or on to a virtual device.

## Getting Started
The majority of the code base takes place in the FdActivity.java file, located at `RoboApp/RoboApp/app/src/main/java/com/robodoot`. This is the best place to start to try to get a handle on the application. From this file, you can see when the other classes are brought in, such as CatEmotion.java, which supplies auxilary functions for dealing with how the cat's mood is displayed.

Additional help for parts of the application can be found in the `./doc` and `./proj_doc` directories.

## Organization of Repo
`./doc` contains legacy documentation that was used when OpenCV was still part of the application. It also includes documentation for using the PocketSphinx voice recognizer to do active listening.

`./RoboApp` is the actual Roboapp and contains all code. **This is the application that is meant to be run on the phone and uploaded to the google store.**.
Color Tracking and Face Tracking: located in `./RoboApp/app/src/main/java/robodoot/RoboApp/FdActivity.java`

`./legacy_files` contains inherited files from previous teams with little use or no known use. See README in that directory

`./proj_doc` contains all non-code documents relating to our software development cycle. See README in that directory

`./FaceTracker` is a seperate app, composed of started and custom code, written to track multiple faces in a video feed. Also incorporated into the main RoboApp and is able to be used. Currently, it detects whether each eye is open and uses facial markers to determine happiness.

`./cameraTest` is a seperate app used to test color tracking. Any major changes to color tracking code can be safely tested here.

`./BatteryInformation` is another standalone app used to detect the level of battery charge on the Android device, it is already incorporated into the main RoboApp.

## Privacy Policy
We require access to these permissions within the application:
`Camera` For facial recognition and color tracking. We do not store any of this data for our own use, the camera just gives information to the code for the cat to track

`Microphone` Pocketsphinx is used within this application to provide always on voice listening. We do not log or store the data that is heard, we just take the commands as strings and use them in our code.

`Storage` Storing data such as accelerometer data or voice commands to the phone for debugging purposes.

## Known Issues
The Arduino board is not yet connected physically to the body of RoboCat.
The Arduino is recognized as a device by the app, and the Arduino receives the servo commands from the app when connected, but it does not write to the Serial1 port(pololu) to move the servos.

The distance data can be written to the phone if a broadcastreceiver is correctly implemented.

Active listening through PocketSphinx has been merged into the master branch, however it only works with certain Android devices. Known working devices (Galaxy s5 and Google Pixel).

The front camera on older Android phones only captures images at 15 fps, so RoboCat can’t follow faces if the user is moving too fast. 
FIXED: Updated to track faces at 30 fps and it works much better now with faster moving users.

Color tracking is still a standalone app and needs to be merged into the master branch, and also needs to receive input from a video feed (currently only captures input from still pictures). 
FIXED: Color Tracking is merged into master branch and now tracks color via video feed. 

Google removed RoboApp from the Android App Store due to a security vulnerability related to a new version of SSL that was released while we were developing. 
FIXED: the security vulnerability issue was resolved and the app is now actively available on the google play store. 

8,000 static analysis errors (mostly .xml errors and errors with the battery app). We are ignoring the spelling errors 

![alt tag](https://raw.githubusercontent.com/kd345312/RoboApp/catFrown.png)

![alt tag](https://raw.githubusercontent.com/kd345312/RoboApp/colors.png)

![alt tag](https://raw.githubusercontent.com/kd345312/RoboApp/readme.png)