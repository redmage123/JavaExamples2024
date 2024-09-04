import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class QuidditchMatch {
    private static final int PLAYERS_PER_TEAM = 7;
    private static final int TOTAL_PLAYERS = PLAYERS_PER_TEAM * 2;

    public static void main(String[] args) {
        CyclicBarrier equipmentCheck = new CyclicBarrier(TOTAL_PLAYERS, () -> {
            System.out.println("All players have checked their equipment. The game can begin!");
        });

        CountDownLatch matchStart = new CountDownLatch(1);

        Thread[] players = new Thread[TOTAL_PLAYERS];
        for (int i = 0; i < TOTAL_PLAYERS; i++) {
            String team = i < PLAYERS_PER_TEAM ? "Gryffindor" : "Slytherin";
            players[i] = new Thread(new QuidditchPlayer(team, i % PLAYERS_PER_TEAM, equipmentCheck, matchStart));
            players[i].start();
        }

        try {
            Thread.sleep(5000); // Madam Hooch prepares for 5 seconds
            System.out.println("Madam Hooch: 'Mount your brooms!'");
            matchStart.countDown(); // Signal players to mount their brooms

            for (Thread player : players) {
                player.join();
            }
            System.out.println("The Quidditch match has begun!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class QuidditchPlayer implements Runnable {
    private String team;
    private int playerNumber;
    private CyclicBarrier equipmentCheck;
    private CountDownLatch matchStart;

    public QuidditchPlayer(String team, int playerNumber, CyclicBarrier equipmentCheck, CountDownLatch matchStart) {
        this.team = team;
        this.playerNumber = playerNumber;
        this.equipmentCheck = equipmentCheck;
        this.matchStart = matchStart;
    }

    @Override
    public void run() {
        try {
            arrive();
            equipmentCheck();
            awaitMatchStart();
            mountBroom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void arrive() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
        System.out.println(getPlayerDescription() + " has arrived at the Quidditch pitch.");
    }

    private void equipmentCheck() throws Exception {
        System.out.println(getPlayerDescription() + " is checking their equipment.");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        System.out.println(getPlayerDescription() + " has finished checking their equipment.");
        equipmentCheck.await();
    }

    private void awaitMatchStart() throws InterruptedException {
        System.out.println(getPlayerDescription() + " is waiting for Madam Hooch's signal.");
        matchStart.await();
    }

    private void mountBroom() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500));
        System.out.println(getPlayerDescription() + " has mounted their broom.");
    }

    private String getPlayerDescription() {
        return team + " Player " + playerNumber;
    }
}