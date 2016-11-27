curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"request":{"user_id": "1", "course_id": "1", "when": "Thu, 10 Nov 2016 22:45:10", "location": "BA100"}}' http://notes4u.herokuapp.com/requests

# API Usage Documentation

## Usage

### Creating an Entity

An example of creating a Request
```
POST {request: {user_id: <user-id>, course_id: <user-id>, when: "Thu, 10 Nov 2016 22:45:10", location: "BA1200"}} to notes4u.herokuapp.com/requests
```

`curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"request":{"user_id": "1", "course_id": "1", "when": "Thu, 10 Nov 2016 22:45:10", "location": "BA100"}}' http://notes4u.herokuapp.com/requests`


http://stackoverflow.com/questions/15797979/posting-to-ruby-on-rails-app-from-android
