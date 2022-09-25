package org.cuy.nugas.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @SequenceGenerator(name = "usersSeq", sequenceName = "users_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "usersSeq", strategy = GenerationType.SEQUENCE)
    public Long id;

    public String username;
    public String email;
    public String password;

    @OneToMany(mappedBy = "user")
    public List<TaskModel> taskList;

    @ManyToMany
    @JoinTable(name = "group_tasks_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_task_id"))
    public List<GroupTaskModel> groupTaskList;
}
