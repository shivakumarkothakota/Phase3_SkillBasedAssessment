package com.rest.its.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "interview")
public class Interview {

    @Id
    @NotNull
    private int interviewId;

    @NotNull
    @Column(name = "title")
    private String interviewName;

    @NotNull
    @Column(name = "interviewer")
    private String interviewerName;

    @NotNull
    @Column(name = "skills")
    private String usersSkills;

    @Column(name = "start_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name = "status")
    private String interviewStatus;

    @NotNull
    @Column(name = "remarks")
    private String remarks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "interview_user",
            joinColumns = {@JoinColumn(name = "interview_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users;

    public Interview() {
    }

    

    public int getInterviewId() {
		return interviewId;
	}



	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}



	public String getInterviewName() {
		return interviewName;
	}



	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}



	public String getInterviewerName() {
		return interviewerName;
	}



	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}



	public String getUsersSkills() {
		return usersSkills;
	}



	public void setUsersSkills(String usersSkills) {
		this.usersSkills = usersSkills;
	}



	public LocalTime getTime() {
		return time;
	}



	public void setTime(LocalTime time) {
		this.time = time;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public String getInterviewStatus() {
		return interviewStatus;
	}



	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUsers(List<User> users) {
        this.getUsers().addAll(users);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return interviewId == interview.interviewId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewId);
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewName='" + interviewName + '\'' +
                ", interviewer='" + interviewerName + '\'' +
                ", skills='" + usersSkills + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", status='" + interviewStatus + '\'' +
                ", remarks='" + remarks + '\'' +
                ", users=" + users +
                '}';
    }

    public void setInterview(Interview interview) {
        this.setRemarks(interview.getRemarks());
        this.setInterviewName(interview.getInterviewName());
        this.setInterviewerName(interview.getInterviewerName());
        this.setDate(interview.getDate());
        this.setTime(interview.getTime());
        this.setUsersSkills(interview.getUsersSkills());
        this.setInterviewStatus(interview.getInterviewStatus());
    }
}
