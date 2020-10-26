# SBA3
User Operations
===============

Add User 
-------
<b>POST :</b> /api/users<br>
Body:{
	"userId": 1,
	"fname": "PQLXYS",
	"lName": "PQLXYS",
	"email": "a@b.com",
	"mobile": "0987654321"
}


Get Users List
--------------
<b>GET : /api/users </b>

Get User's Details
--------------
<b>GET : /api/users/{userId}<b>

Delete User
-------------
<b>DELETE : /api/users/{userId}</b>

Update User
-------------
<b>PUT :</b> /api/users<br>
Body:{
	"userId": 1,
	"fname": "PQLXYS",
	"lName": "PQLXYS",
	"email": "a@b.com",
	"mobile": "0987654321"
}

Add Interview to User
---------------------
<b>PUT :</b> /api/users/addInterview/{userId}/{interviewId}

Interview Operaions
====================

Get All Interviews
------------------
<b>GET : </b>/api/interviews

Add Interview
--------------
<b>POST : </b> /api/interviews/<br>
Body:{
		"interviewId": 2,
	  "interviewName": "Full Stack Java",
    "interviewer": "XYZ",
    "skills": "Java,Spring",
    "time": "21:16:33",
    "date": "2020-07-13",
    "status": "Confirmed",
    "remarks": "Urgent"
  }

Update Interview
--------------
<b>PUT : </b> /api/interviews/<br>
Body:{
		"interviewId": 2,
	  "interviewName": "Full Stack Java",
    "interviewer": "XYZ",
    "skills": "Java,Spring",
    "time": "21:16:33",
    "date": "2020-07-13",
    "status": "Confirmed",
    "remarks": "Urgent"
  }

Delete Interview
-------------
<b>DELETE : </b> /api/interviews/{interviewId}

Get Attendees
--------------
GET : /api/interviews/attendees/{interviewId}

Get Interviews Count
----------------------
GET : /api/interviews/count

Get Interviews By Name
----------------------
GET : /api/interviews/interview/{interviewName}

Get Interviews By Interviewer
----------------------
GET : /api/interviews/interviewer/{interviewerName}

Add User to Interview
---------------------
<b>PUT : </b> /api/interviews/addUsers/{interviewId}/{userId}

Remove User from Interview
--------------------------
<b>PUT : </b> /api/interviews/removeUsers/{interviewId}/{userId}

Update Interview Status
--------------------------
<b>PUT : </b> /api/interviews/updateStatus/{interviewId}/{status}




