# Java Shapes Drawing Application

## Project Description
This is a Java application that allows drawing and manipulating geometric shapes (including circles) using JavaFX for the graphical interface.

## Prerequisites
- Java JDK 11 or newer
- JavaFX SDK (version 17 or newer recommended)

## Setup Instructions

### 1. Install JavaFX
Download JavaFX SDK from: https://gluonhq.com/products/javafx/

### 2. Add JavaFX to your project
There are two ways to add JavaFX:

#### Option A: Using IDE (Eclipse/IntelliJ)
1. Right-click on your project
2. Go to Build Path → Configure Build Path
3. Click on Libraries tab
4. Click "Add External JARs"
5. Navigate to your JavaFX SDK's lib folder
6. Select all .jar files

#### Option B: Using Maven
Add these dependencies to your `pom.xml`:
```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>17.0.2</version>
</dependency>
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-graphics</artifactId>
    <version>17.0.2</version>
</dependency>
```

### 3. Run Configuration
When running the application, add this VM argument:
```
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.graphics
```
Replace `/path/to/javafx-sdk` with your actual JavaFX SDK path.

## Features
- Create and draw figures
- Modify circle properties (color, size)
- Move shapes
- Rotate shapes
- Scale shapes

