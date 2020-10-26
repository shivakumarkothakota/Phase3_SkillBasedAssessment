package com.rest.its.controllers;

import com.rest.its.dto.InterviewDto;
import com.rest.its.dto.UserDto;
import com.rest.its.entities.Interview;
import com.rest.its.entities.User;
import com.rest.its.services.InterviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/its/interviews")
public class InterviewController {
    @Autowired
    InterviewService interviewService;
    
    
    
    // Allows to add interview
    @PostMapping({"", "/"})
    public Interview addInterview(@RequestBody Interview interview) {

        return interviewService.addInterview(interview);
    }
    
    // Allows to delete an existing interview
    @DeleteMapping({"/{id}"})
    public void deleteInterview(@PathVariable(value = "id") int id) {
        interviewService.deleteInterview(id);
    }
    
    // Allows to update interview status on go
    @PutMapping("/updateStatus/{interviewId}/{interviewStatus}")
    public Interview updateInterviewStatus(@PathVariable(value = "interviewId") int interviewId, @PathVariable(value = "interviewStatus") String interviewStatus) {
        return interviewService.updateInterviewStatus(interviewId,interviewStatus);
    }

    // Allows to search the interview on the basis of interview name
    @GetMapping("/interview/{name}")
    public List<InterviewDto> getInterviewByName(@PathVariable(value = "name") String name) {
        return interviewService.findAllByInterviewName(name);
    }

    // Allows to search the interview on the basis of interviewer name
    @GetMapping("/interviewer/{name}")
    public List<InterviewDto> getInterviewBy(@PathVariable(value = "name") String interviewerName) {
        return interviewService.findAllByInterviewerName(interviewerName);
    }

    
    // Allows searching total no of count of interviews
    @GetMapping("/count")
    public int getInterviewCount() {
        return interviewService.getInterviewsCount();
    }
    
    
    // Allows to display all the interviews
    @GetMapping({"", "/"})
    public List<Interview> getInterviews() {
        return interviewService.getInterviews();
    }

    // Allows to add an attendee to an interview    
    @PutMapping({"/addUsers/{interviewId}/{userId}"})
    public Interview addUsers(@PathVariable(value = "interviewId") int interviewId, @PathVariable(value = "userId") int userId) {
        return interviewService.addcandidateToInterview(interviewId, userId);
    }
    
    
    // Allows to display all the attendees of an interview
    @GetMapping("/attendees/{interviewId}")
    public Set<UserDto> getAttendees(@PathVariable(value = "interviewId") int interviewId) {
        return interviewService.findByInterviewId(interviewId).getUsers();
    }
    
    
   /////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    @GetMapping("/{id}")
    public InterviewDto getInterviewById(@PathVariable(value = "id") int id) {
        return interviewService.findByInterviewId(id);
    }    

    @PutMapping({"/removeUsers/{interviewId}/{userId}"})
    public Interview removeUsers(@PathVariable(value = "interviewId") int interviewId, @PathVariable(value = "userId") int userId) {
        return interviewService.removeUsersFromInterview(interviewId, userId);
    }

    @PutMapping({"", "/"})
    public Interview updateInterview(@RequestBody Interview interview) {
        return interviewService.updateInterview(interview);
    }    

}

