import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class HogwartsGreatHall {
    private static final int TOTAL_STUDENTS = 20;
    private static final int AVAILABLE_SEATS = 5;

    public static void main(String[] args) {
        Semaphore seats = new Semaphore(AVAILABLE_SEATS);

        for (int i = 1; i <= TOTAL_STUDENTS; i++) {
            new Thread(new Student(i, seats)).start();
        }
    }

    static class Student implements Runnable {
        private int id;
        private Semaphore seats;

        public Student(int id, Semaphore seats) {
            this.id = id;
            this.seats = seats;
        }

        @Override
        public void run() {
            try {
                System.out.println("Student " + id + " is waiting to enter the Great Hall.");
                seats.acquire();
                System.out.println("Student " + id + " has entered the Great Hall and taken a seat.");
                eat();
                System.out.println("Student " + id + " has finished eating and left the Great Hall.");
                seats.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void eat() throws InterruptedException {
            System.out.println("Student " + id + " is enjoying the feast!");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
        }
    }
}