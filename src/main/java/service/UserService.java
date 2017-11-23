package service;

import model.Issue;
import model.Role;
import model.Ticket;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.IssueRepository;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {


    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * @param user by default
     * @return true/false for sucsess matching
     */
    public boolean insert(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        roles.add(roleRepository.findByName("ROLE_ADMIN"));
        user.setRoleList(roles);
        return userRepository.save(user) != null;
    }

    public boolean insertPwdEncode(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
        old.setCreditCard(user.getCreditCard());
        return userRepository.save(old) != null;
    }

    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    public User get(Integer id) {
        return userRepository.findOne(id);
    }

    public boolean delete(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
           if ( this.getByName(authentication.getName()).getId() == id )
               return false;
            User old = userRepository.findOne(id);
            if (old == null)
                return false;
            if (old.getTicketsList() != null) {
                for (Ticket ticket : old.getTicketsList()) {
                    ticket.getAirPlace().setCapacity(ticket.getAirPlace().getCapacity()+1);
                    aircraftService.savePlaceInfo(ticket.getAirPlace());
                }
            }
            userRepository.delete(id);
        }
        return true;
    }

    public List<User> getAll() {
        return userRepository.findAllByOrderByIdAsc();
    }


    public List<Issue> getAllIssue() {
        return issueRepository.findAll();
    }

    public Issue getIssue(Integer idIssue) {
        return issueRepository.findOne(idIssue);
    }

    public boolean saveIssue(Issue issue) {
        return issueRepository.save(issue) != null;
    }

}

