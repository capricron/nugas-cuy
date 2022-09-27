package org.cuy.nugas.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "group_tasks")
public class GroupTaskModel {

    @Id
    @SequenceGenerator(name = "groupTasksSeq", sequenceName = "group_tasks_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "groupTasksSeq", strategy = GenerationType.SEQUENCE)
    public Long id;

    @Column(name = "group_name")
    public String groupName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_tasks_users", joinColumns = @JoinColumn(name = "group_task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<UserModel> userList;

}
