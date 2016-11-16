# Notes4U

## Iteration 02

 * Start date: Friday, November 4, 2016
 * End date: Monday, November 14, 2016

## Process

**Important Note:** The Github repository for the back-end server is currently hosted on https://github.com/eddiequan/notes4u_server. We separated the back-end code from the rest of the repository to make deployments to Heroku easier.

#### Roles & responsibilities

* UI Designer: Muser
   * Designs the structure of user interface and presents it to the developers (via mockups or verbally).
   * Designs the graphics for the application such as the logo or background images.

* Back-end Developers: Eddie, Kyle, Sunny
   * Develops the back-end API which the front-end Android client will rely on for data fetching and persistence.
   * Deploys and maintains the back-end application.
   * Implements custom endpoints that the front-end client requires (e.g. calculating the average rating for a NoteTaker).
   
* Front-end Developers: Charga, James, Jimmy, Muser, Tyler
   * Implements the workflows specified by the UI designer for our Android front-end.
   * Adds styling and markup to the front-end application to make it conform to the UI designer's specifications.
   
* Videographers: Muser, Sunny
   * Creates a video demo with the working prototype of our application.
   * Creates an outline for the video structure.
   * Drafts and proofreads the script for the video so that our complete user journey is showcased in a clear and concise fashion.
   
* Notetaker: James
  * Takes meeting minutes during meetings.
  
* Meeting Facilitator: Kyle, Charga
  * Facilitates meetings, ensures that all the questions we had going into the meeting are either resolved or can be actioned upon.
  
* Written Work Editors: Sunny, James
    * Proofreads written deliverables.

#### Events

* "General Team Meeting" every Wednesday 11PM on Google Hangout
   * Discuss weekly progresses between the teams; front-end, back-end, UI designer. It allows the members to hold accountability to fulfilling their respective tasks.
   * Discuss the newly emerged tasks during the process or the tasks that are complete.

* "Back-end team meeting" every Wednesday 5PM in-person
   * Develop the databases for the Application, and while doing so, many of the back-end team members who are not familiar with the Ruby language can learn the language together.
   * After some discussion, the team members can go to the TA's office hour (7PM-8PM) if there are any questions that need to be answered.

* "Front-end team meeting" every Friday 6PM in-person
  * Develop the pages for the Application and link the pages with each other as each team member is responsible for each page.

* "Video making session"
   * Make a video that captures the Application process from user login to requesting for notes.

#### Artifacts

* To-do list using Trello
   * Prepared during the General Team meeting (every Wednesday on Google Hangout). Using Trello board because it has functions to set the priorities with the color and can easily move around the tasks from the to-do category to completed category and vice versa.
   * We would have prioritized the tasks over the urgency of the task (determined by whether or not the task prevents the team's progress), then the value of the task (aligned with the Product Goals). However, after laying out all the tasks with the highest urgency, we didn't have to compare the tasks with the same value because we had enough members to take on the tasks with the same urgency. 
   * During the development, if any issues blocking the progress are raised, that issue will be set with the highest priority (because of the urgency) and will be posted on each team's "To-do list" board in Trello. If there is an incomplete task at hand which was prioritized because it also hindered the team's progress, focus on the initial task and whoever finished their original task will deal with the newly raised issue (FIFO).
   * Front-end team's trello board: https://trello.com/b/K5NMotln/frontend-tasks
   * Back-end team's trello board: https://trello.com/b/fpJuuHce/backend-tasks
   
* [Meeting notes](https://github.com/csc301-fall-2016/project-team-19/tree/master/meeting_notes) prepared by James
  * Complement to the trello board that keeps track of questions asked and other discussions not represented in the trello board.
  * It also lets the team members to keep track of the changes in our priorities.

## Product

### Goals and tasks:

1. Implement a complete user journey for the Note requester’s (a.k.a. Slacker’s) portion of the Application.
   1. Now that we have the User Interface laid out, deploying the database server so that it can be accessible to the team is the top-priority because the front-end team cannot test their codes without the working database.
   2. Each of the front-end team members implements each page of the application: user login, posting a request for note, viewing the slacker's requests, and viewing the Not takers' profiles.
   3. Link individual pages with each other after all the pages are implemented.
   4. Having a logo and background image for the Application is solely for the aesthetics of the Application, so it has the lowest priority.

2. Make a video demo that captures the core features of the Application.
   1. Capture the necessary screens of the Application to present in the video so that the videographers can edit and make a script depending on the screenshots.
   2. Write a narrative script and make sure it fits into 3 minutes.
   3. Edit the video to make the video run in 3 minutes, and add the narrations and subtitles.


### Artifacts

* Databases for Users, Requests, Replies, Ratings on heroku
* Actual codes for Application pages that can be emulated on Android Studio.
  * Pages include pages for Login, Posting requests, Viewing their requests, Viewing the Note taker profiles.
* Video of the Application's functionalities which is to be submitted for Deliverable 2.
* Scripts of the video demo so that Videographers can make the video demo correspondingly.

