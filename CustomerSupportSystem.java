import java.util.*;

/* --- Ticket ADT --- */
class Ticket {
    int ticketID;
    String customerName;
    String issueDescription;
    int priority; // 1 = urgent, 2 = normal
    Ticket next;

    public Ticket(int ticketID, String customerName, String issueDescription, int priority) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        this.next = null;
    }
}

/* --- Singly Linked List --- */
class TicketList {
    Ticket head;

    // Insert ticket at end
    public void insertTicket(Ticket newTicket) {
        if (head == null) {
            head = newTicket;
        } else {
            Ticket temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newTicket;
        }
        System.out.println("Ticket inserted successfully!");
    }

    // Delete ticket by ID
    public Ticket deleteTicket(int ticketID) {
        if (head == null) return null;

        if (head.ticketID == ticketID) {
            Ticket temp = head;
            head = head.next;
            return temp;
        }

        Ticket prev = null, curr = head;
        while (curr != null && curr.ticketID != ticketID) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) return null;
        prev.next = curr.next;
        return curr;
    }

    // Retrieve ticket by ID
    public Ticket retrieveTicket(int ticketID) {
        Ticket temp = head;
        while (temp != null) {
            if (temp.ticketID == ticketID) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Print all tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        Ticket temp = head;
        System.out.println("Current Ticket List:");
        while (temp != null) {
            System.out.println("ID: " + temp.ticketID + " | Name: " + temp.customerName +
                    " | Priority: " + temp.priority + " | Issue: " + temp.issueDescription);
            temp = temp.next;
        }
    }
}

/* --- Stack for Undo Operations --- */
class UndoStack {
    Stack<Ticket> stack = new Stack<>();

    public void push(Ticket ticket) {
        stack.push(ticket);
    }

    public Ticket pop() {
        if (stack.isEmpty()) return null;
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

/* --- Priority Queue (Urgent Tickets First) --- */
class TicketPriorityQueue {
    PriorityQueue<Ticket> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.priority));

    public void addTicket(Ticket t) {
        pq.add(t);
    }

    public void processTickets() {
        System.out.println("Processing tickets by priority:");
        while (!pq.isEmpty()) {
            Ticket t = pq.poll();
            System.out.println("Processing Ticket ID: " + t.ticketID +
                    " | Priority: " + t.priority + " | Issue: " + t.issueDescription);
        }
    }
}

/* --- Circular Queue (Round-Robin) --- */
class CircularQueue {
    private int size, front, rear, count;
    private Ticket[] queue;

    public CircularQueue(int size) {
        this.size = size;
        this.queue = new Ticket[size];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public void enqueue(Ticket t) {
        if (isFull()) {
            System.out.println("Queue is full!");
            return;
        }
        rear = (rear + 1) % size;
        queue[rear] = t;
        count++;
    }

    public Ticket dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        Ticket t = queue[front];
        front = (front + 1) % size;
        count--;
        return t;
    }

    public boolean isFull() {
        return count == size;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void processRoundRobin() {
        System.out.println("Round-Robin Ticket Processing:");
        while (!isEmpty()) {
            Ticket t = dequeue();
            System.out.println("Processing Ticket ID: " + t.ticketID + " | Customer: " + t.customerName);
        }
    }
}

/* --- Polynomial Linked List (Billing History) --- */
class Term {
    int coeff, exp;
    Term next;
    Term(int c, int e) { coeff = c; exp = e; next = null; }
}

class Polynomial {
    Term head;

    public void insertTerm(int coeff, int exp) {
        Term newTerm = new Term(coeff, exp);
        if (head == null) head = newTerm;
        else {
            Term temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newTerm;
        }
    }

    // Compare two billing histories (polynomials)
    public boolean compare(Polynomial p2) {
        Term t1 = head, t2 = p2.head;
        while (t1 != null && t2 != null) {
            if (t1.coeff != t2.coeff || t1.exp != t2.exp)
                return false;
            t1 = t1.next;
            t2 = t2.next;
        }
        return (t1 == null && t2 == null);
    }
}

/* --- Main Customer Support System --- */
public class CustomerSupportSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketList ticketList = new TicketList();
        UndoStack undoStack = new UndoStack();
        TicketPriorityQueue pq = new TicketPriorityQueue();
        CircularQueue cq = new CircularQueue(5);

        while (true) {
            System.out.println("\n=== CUSTOMER SUPPORT SYSTEM MENU ===");
            System.out.println("1. Add Ticket");
            System.out.println("2. Delete Ticket");
            System.out.println("3. Retrieve Ticket");
            System.out.println("4. Undo Last Operation");
            System.out.println("5. Process Priority Tickets");
            System.out.println("6. Round Robin Processing");
            System.out.println("7. Compare Billing Records");
            System.out.println("8. Display All Tickets");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Issue Description: ");
                    String issue = sc.nextLine();
                    System.out.print("Enter Priority (1=Urgent, 2=Normal): ");
                    int pr = sc.nextInt();
                    Ticket t = new Ticket(id, name, issue, pr);
                    ticketList.insertTicket(t);
                    undoStack.push(t);
                    pq.addTicket(t);
                    cq.enqueue(t);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to delete: ");
                    int delId = sc.nextInt();
                    Ticket deleted = ticketList.deleteTicket(delId);
                    if (deleted != null) {
                        System.out.println("Deleted Ticket ID: " + deleted.ticketID);
                        undoStack.push(deleted);
                    } else System.out.println("Ticket not found!");
                    break;

                case 3:
                    System.out.print("Enter Ticket ID to retrieve: ");
                    int rid = sc.nextInt();
                    Ticket found = ticketList.retrieveTicket(rid);
                    if (found != null)
                        System.out.println("Ticket Found: " + found.customerName + " | Issue: " + found.issueDescription);
                    else
                        System.out.println("Ticket not found!");
                    break;

                case 4:
                    if (!undoStack.isEmpty()) {
                        Ticket last = undoStack.pop();
                        ticketList.deleteTicket(last.ticketID);
                        System.out.println("Undo successful for Ticket ID: " + last.ticketID);
                    } else System.out.println("No operations to undo.");
                    break;

                case 5:
                    pq.processTickets();
                    break;

                case 6:
                    cq.processRoundRobin();
                    break;

                case 7:
                    Polynomial p1 = new Polynomial();
                    Polynomial p2 = new Polynomial();
                    System.out.println("Enter first billing record (3 terms):");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Coeff: "); int c = sc.nextInt();
                        System.out.print("Exp: "); int e = sc.nextInt();
                        p1.insertTerm(c, e);
                    }
                    System.out.println("Enter second billing record (3 terms):");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Coeff: "); int c = sc.nextInt();
                        System.out.print("Exp: "); int e = sc.nextInt();
                        p2.insertTerm(c, e);
                    }
                    if (p1.compare(p2))
                        System.out.println("Billing records are identical!");
                    else
                        System.out.println("Billing records differ.");
                    break;

                case 8:
                    ticketList.displayTickets();
                    break;

                case 9:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
