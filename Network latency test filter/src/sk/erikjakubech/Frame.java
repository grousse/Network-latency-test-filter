package sk.erikjakubech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class Frame extends JFrame implements ActionListener {

    private JButton chooseFileButton;
    private JButton saveButton;
    private JLabel lostTrackerLabel;
    private static JTextArea textArea;
    private static JFrame frame;
    private File file;

    Frame() {

        frame = new JFrame("Data process");
        frame.setLayout(new FlowLayout(FlowLayout.LEADING));

        ImageIcon icon = new ImageIcon("src/sk/erikjakubech/icon.png");
        frame.setIconImage(icon.getImage());

        textArea = new JTextArea(50,60);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(true);

        lostTrackerLabel = new JLabel();

        JScrollPane scroll = new JScrollPane(getTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        saveButton = new JButton("Uložiť");
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);


        chooseFileButton = new JButton("Vybrať súbor");
        chooseFileButton.addActionListener(this);
        chooseFileButton.setFocusable(false);

        frame.add(chooseFileButton);
        frame.add(saveButton);
        frame.add(lostTrackerLabel);
        frame.add(scroll);

        frame.setSize(700,885);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseFileButton) {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);
            FileReader.getData().clear();
            textArea.setText("");
            lostTrackerLabel.setText("");

            if (response == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                FileReader.fileRead(file.getAbsoluteFile());
                lostTrackerLabel.setText(FileReader.lostTracker());
                lostTrackerLabel.setVisible(true);

                for (String s : FileReader.getData()) {
                    if (!s.contains("Reply") && !s.contains("Pinging") && !s.contains("Request")) {
                        FileReader.displayData(s);
                    }
                }
            }
        }
        if (e.getSource() == saveButton) {
            FileService.createOutput();
        }
    }

    public static JTextArea getTextArea() {
        return textArea;
    }

    public static JFrame getFrame() {
        return frame;
    }
}