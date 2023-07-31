import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeConversionSwingApp extends JFrame {
    private JLabel sourceLabel;
    private JLabel targetLabel;
    private JTextField sourceField;
    private JTextField targetField;
    private JButton convertButton;

    private Set<String> availableZoneIds;

    public TimeConversionSwingApp() {
        // Set up the frame
        setTitle("Time Conversion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        // Retrieve the set of available time zone IDs
        availableZoneIds = ZoneId.getAvailableZoneIds();

        // Create and add components
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        sourceLabel = new JLabel("Source Country:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(sourceLabel, constraints);

        sourceField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(sourceField, constraints);

        targetLabel = new JLabel("Target Country:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(targetLabel, constraints);

        targetField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(targetField, constraints);

        convertButton = new JButton("Convert");
        convertButton.setForeground(Color.WHITE);
        convertButton.setBackground(new Color(59, 89, 182));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(convertButton, constraints);

        // Attach ActionListener to the Convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceCountry = sourceField.getText().trim().toLowerCase();
                String targetCountry = targetField.getText().trim().toLowerCase();

                // Check if the entered time zone IDs are valid
                if (!availableZoneIds.contains(sourceCountry) || !availableZoneIds.contains(targetCountry)) {
                    JOptionPane.showMessageDialog(null, "Invalid time zone ID(s). Please enter valid time zone IDs.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the current time in the source country's time zone
                ZonedDateTime sourceDateTime = ZonedDateTime.now(ZoneId.of(sourceCountry));

                // Convert the source time to the target country's time zone
                ZonedDateTime targetDateTime = sourceDateTime.withZoneSameInstant(ZoneId.of(targetCountry));

                // Format the converted time as a string in 12-hour format with AM/PM
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                String convertedTime = targetDateTime.format(formatter);

                // Display the converted time in a dialog box
                JOptionPane.showMessageDialog(null, "Converted Time: " + convertedTime, "Time Conversion", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TimeConversionSwingApp();
            }
        });
    }
}
