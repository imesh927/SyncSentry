package com.ss.Login;

import com.ss.Database.DbQuery;
import com.ss.Register.*;
import com.ss.Home.*;
import javax.swing.*;
import java.awt.*;

public class LoginInterface extends JFrame {
    public LoginInterface() {
        initComponents();
    }

    private void initComponents() {
        Container mainContainer = getContentPane();
        mainContainer.setBackground(Color.WHITE);

        // Creating a gradient panel
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                int width = getWidth();
                int height = getHeight();

                Color startColor = new Color(109, 210, 225);
                Color endColor = new Color(37, 122, 211);

                GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                g2d.dispose();
            }
        };


        mainContainer.add(gradientPanel, BorderLayout.CENTER);
        gradientPanel.setLayout(new GridLayout(0, 2));

        JPanel fieldsPanel = new JPanel(new BorderLayout()); // contains usernameAndPasswordPanel and buttonsPanel
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 100, 10));
        fieldsPanel.setBackground(new Color(255, 255, 255, 0));

        JPanel usernameAndPasswordPanel = new JPanel(new GridLayout(0, 2, 10, 30)); // contains usernameLabel, usernameField, passwordLabel, passwordField
        usernameAndPasswordPanel.setBackground(new Color(255, 255, 255, 0));
        usernameAndPasswordPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 40, 50));
        fieldsPanel.add(usernameAndPasswordPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel( new GridLayout(0, 1, 10, 10)); // contains loginButton, orLabel, registerButton
        buttonsPanel.setBackground(new Color(255, 255, 255, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(110, 110, 0, 110));
        fieldsPanel.add(buttonsPanel, BorderLayout.SOUTH);

        JLabel EmailLabel = new JLabel("Email:");
        EmailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameAndPasswordPanel.add(EmailLabel);

        JTextField emailField = new JTextField();
        usernameAndPasswordPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameAndPasswordPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        usernameAndPasswordPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setSize(100,80);
        buttonsPanel.add(loginButton);

        JLabel orLabel = new JLabel("or");
        orLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        orLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonsPanel.add(orLabel);

        JButton registerButton = new JButton("Register");
        buttonsPanel.add(registerButton);

        JPanel imagePanel = new JPanel(new BorderLayout()); // contains appNameLabel and imageLabel
        imagePanel.setBackground(new Color(255, 255, 255, 0));

        JLabel appNameLabel = new JLabel("Sync Sentry");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 38));
        appNameLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(appNameLabel, BorderLayout.NORTH);

//        ImageIcon image = new ImageIcon("src/Images/logo.png");
        java.net.URL imageURL = getClass().getResource("/Images/logo.png");
        ImageIcon image = new ImageIcon(imageURL);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 85, 0));
        imagePanel.add(imageLabel, BorderLayout.SOUTH);

        gradientPanel.add(imagePanel);
        gradientPanel.add(fieldsPanel);

        // Adding action listeners to the buttons
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid username and password", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                String status = new DbQuery().validateLoginInDb(email, password); //check the login credentials in the database
                if (status == "success"){ // if the login successful
                    dispose();
                    JOptionPane.showMessageDialog(this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(e -> {
            dispose();
            new RegisterInterface();
        });


        setTitle("Login");
        setSize(720, 480);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginInterface::new);
    }
}
