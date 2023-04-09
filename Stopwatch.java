import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch implements ActionListener {
    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton, stopButton, holdButton;
    private long startTime = 0, stopTime = 0, elapsedTime = 0;

    public Stopwatch() {
        frame = new JFrame("Stopwatch");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel("0 seconds");
        frame.add(timeLabel);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        frame.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        frame.add(stopButton);

        holdButton = new JButton("Hold");
        holdButton.addActionListener(this);
        frame.add(holdButton);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startTime = System.currentTimeMillis() - elapsedTime;
            stopTime = 0;
            elapsedTime = 0;
        } else if (e.getSource() == stopButton) {
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
        } else if (e.getSource() == holdButton) {
            elapsedTime = System.currentTimeMillis() - startTime;
            stopTime = 0;
        }

        updateDisplay();
    }

    private void updateDisplay() {
        long seconds = 0;

        if (stopTime != 0) {
            seconds = elapsedTime / 1000;
        } else if (startTime != 0) {
            seconds = (System.currentTimeMillis() - startTime) / 1000;
        }

        timeLabel.setText(seconds + " seconds");
    }

    public static void main(String[] args) {
        new Stopwatch();
    }
}
