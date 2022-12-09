# Just_in_Case
Please note that front end code is in branch frontend, while back end code is in main.

Just In Case User Manual

## 1.0 General Information
Just in Case is an application meant to help CWRU students plan for their day in advance by allowing them to post and receive updates on what’s happening on Campus. 

## 1.1 System Overview
The Just In Case Application is divided into two main sections, the Live Feed and the Building Hub. It opens up with a login page for CWRU users or admin, taking in their case email in the format abc123@case.edu and a password from the user. 


## 1.2 Access and run instructions:

Running application:
First, on backend branch (main branch), using the terminal:
run `mvn clean install`
run `mvn sprint-boot:run`

Then, on frontend branch, using the terminal:
run `npm install`
run `npm run serve`

Then, use Google Chrome to navigate to `localhost:____`

*To access the website as admin, you can use the following credentials:*

Email: srd119@case.edu

Password: admin!23

*To access as non-admin, use the following credentials:*

Email: abc123@case.edu

Password: pass!23


## 1.3 User Functionality

### 1.3.1 User Registration:
Upon entering their case e-mail and password, if the user does not have an account, they will be prompted to create one and add a username to their account. With these credentials, they can log into the page as a user.

### 1.3.2 Application setup:
Upon logging in, users will see a split screen containing the Live Feed on the left and the Building Hub on the right. Users also have the option to log out by pressing a button on the top right side of the screen labeled “Log Out”

### 1.3.3 Live Feed:
The live feed contains posts made by users in the format:
``` New Alert!
Reports of “POST_TYPE” at “LOCATION"
		Posted by: User ID or Anonymous
		at MM/DD/YYY @00:00 
```

This way, each live feed post contains information about the location being reported, the type of the post, such as “EXCESSIVE_RAIN” or “SNOW” or “SUSPICIOUS_BEHAVIOR”, the user who posted it (although at the moment all user posts are set to be anonymous) and the date and time of the post. 

As users scroll down their Live Feed, they can see what posts have been made by previous users, as they are ranked in order from most to least recent. Additionally, at the bottom left corner, users can see a plus sign circle icon which, when selected, pops up a screen allowing users to make a new post.

### 1.3.4 Making a new post:
When users select the button to make a new post, a pop-up screen appears. The screen prompts the user to make a new post, by selecting the post type and the location from drop-down menus provided. The Post Type options currently include: “There is excessive rain on”, “There is excessive snow on”, “It is crowded on”, and “The following location is closed”.  The location options include: “Adelbert Hall”, “Adelbert Gym”, “Wolstein Hall”, “Amasa Stone Chapel”, “Art Studio”, “Bookstore”, “Bellflower Hall”, and “Bingham”. The pop-up also contains a checkbox asking the user if they would like to post anonymously. At the bottom right corner, the pop-up contains a Submit button so that users can make their post. If they wish to cancel, they can click the “x” mark provided in the top right corner to close the pop-up. 

### 1.3.5 Building Hub:
The Building Hub can be seen on the right side of the page. It allows users to learn more information about the buildings on campus and check the status of their facilities. By clicking on one of the buildings in the building hub, users can see the information about the building pop up on the left side of the screen, replacing the live feed. To go back to the live feed after selecting a building, users can simply click on Live Feed at the top and they will go back to seeing their live feed. 

## 1.4 Admin Functionality
### 1.4.1 Admin Registration: 
Similarly to users, Administrators log in by entering their case email and password into the log in screen. With these credentials, they can log into the page. 

### 1.4.2 Admin Building Hub Control:
Administrators can choose to add or edit the buildings that are in the Building Hub. When admins log in, their home page shows two buttons at the bottom right hand corner of the screen: “Add Building” and “Edit Building”.
#### 1.4.2.1 Add Building: 
Selecting the add building button prompts a pop-up to appear on the screen, showing two input options that the administrator uses to add a building. The building requires the building name, such as Nord or Yost, and then a description of the building. Administrators should keep in mind that building descriptions cannot exceed 500 characters. At the bottom right corner, the pop-up contains a Submit button so that admins can add the building to the Building Hub. If they wish to cancel, they can click the “x” mark provided in the top right corner to close the pop-up. 

#### 1.4.2.2 Edit/Remove Building:  
Edit building currently only allows administrators to select a building from the Building Hub list to remove. Selecting the edit building button prompts a pop-up to appear on the screen. The popup asks the administrator to select the buildings they’d like to remove, followed by a list of all the building names accompanied by checkmarks. When the administrator selects which buildings they want to remove, they can then select the Submit button and it successfully removes the building. If they don’t wish to remove anything, they can select the “x” mark above to close the popup.

## 2.0 UI Snapshots
### 2.1 Login snapshots

#### 2.1.1 Blank login page:

<img width="300" alt="LogIn" src="https://user-images.githubusercontent.com/102556053/206800668-7ad184cf-de95-4ce0-b061-3d6855d6b2d3.png">

#### 2.1.2 Create user page:

<img width="300" alt="Create User" src="https://user-images.githubusercontent.com/102556053/206800804-bb9688d2-11e1-473d-a0e6-08bd47bb1b41.png">

#### 2.1.3 Log in page:

<img width="300" alt="Login" src="https://user-images.githubusercontent.com/102556053/206800837-685fd4e5-0a27-4c0e-bac4-ef4ec3c95208.png">

#### 2.1.4 Log in Invalid account error (does not exist)

<img width="300" alt="Screen Shot 2022-12-09 at 4 57 02 PM" src="https://user-images.githubusercontent.com/102556053/206802333-1413ebbc-27e5-4b78-b2c3-517519019737.png">

#### 2.1.4 Log in Invalid e-mail error (not a CWRU email)

<img width="300" alt="Screen Shot 2022-12-09 at 4 57 35 PM" src="https://user-images.githubusercontent.com/102556053/206802384-ef727197-d6de-465f-b838-5c7dc2bb7271.png">


### 2.2 User page snapshots

#### 2.2.1 Main page snapshot

<img width="300" alt="Screen Shot 2022-12-09 at 4 53 44 PM" src="https://user-images.githubusercontent.com/102556053/206801975-5b529dbc-c7a3-469e-bcc1-e288a4efaf19.png">

#### 2.2.2 New Post Pop-up

<img width="300" alt="Screen Shot 2022-12-09 at 4 48 19 PM" src="https://user-images.githubusercontent.com/102556053/206801340-a3de9536-d54f-49bd-8127-2934eccec8aa.png">

### 2.3 Admin Snapshots

#### 2.3.1 Admin Main Page Live Feed View

<img width="300" alt="Screen Shot 2022-12-09 at 4 46 45 PM" src="https://user-images.githubusercontent.com/102556053/206801164-98103de5-48ec-48f2-914e-2069dfe20c23.png">

#### 2.3.2 Admin Main Page Building Hub View
<img width="300" alt="Screen Shot 2022-12-09 at 4 50 31 PM" src="https://user-images.githubusercontent.com/102556053/206801616-1b1de916-e879-4bf3-af85-22ed069c981d.png">

#### 2.3.3 Add Building Pop-up
<img width="300" alt="Screen Shot 2022-12-09 at 4 49 06 PM" src="https://user-images.githubusercontent.com/102556053/206801451-5cc717b1-6564-43bf-be5b-45c82b596d8d.png">

#### 2.3.4 Remove Building Pop-up
<img width="300" alt="Screen Shot 2022-12-09 at 4 51 26 PM" src="https://user-images.githubusercontent.com/102556053/206801724-035550d1-a911-47e9-82bf-20594599c509.png">

## 3.0 Relevant documentation:
### 3.0.1 Software Requirements Specifications

[Software Requirements Specification.pdf](https://github.com/SylvieDyer/Just_in_Case/files/10198454/Software.Requirements.Specification.pdf)

### 3.0.2 Software Design Document

[Software Design Document- Just In Case-2.pdf](https://github.com/SylvieDyer/Just_in_Case/files/10198456/Software.Design.Document-.Just.In.Case-2.pdf)

### 3.0.3 Functional Testing Document

[Just_in_Case_Functional_Testing_Document.pdf](https://github.com/SylvieDyer/Just_in_Case/files/10198457/Just_in_Case_Functional_Testing_Document.pdf)
