# API Usage Documentation

## Usage

The Notes4U Server API follows a basic RESTful interface.

### Creating an Entity

An example of creating a Request

```
POST {request: {user_id: <user-id>, course_id: <user-id>, when: "Thu, 10 Nov 2016 22:45:10", location: "BA1200"}} to notes4u.herokuapp.com/requests
```

