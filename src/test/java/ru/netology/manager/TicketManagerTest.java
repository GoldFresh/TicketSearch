package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

public class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    Ticket ticket1 = new Ticket(1, 16_000, "SVO", "KZN", 95);
    Ticket ticket2 = new Ticket(2, 14_000, "AER", "IST", 145);
    Ticket ticket3 = new Ticket(3, 12_000, "DME", "LED", 85);
    Ticket ticket4 = new Ticket(4, 18_000, "SVO", "KZN", 105);
    Ticket ticket5 = new Ticket(5, 20_000, "VKO", "LED", 95);
    Ticket ticket6 = new Ticket(6, 22_000, "SVO", "KZN", 125);

    @BeforeEach
    public void setup() {
        manager.addTicket(ticket1);
        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket5);
        manager.addTicket(ticket6);
    }

    @Test
    public void shouldSave() {
        Ticket ticket7 = new Ticket(7, 15_000, "AAA", "AAE", 120);
        repository.save(ticket7);

        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7};
        Ticket[] actual = repository.getAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllValid() {
        Ticket[] expected = new Ticket[]{ticket1, ticket4, ticket6};
        Ticket[] actual = manager.findAll("SVO", "KZN");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findValid(){
        Ticket[] expected = new Ticket[]{ticket2};
        Ticket[] actual = manager.findAll("AER", "IST");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findNothing(){
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.findAll("AAA", "AAE");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowTicketsInAscendingOrder() {
        Ticket[] expected = new Ticket[]{ticket3, ticket2, ticket1, ticket4, ticket5, ticket6};
        Ticket[] actual = manager.priceAscending();

        Assertions.assertArrayEquals(expected, actual);
    }
}
