package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void addTicket(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.getAll()) {
            if (matches(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public boolean matches(Ticket ticket, String search, String search1) {
        if (ticket.getDeparture().contains(search)) {
            return true;
        }
        if (ticket.getArrival().contains(search1)) {
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] priceAscending() {
        Ticket[] result = repository.getAll();
        Arrays.sort(result);
        return result;
    }


//    public Ticket[] findAll(String from, String to) {
//        Ticket[] result = repository.getAll();
//        for (int i = 0; i < result.length; i++) {
//            result[i] = result[result.length - 1];
//        }
//        return result;
//    }
}
