package gui.composants.left;

import com.sun.tools.javac.Main;
import database.Database;
import gui.MainWindow;
import gui.events.*;
import gui.Panel;
import gui.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;


public class User extends JPanel implements Panel {
    private final UUID uuid;
    private final Database database;
    private final Panel parent;

    private ProfilePicture profile_picture;
    private JLabel name_display;
    private JLabel last_message_date;
    private JLabel status_led;

    public User(Panel parent, Database database,  UUID uuid) {
        this.parent = parent;
        this.database = database;
        this.uuid = uuid;
        initComponents();
    }

    private void initComponents() {
        this.setBackground(MainWindow.BLACK);
        this.setForeground(MainWindow.WHITE);
        this.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        this.setMaximumSize(new Dimension(320, 50));

        this.name_display = new JLabel();
        this.last_message_date = new JLabel();

        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{50, 200, 50, 0};
        ((GridBagLayout) getLayout()).rowHeights = new int[]{20, 30, 0};
        ((GridBagLayout) getLayout()).columnWeights = new double[]{0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0E-4};

        this.profile_picture = new ProfilePicture("/profil.png", 35);

        this.name_display.setText(this.database.getNicknameFor(this.uuid));
        this.name_display.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        this.name_display.setForeground(MainWindow.WHITE);

        this.last_message_date.setText("date");
        this.last_message_date.setForeground(MainWindow.DARK_GRAY);

        this.status_led = new JLabel("•");
        this.status_led.setForeground(MainWindow.GREEN);

        add(this.profile_picture, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        add(this.name_display, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10, 0, 0, 5), 0, 0));
        add(this.last_message_date, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10, 0, 0, 0), 0, 0));
        add(this.status_led, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 40, -10, 0), 0, 0));


        this.profile_picture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                clickedOn();
            }
        });
        this.name_display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                clickedOn();
            }
        });
        this.last_message_date.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                clickedOn();
            }
        });
        this.status_led.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                clickedOn();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                clickedOn();
            }
        });
    }

    private void toBlue() {
        this.setBackground(MainWindow.BLUE);
        name_display.setForeground(MainWindow.WHITE);
        last_message_date.setForeground(MainWindow.LIGHT_GRAY);
    }

    private void toGray() {
        this.setBackground(MainWindow.BLACK);
        name_display.setForeground(MainWindow.WHITE);
        last_message_date.setForeground(MainWindow.DARK_GRAY);
    }

    private void clickedOn() {
        this.toBlue();
        this.converge_event(new ChangeSelectedUser(this.uuid));
    }

    public void propagate_event(Event event) {
        if (event instanceof ChangeSelectedUser && !((ChangeSelectedUser) event).getUUID().equals(this.uuid)) {
            this.toGray();
        } else if (event instanceof ConnectedUser && ((ConnectedUser) event).getUUID().equals(this.uuid)) {
            this.status_led.setForeground(MainWindow.GREEN);
        } else if (event instanceof DisconnectUser && ((DisconnectUser) event).getUUID().equals(this.uuid)) {
            this.status_led.setForeground(MainWindow.RED);
        } else if (
                event instanceof ReceiveMessage && (
                        (((ReceiveMessage) event).getMessage().getFrom().equals(this.uuid) && ((ReceiveMessage) event).getMessage().getTo().equals(this.database.getUUID())) ||
                                (((ReceiveMessage) event).getMessage().getTo().equals(this.uuid) && ((ReceiveMessage) event).getMessage().getFrom().equals(this.database.getUUID()))
                )
        ) {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            this.last_message_date.setText(formatter.format(((ReceiveMessage) event).getMessage().getDate()));
        } else if(event instanceof ChangeNickname && ((ChangeNickname) event).getUUID().equals(this.uuid)) {
            this.name_display.setText(((ChangeNickname) event).getNickname());
        }
    }

    @Override
    public void converge_event(Event event) {
        this.parent.converge_event(event);
    }
}