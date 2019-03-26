clone the project and run tests by running the commands:
Prerequisites:
1. Have Appium with version >1.70 installed
2. For ios, an iphone 6 emulator running ios 11.0 is required
3. For android, an android emulator running Android 8.0 is required

Steps:
1) Start the appium server by running the command "appium"
2) To run tests, do one of the following
    a) for running tests on Android - "./gradlew cucumber -Ddevice=android"
    b) for running tests on IOS - "./gradlew cucumber -Ddevice=ios"
