package controller;

import model.AircraftPlaceInfo;
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
import service.AircraftService;
import service.FlightService;
import service.UserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        Ticket ticket = flightService.getTicket(idTicket);
        if (ticket == null) {
            resultMessage = "delete ticket fail";
            return "redirect:/tickets";
        }
        LocalDateTime curDate = LocalDateTime.now();
        long diff = ChronoUnit.DAYS.between(ticket.getFlight().getStart(), curDate);
        if (diff < 1) {
            resultMessage = "sorry. too late. less then 1 day remains to flight";
            return "redirect:/tickets";
        }

        User user = ticket.getUser();
        user.removeTicket(idTicket);
        user.setBalance(user.getBalance() + ticket.getFactCost());

        AircraftPlaceInfo place = ticket.getAirPlace().setCapacity(ticket.getAirPlace().getCapacity() + 1);
        place.removeTicket(idTicket);

        Flight flight = ticket.getFlight();
        flight.removeTicket(idTicket);

        if (flightService.insert(flight) && userService.saveCompleteObject(user) && aircraftService.savePlaceInfo(place)) {
            resultMessage = "delete ticket ok";
        } else
            resultMessage = "delete ticket fail";


        return "redirect:/tickets";
    }

    @RequestMapping(value = "/newTicket", method = RequestMethod.GET)
    public String newTicketHome(@RequestParam(value = "idFlight") Integer idFlight, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Flight flight = flightService.get(idFlight);
            model.addAttribute("message", resultMessage);
            Ticket ticket = new Ticket();
            ticket.setFlight(flight);
            model.addAttribute("ticket", ticket);
            model.addAttribute("user", userService.getByName(authentication.getName()));
            resultMessage = null;
            return "buy/newTicket";
        }
        return "redirect:/main";
    }


    @RequestMapping(value = "/newTicket", method = RequestMethod.POST)
    public String newTicketPost(@ModelAttribute Ticket ticket, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = userService.getByName(authentication.getName());
            AircraftPlaceInfo place = aircraftService.getPlaceInfo(ticket.getAirPlace().getId());
            Flight flight = flightService.get(ticket.getFlight().getId());
            if (place.getCapacity() > 1) {
                place.setCapacity(place.getCapacity() - 1);
            } else {
                resultMessage = "no free place in this class";
                return "redirect:/main";
            }
            if (user.getCreditCard() != null) {
                ticket.setFactCost(place.getPrice());
                if (flightService.persistTicket(ticket, flight, user, place))
                    resultMessage = "ticket addition ok";
                else
                    resultMessage = "ticket addition fail";

            } else
                resultMessage = "you must add billing method";
        }
        return "redirect:/main";
    }
}