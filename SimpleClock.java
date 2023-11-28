import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;



public class SimpleClock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JButton militaryTime;
    JButton estTime;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;
    boolean isRunning;

    boolean isMilitary = true;
    boolean isEST = true;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(400, 300);
        this.setResizable(false);
        this.setVisible(true);




        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat=new SimpleDateFormat("EEEE");
        dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        dayLabel=new JLabel();
        dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));

        dateLabel=new JLabel();
        dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));



        this.militaryTime = new JButton("12/24 Hour");
        militaryTime.addActionListener(this::militaryStandard);

        this.estTime = new JButton("Local/GMT");
        estTime.addActionListener(this::estTime);


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);

        this.setVisible(true);
        this.add(militaryTime);
        this.add(estTime);


        Thread clockThread = new Thread(() -> {
            while (true) {

                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                if (isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        clockThread.start();


    }

    private void estTime(ActionEvent actionEvent) {
        if (isEST) {
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            isEST = false;
        } else {
            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            isEST = true;
        }
    }

    private void militaryStandard(ActionEvent actionEvent) {
        if (isMilitary) {
            timeFormat = new SimpleDateFormat("HH:mm:ss a");
            isMilitary = false;
        } else {
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            isMilitary = true;
        }


    }

    public static void main(String[] args) {
        new SimpleClock();
    }
}