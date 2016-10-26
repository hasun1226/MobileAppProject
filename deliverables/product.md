# Notes4U/Team19

#### Q1: What are you planning to build?

Notes4U is a mobile application that serves the needs for obtaining notes for a specified lecture when the student is unable to attend class. When a student inevitably misses a lecture they are left with a gap in their learning for that class and may fall behind. It is in their best interest to catch up and obtain the notes for the missed lecture. With Notes4U he/she can request help from another student to take the notes in place of the requester. 

A typical user journey on Notes4U would be:
1. Sign into the application
1. Submit a notetaking request for a class that you will be missing in the near future
1. Wait for notetakers to respond to the request, receive a notification
1. Choose a notetaker to attend the class in your place to take notes
1. Receive the notes in PDF format, and pay the notetaker accordingly
1. Give the notetaker a rating

It is important to note that Notes4U is not a replacement for course attendance/participation. Notes4U strictly concerns itself with written notes and not audio recordings.

** Workflow Diagram Here **

#### Q2: Who are your target users?

- UofT students aged 17+ who have to miss a class for various reasons (sickness, work, urgent deadlines, unforseen events)
- Students who are looking to make an extra bit of income on the side in their free time. (Note Takers)

- Personas: Jack is a 1st year student taking BIO120. He has no friends in the class, and he has lectures every Monday morning. Jack wants to go to a party Sunday night and he knows that he's probably not going to be in shape for class. He needs the lecture notes however because Professor Thompson refuses to post any of his notes. Jack hates the prof but he needs to pass the upcoming midterm and Thompson’s test are ridiculously hard (40 average like…...). He needs the notes but he has no friends in his class (he spent too much time partying with his dorm-mates.) He decides he needs someone to go to his class to take his notes for him.

#### Q3: Why would your users choose your product? What are they using today to solve their problem?

Students who get sick often or have obligations during lecture times will ask for notes and most of them are asking through discussion boards such as piazza. However, asking for a notes on piazza is very slow without incentives. Notes4U filters out the students who are available at a certain time slot and capable of taking understandable notes, making the process of offering and receiving the notes much faster. 

Many professors prefer to present their lectures through the blackboard or verbally, so if a student misses a class and has no one to borrow notes from, they lose an entire lecture's worth of information. 

Currently, if a student wants to exchange money for class notes, the only real option is to try to search for notes on a "note repository" like OneClass, where students who have taken a course will upload their old notes for a set price. However, this does not exactly solve our problem because of two reasons. Firstly, curriculums evolve and so even after a single semester, the notes may be outdated. Secondly, there is no way to request notes from a specific course, the user's options are at the mercy of whether or not a student has decided to upload a set of notes for their specific course. Notes4U allows the student to get a personalized set of notes for the particular lecture that they missed.

##### Product Decisions
- User profiles for Note Takers that show their course history, current Major, and other related information
    - This allows the person who wants to request a Note Taker to make an informed decision on who they want to be their Note Taker (e.g. a CS student may want to only choose Note Takers who are in the CS Major)
    - An alternative we thought about at first was to only allow students in the same class to be Note Takers for a certain class but we decided it was way too restrictive.
- Reputation system for Note Takers
    - One of our concerns was malicious behaviour on the Note Takers' end, and quality concerns.
    - Adding a Uber-esque reputation system theoretically alleviates these problems over time (malicious behaviour and bad note quality will cause a Note Taker's reputation to go down).

