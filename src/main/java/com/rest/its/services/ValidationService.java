package com.rest.its.services;

import java.util.ArrayList;
import java.util.List;

import com.rest.its.entities.Interview;
import com.rest.its.entities.User;

public class ValidationService {
	
	
	private List<String> basicval;

    public boolean validateUser(User user) {
        basicval = new ArrayList<>();
        if (null != user) {
            if (user.getUserId() == 0)
            	basicval.add("User Id is required and cannot be 0");

            if (null != user.getFirstName()) {
                int fNameLen = user.getFirstName().trim().length();
                if (fNameLen != 0) {
                    if (fNameLen < 5 || fNameLen > 30)
                    	basicval.add("First Name length should be between 5 to 30 Characters");
                } else
                	basicval.add("First Name cannot be blank.");
            } else
            	basicval.add("First Name is required.");

            if (null != user.getLastName()) {
                int lNameLen = user.getLastName().trim().length();
                if (lNameLen > 0) {
                    if (lNameLen < 3 || lNameLen > 25)
                    	basicval.add("Last name length should be between 3 to 25 Characters");
                } else
                	basicval.add("Last Name cannot be blank or empty");
            } else
            	basicval.add("Last Name is required.");


            if (null != user.getEmail()) {
                int emailLen = user.getEmail().trim().length();
                if (emailLen > 0) {
                    if (!user.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
                    	basicval.add("Email:" + user.getEmail() + " is invalid.");
                } else
                	basicval.add("Email Id cannot be blank.");
            } else
            	basicval.add("Email Id is required.");


            if (null != user.getMobile()) {
                if (!user.getMobile().matches("^\\d{10}$"))
                	basicval.add("Mobile number:" + user.getMobile() + " is invalid.");
            } else
            	basicval.add("Mobile number is required.");

        } else
        	basicval.add("User is required");

        return !(basicval.size() > 0);
    }

   
    
    public boolean validateInterview(Interview interview) {
    	basicval = new ArrayList<>();
        if (null != interview) {
            if (interview.getInterviewId() == 0)
            	basicval.add("Interview Id is required and cannot be 0");

            if (null != interview.getInterviewName()) {
                int interviewNameLen = interview.getInterviewName().trim().length();
                if (interviewNameLen != 0) {
                    if (interviewNameLen < 3 || interviewNameLen > 30)
                    	basicval.add("Interview Name length should be between 3 to 30 Characters");
                } else
                	basicval.add("Interview Name cannot be blank.");
            } else
            	basicval.add("Interview Name is required.");

            if (null != interview.getInterviewerName()) {
                int interviewerNameLen = interview.getInterviewerName().trim().length();
                if (interviewerNameLen > 0) {
                    if (interviewerNameLen < 5 || interviewerNameLen > 30)
                    	basicval.add("Interviewer name length should be between 5 to 30 Characters");
                } else
                	basicval.add("Interviewer Name cannot be blank.");
            } else
            	basicval.add("Interviewer Name is required.");

            if (null != interview.getUsersSkills()) {
                int skillsLen = interview.getUsersSkills().trim().length();
                if (skillsLen != 0) {
                    if (skillsLen < 5 || skillsLen > 30)
                    	basicval.add("Skills length should be between 5 to 30 characters.");
                } else
                	basicval.add("Skills Name cannot be blank.");
            } else
            	basicval.add("Skills Name is required.");

            if (null != interview.getInterviewStatus()) {
                int statusLen = interview.getInterviewStatus().trim().length();
                if (statusLen != 0) {
                    if (statusLen < 5 || statusLen > 100)
                    	basicval.add("Status length should be between 5 to 100 Chars.");
                } else
                	basicval.add("Status Name cannot be blank.");
            } else
            	basicval.add("Status Name is required.");

            if (null != interview.getRemarks()) {
                int remarksLen = interview.getRemarks().trim().length();
                if (remarksLen != 0) {
                    if (remarksLen < 5 || remarksLen > 100)
                    	basicval.add("Remarks length should be between 5 to 100 Chars.");
                } else
                	basicval.add("Remarks Name cannot be blank.");
            } else
            	basicval.add("Remarks Name is required.");
        } else
        	basicval.add("interview is required");

        return !(basicval.size() > 0);
    }

    public List<String> getErrors() {
        return basicval;
    }

}
