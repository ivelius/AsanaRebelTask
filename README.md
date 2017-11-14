# AsanaRebelTask
This is a small app that fetches Github repositories for a user and presents some info on the screen.

## Language
My language of choice for this task was Kotlin event though I am more proficient with Java and could've done it completely in Java. However there are plenty of other projects in my public repo that are written in Java.I am having fun so far using Kotlin , so I decided to have some fun during the task :)

## Architechture
I went with MVP , since it is fair simple and quite powerful . The perfect balance between complex solution and lack of architecture at all. MVP has proven to suite most of my needs during the years.

## UI
Since I have a limited time , I've decided to keep things simple. Using a plain Material design provided by vanilla Android (which I find nice BTW). I don't use custom views nor Fragments for this task as they would rather add complexity than serve a real purpose.

## Frameworks
I didn't abuse the project with unnecessary dependencies , I've only used what I though is needed.

  - Dagger 2 
  - RxKotlin 2
  - Retrofit 2
  - Mockito
  - Espresso
  - Picasso

## Tests
Tests are important and there are many types of tests out there. My purpose is not to reach a full coverage but rather to give a taste of how I would approach testing in general.
There are Unit tests for Presenters that run on host JVM , and some UI tests that run on device/emulator.

## Installation
  - Clone the project and open it in Android Studio 3.0
  - Run the project on Emulator / Device with internet connection
  - To run tests I suggest to run them directly from the Android Studio by pressing a "play" button that appears near the test
  

This is how the app looks like once you run it and search for results.

[![ScreenShot](https://raw.githubusercontent.com/ivelius/AsanaRebelTask/master/screenshot1.png)](https://raw.githubusercontent.com/ivelius/AsanaRebelTask/master/screenshot1.png)
