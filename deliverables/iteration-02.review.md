# Notes4U

## Iteration 02 - Review & Retrospect

 * When: November 14, 2016
 * Where: BA1200 in-person

## Process - Reflection

Takeaways after going into the actual development.

####Decisions turned out well:####

 1. Deciding that we should focus on implementing one user story first instead of the full system. We focused on implementing the Note requester's (a.k.a. Slacker's) portion of the application in order to include in Deliverable 2. Aside from the fact that the Slacker is the starting point of the application's usage, the development and task assignment were a lot easier by narrowing down the scope to the Slackers which led the team to complete the working prototype and the video on time.
 2. Having a weekly general and team-based meeting because it got the team members to work on the application by asking for progresses and working on the project together in-person. It also allowed the team to finally have a get-together and members got less awkward with each other. This decision is highly prioritized due to letting the team reach our goals on a consistent basis.
    * [Meeting notes](https://github.com/csc301-fall-2016/project-team-19/tree/master/meeting_notes)
    * [Oct. 26th General meeting](https://trello.com/b/DNriMWwi/iteration-3-meeting-notes)
    * [Nov. 2nd General meeting](https://trello.com/b/4HBqngjm/iteration-4-meeting-notes)
    * [Nov. 9th General meeting](https://trello.com/b/pA00rNHs/iteration-meeting-5)
 3. Each front-end member being assigned to a screen that was required for the Slacker's user story. After the database was launched, front-end team members could divide-and-conquer the works according to the screens. It saved a lot of time when trying to meet the goal without sacrificing the coherence of the application. This is prioritized after the back-end team's decision because front-end team's tasks were only possible after the back-end team's tasks for debugging purposes. See [Note4U responsibilities](https://github.com/csc301-fall-2016/project-team-19/tree/master/Notes4U) for how the tasks were divided.
 4. Having a separate Trello board to keep track of the tasks that needs to be done. This was especially helpful when the team was developing the products because a group of this size is supposedly hard to coordinate and monitor. However, this was not a big problem thanks to the Trello board keeping track of each teams' tasks and having each member to take charge of the task if nobody is in charge of it.
    * Front-end Team's To-do List: https://trello.com/b/K5NMotln/frontend-tasks
    * Back-end Team's To-do- List: https://trello.com/b/fpJuuHce/backend-tasks




####Decisions did not turn out as well as you hoped:####

 1. We set a deadline that is too tight for the front-end team, tasks were completed but the completion was delayed after the initial deadline. This is the most important decision that we need to worry about because it shows that our scheduling isn't working perfectly for the project that has a set deadline and it might risk the team to rush the whole project in the end. This can be checked in the [commit history](https://github.com/csc301-fall-2016/project-team-19/commits/master)
 2. We only followed the story line of Slackers and did not use the screen mockups as a reference. This lead to us missing a component of the tasks in the front-end team. The [menu page](https://github.com/csc301-fall-2016/project-team-19/blob/master/Notes4U/app/src/main/java/team19/notes4u/MainActivity.java) which is a transitory page for the application was not assigned to any member and the team realized that this screen was unimplemented only after we were linking the screens together. This will become more important in the coming weeks as more user stories are implemented, and each necessary screen should be detailed and assigned for the completion of the project.
 3. The method of coordinating team members that we decided to use is slightly inconsistent. We used only slack as to coordinate any team meetings. This lead to the inability to have a video making session. As a result, the videographers are reduced down to two members from initial four members but the members still had to work individually.
 4. Although back-end team can get the job done and members are learning the Ruby on rails language, the team is highly reliant on sole member when tasks get too complicated. This decision has the lowest value because a robust back-end is not required for the second deliverable.

####We are planning to make the following change(s) to our process:####

 1. We need to have a better coordination scheme moving into the hardest part of the development, especially when the school works will start to pour down on team members. This concern is also signaled by the first and third decision that did not turn out very well. Once we have a better scheduling and coordination schemes, many of the unnoticed failures can be mitigated. We will find a suitable application that helps us with coordinating meetings of members (i.e. doodle). 
 2. Continue with the divide-and-conquer system, but make sure that the list of tasks are complete through dynamic and thorough process. (i.e. In case the task list prepared at the beginning of the iteration, keep coming back to the task list to find if there are anything missing and assign them if any)
 
 
## Product - Review

####Goals/tasks that were met/completed:####

 1. Actual code for Application pages that can be emulated on Android Studio: [Notes4U](https://github.com/csc301-fall-2016/project-team-19/tree/master/Notes4U).
 2. Databases for Users, Requests, Replies on heroku: http://notes4u.herokuapp.com/
 3. [Video demo](https://github.com/csc301-fall-2016/project-team-19/blob/master/deliverables/videodemofinal.mp4).
 4. [Scripts](https://github.com/csc301-fall-2016/project-team-19/blob/master/artifacts/script.txt) of the video demo.
 5. [Notes4U logo](https://github.com/csc301-fall-2016/project-team-19/blob/master/Notes4U/app/src/main/res/drawable/logo2.png).

####Goals/tasks that were planned but not met/completed:####

 1. A page for [posting requests](https://github.com/csc301-fall-2016/project-team-19/blob/master/Notes4U/app/src/main/java/team19/notes4u/PostActivity.java) is not quite finished yet because of the connection error to the database. We are still trying to figure out what the problem is even after having four members of the team working on it.
 2. This was not part of the task in Iteration-plan, and thus the lower importance, but back-end team is trying to figure out how to make the routing for nested resources to work. This has turned out to be difficult because majority of the back-end team members are not quite proficient in Ruby on rails language. (i.e. [requests associated with user with id 1](http://notes4u.herokuapp.com/users/1/requests) is showing all the requests in the database)


####Going into the next iteration, our main insights are:####
 * Figure out the features that need to be implemented as to get the MVP done before the live demo date. Having a meeting on  renewing the to-do lists would be important. 
 * We need more effective and efficient ways to make decisions and to get results.
 * Increased responsibility on Front-end team, so less boundary between the team may be required.
