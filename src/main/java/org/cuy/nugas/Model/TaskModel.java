package org.cuy.nugas.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @SequenceGenerator(name = "tasksSeq", sequenceName = "tasks_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "tasksSeq", strategy = GenerationType.SEQUENCE)
    public Long id;

    public String name;
    public String note;
    public Date date;
    public String time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserModel user;

    @JsonIgnore
    public UserModel getUser() {
        return user;
    }

}
