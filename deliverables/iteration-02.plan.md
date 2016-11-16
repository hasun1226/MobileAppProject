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

* To-do lists using Trello
   * These are prepared during the General Team meeting (every Wednesday on Google Hangout). We are using Trello boards to track tasks because Trello allows users to label cards by priority and easily move tasks between task lists. Overall we found that it was a lightweight, and flexible solution that didn't add too much overhead to our process.
   * We prioritize tasks using colour scheme - red, yellow, green depending on urgency.
      * Red (most urgent) usually indicates that a task is blocking another team member from making progress.
      * Yellow (medium urgency) indicates tasks that definitely need to get done by the next General Meeting.
      * Green (least urgent) usually indicate "nice-to-haves".
   * We found that we didn't need any further granularity within the three colours (i.e. adding a priority to each "red" card to see which task needs to be finished first) as we had enough team members to ensure that all high-priority tasks were taken on immediately.
   * During development, if any issues blocking progress are raised, that issue will be set with the highest priority and will be posted on each team's "To-do list" board in Trello. If there is an incomplete task at hand which was prioritized because it also hindered the team's progress, focus on the initial task and whoever finished their original task will deal with the newly raised issue (FIFO).
   * Front-end team's trello board: https://trello.com/b/K5NMotln/frontend-tasks
   * Back-end team's trello board: https://trello.com/b/fpJuuHce/backend-tasks
   
* [Meeting notes](https://github.com/csc301-fall-2016/project-team-19/tree/master/meeting_notes) prepared by James
  * Complement to the trello board that keeps track of questions asked and other discussions not represented in the trello board.
  * It also lets the team members to keep track of the changes in our priorities.

* Meeting Notes on Trello
  * We track the discussion points we have on each Weekly General Meeting in separate Trello boards.
    * https://trello.com/team19104

## Product

### Goals and tasks:

1. Implement a complete user journey for the note requester’s (a.k.a. Slacker’s) portion of the application.
   1. Deploy a back-end application.
   1. Implement the required entities for the user journey in the back-end.
   1. Split each page in the UI to be a separate task for the Front-end team:
      1. User Login
      1. Posting a Note Request
      1. Viewing Your Posted Requests
      1. Viewing a Note-Taker's Profile
    1. Link each page together.
    1. Add styling and graphics to the application to make it look nice.

2. Create a video demo that captures the core features of the application.
   1. Capture footage of Notes4U going through the primary user journey.
   2. Write a narrative script for the video.
   3. Edit the video to ensure the runtime is under 3 minutes, and add the narration and captions.


### Artifacts

* Databases for Users, Requests, Replies, Ratings on heroku
* Actual codes for Application pages that can be emulated on Android Studio.
  * Pages include pages for Login, Posting requests, Viewing their requests, Viewing the Note taker profiles.
* Video of the Note4U's primary user journey (a user logs in, requests notes for a certain class, and gets a response from a NoteTaker).
* Scripts of the video demo so that Videographers can make the video demo correspondingly.

