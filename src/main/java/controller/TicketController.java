package controller;

import model.Flight;
import model.Ticket;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repository.TicketRepository;
import service.AircraftService;
import service.FlightService;
import service.UserService;

import java.util.Iterator;

@Controller
public class TicketController {

    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private FlightService flightService;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private UserService userService;

    private String resultMessage;

    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String ticketHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            logger.info(authentication.getName());
            User user = userService.getByName(authentication.getName());
            model.addAttribute("ticketList", user.getTicketsList());
        }
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "buy/tickets";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public String ticketHomePost(@RequestParam(value = "idTicket", required = true) Integer idTicket, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = userService.getByName(authentication.getName());
            //user.getTicketsList().removeIf(ticket -> (ticket.getId().intValue() == idTicket.intValue())); не работает

            // user.getTicketsList().removeIf(ticket -> ticket.getId() == idTicket); тоже не работает. прикол.

            int cost=0;
            Iterator<Ticket> it = user.getTicketsList().iterator();
            while (it.hasNext()) {
                Ticket ticket = it.next();
                if (ticket.getId() == idTicket) {
                    cost = ticket.getAirPlace().getPrice();
                    it.remove();
                }
            }
            user.setBalance(user.getBalance()+ cost); //возможно необходимо запоминать стоимость покупки.
            flightService.deleteTicket(idTicket); //каскадно не удаляется почему- то
            if (!userService.saveCompleteObject(user))
                resultMessage = "delete ticket fail";
            else
                resultMessage = "delete ticket ok";
        }

        return "redirect:/tickets";
    }

    @RequestMapping(value = "/newTicket", method = RequestMethod.GET)
    public String newTicketHome(@RequestParam(value = "idFlight") Integer idFlight, Model model) {
        Flight flight = flightService.get(idFlight);
        model.addAttribute("message", resultMessage);
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        model.addAttribute("ticket", ticket);
        resultMessage = null;
        return "buy/newTicket";
    }

    @RequestMapping(value = "/newTicket", method = RequestMethod.POST)
    public String newTicketPost(@ModelAttribute Ticket ticket, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            logger.info(authentication.getName());
            User user = userService.getByName(authentication.getName());
            ticket.setAirPlace(aircraftService.getPlaceInfo(ticket.getAirPlace().getId()));
            if (ticket.getAirPlace().getCapacity() > 1) {
                ticket.getAirPlace().setCapacity(ticket.getAirPlace().getCapacity() - 1);
            } else {
                resultMessage = "no free place in this class";
                return "redirect:/main";
            }
            if (user.getBalance() >= ticket.getAirPlace().getPrice()) {
                user.setBalance(user.getBalance() - ticket.getAirPlace().getPrice());
                user.addTicket(ticket);
                userService.saveCompleteObject(user);
                flightService.insertTicket(ticket);
            } else
                resultMessage = "not enough balance";
        }
        return "redirect:/main";
    }

}
