import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
    public class Pong extends JPanel implements KeyListener, Runnable {

        private int player1X = 20;
        private int player1Y = 200;
        private int player2X = 760;
        private int player2Y = 200;
        private int ballX = 400;
        private int ballY = 250;
        private int ballXSpeed = -3;
        private int ballYSpeed = -1;
        private int player1Score = 0;
        private int player2Score = 0;

        public Pong() {
            JFrame frame = new JFrame("Pong");
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.addKeyListener(this);
            frame.add(this);
            Thread gameThread = new Thread(this);
            gameThread.start();
            frame.setVisible(true);
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 800, 500);
            g.setColor(Color.BLACK);
            g.fillRect(player1X, player1Y, 20, 100);
            g.fillRect(player2X, player2Y, 20, 100);
            g.fillOval(ballX, ballY, 20, 20);
            g.drawString("Player 1: " + player1Score, 100, 20);
            g.drawString("Player 2: " + player2Score, 600, 20);
        }

        public void run() {
            while (true) {
                update();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void update() {
            ballX += ballXSpeed;
            ballY += ballYSpeed;
            if (ballY < 0 || ballY > 480) {
                ballYSpeed = -ballYSpeed;
            }
            if (ballX < 40 && ballX > 20 && ballY > player1Y && ballY < player1Y + 100) {
                ballXSpeed = -ballXSpeed;
            }
            if (ballX > 740 && ballX < 760 && ballY > player2Y && ballY < player2Y + 100) {
                ballXSpeed = -ballXSpeed;
            }
            if (ballX < 0) {
                player2Score++;
                ballX = 400;
                ballY = 250;
            }
            if (ballX > 800) {
                player1Score++;
                ballX = 400;
                ballY = 250;
            }
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                player2Y -= 10;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player2Y += 10;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                player1Y -= 10;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                player1Y += 10;
            }
        }

        public void keyTyped(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {}

    }
