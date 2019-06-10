# Kotlin Sprint Challenge

## Objectives

### Module 1 - Kotlin Part 1

- [x] write Kotlin functions
- [x] create Kotlin variables
- [x] use Kotlin String interpolation
- [x] use conditionals in Kotlin
- [x] create Kotlin classes
- [x] use Kotlin constants

### Module 2 - Kotlin Part 2

- [x] work with Lists in Kotlin
- [ ] use Loops in Kotlin
- [ ] work with Interfaces, Abstract Classes and Inherited Classes in Kotlin
- [x] use special types of classes in Kotlin
- [x] work with Pairs and Maps in Kotlin
- [x] use Kotlin Extension Functions

### Module 3 - Kotlin in Android

- [x] convert Java code to Kotlin
- [x] add Kotlin file to existing project
- [x] create a new app using Kotlin
- [x] use Interface callbacks with Kotlin
- [x] add the KotlinX Serialization library to an Android project
- [x] set up a class for Kotlin serialization
- [x] encode and decode JSON

### Module 4 - Kotlin Coroutines

- [ ] explain the advantage of Coroutines other Async patterns
- [x] set up app to work with coroutines
- [x] use annotations to ensure proper threading
- [x] prepare methods to be run on coroutines
- [x] spin up coroutine
- [x] move coroutine execution from one thread to another

## Requirements

Build an app which uses the spacetelescope api to retrieve videos and then build a view player to play them.

1. Use coroutines to execute network calls to the api
> The documentation can be found here [](http://hubblesite.org/api/documentation)
> Be sure to add the `android:usesCleartextTraffic="true"` attribute to the application tag in your manifest to work with http urls (as opposed to https)
> return pair from http request method
2. Use the serialization library to process the JSON response

> You can use the `@Optional` tag and `Json.nonstrict.parse` to ignore non-necessary Json properties.

> add extention function to video to get the desired video file url that is specified in a constant

3. Write an extension function to the VideoData class (the one with the array of video_files) to return a video_file object based on a predetermined value which is stored as a constant in the VideoData class
4. Add a `VideoView`, `SeekBar`, and `Button` to the layout and use them to play the video retrieved from the url
   1. Button - can play/pause the video
   2. VideoView - Video output
   3. SeekBar
      1. Follows progress of the video
      2. Secondary progress tracks the buffer progress
      3. User can tap and drag to seek to different locations
      4. Random change

> You can use the `kotlin-android-extensions` plugin that comes with your kotlin app to use the view ids as objects
