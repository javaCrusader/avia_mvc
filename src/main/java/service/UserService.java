package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.IssueRepository;
import repository.RoleRepository;
import repository.TicketRepository;
import repository.UserRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean insert(User user, Set<Role> roles) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleList(roles);
        return userRepository.save(user) != null;
    }

    /**
     * @param user by default
     * @return true/false for sucsess matching
     */
    public boolean insert(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoleList(roles);
        return userRepository.save(user) != null;
    }

    public boolean saveCompleteObject(User user) {
        return userRepository.save(user) != null;
    }

    public boolean update(User user) {
        User old = userRepository.findByName(user.getName());
        if (old == null)
            return false;
        old.setFirstName(user.getFirstName());
        old.setLastName(user.getLastName());
        old.setAddress(user.getAddress());
        old.setEmail(user.getEmail());
        old.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(old) != null;
    }

    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    public List<Issue> getAllIssue () {
        return issueRepository.findAll();
    }


    public boolean addIssue(User user, Issue issue) {
        user.getIssueList().add(issue);
        return issueRepository.save(issue) != null;
    }
}

