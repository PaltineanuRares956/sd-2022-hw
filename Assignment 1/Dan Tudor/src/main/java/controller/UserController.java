package controller;

import model.Client;
import model.User;
import model.validation.Notification;
import service.client.ClientService;
import service.user.AuthenticationService;
import view.LoginView;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private final UserView userView;
    //private final AuthenticationService authenticationService;
    //private final User currentUser;
    private final ClientService clientService;

    public UserController(UserView userView, ClientService clientService){//},User currentUser){//, AuthenticationService authenticationService, User currentUser) {
        this.userView = userView;
        //this.authenticationService = authenticationService;
        //this.currentUser = currentUser;
        this.clientService = clientService;
        userView.setBtnInfoViewListener(new UserController.BtnInfoViewListener());
        userView.setBtnInfoUpdateListener(new UserController.BtnInfoUpdateListener());
        userView.setBtnAccountCreateListener(new UserController.BtnAccountCreateListener());
        userView.setBtnAccountUpdateListener(new UserController.BtnAccountUpdateListener());
        userView.setBtnAccountDeleteListener(new UserController.BtnAccountDeleteListener());
        userView.setBtnAccountViewListener(new UserController.BtnAccountViewListener());

    }

//    private class LoginButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String username = loginView.getUsername();
//            String password = loginView.getPassword();
//
//            Notification<User> loginNotification = authenticationService.login(username, password);
//
//            if (loginNotification.hasErrors()) {
//                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
//            } else {
//                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
//            }
//        }
//    }

//    private class RegisterButtonListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String username = loginView.getUsername();
//            String password = loginView.getPassword();
//
//            Notification<Boolean> registerNotification = authenticationService.register(username, password);
//
//            if (registerNotification.hasErrors()) {
//                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
//            } else {
//                if (!registerNotification.getResult()) {
//                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again " +
//                            "later.");
//                } else {
//                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
//                }
//            }
//        }
//    }

    private class BtnInfoViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = userView.getTfInfoName();
            Client client = clientService.findByNameInfo(name);
            userView.getLabelInfo().setText(//"Id:" + client.getId() + "  " +
                    "Name:" + client.getName() + "  " +
                    "CardID:" + client.getCardID() + "  " +
                    "CNP:" + client.getCNP() + "  " +
                    "Address:" + client.getAddress() + "  "
                    //"Balance:" + client.getBalance() + "  " +
                    //"Type:" + client.getType() + "  " +
                    //"Date of creation:" + client.getDateOfCreation().toString()
                    );
        }
    }

    public class BtnInfoUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = userView.getTfInfoName();
            Long cardID = Long.parseLong(userView.getTfInfoCardId());
            String CNP = userView.getTfInfoCNP();
            String address = userView.getTfInfoAddress();
            clientService.updateInfo(name,cardID,CNP,address);
            //System.out.println("ceva");
        }
    }

    public class BtnAccountCreateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class BtnAccountUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class BtnAccountDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class BtnAccountViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
