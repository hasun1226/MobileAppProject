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

* "General Team Meeting" every Wednesday 11PM on Google Hangouts
   * We have a weekly meeting on Google Hangouts to discuss each teams' progress: front-end, back-end, UI designer. This meeting allows the team to hold each other accountable to fulfilling their respective tasks, and to determine whether or not we are on track to completion.
   * We also discuss any newly emerged tasks we've uncovered and try to resolve any questions we have had during the iteration.

* "Back-end Team Meeting" every Wednesday 5PM in-person
   * The back-end team meets weekly to implement functionality for Note4U's back-end API. The reason why the team meets as a group is so that the team members who are not as familiar with Ruby on Rails can learn the framework together.

* "Front-end Team Meeting" every Friday 6PM in-person
  * The front-end team meets weekly to implement pages specified by the UI Designer.
  * Furthermore, the team meets in-person to coordinate the linking of each page to each other since each member of the front-end team is responsible for a separate page.

* "Video-making Session" Sunday Nov. 13, 2016
   * Sunny and Muser met to work on the video demo for the second deliverable.

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

