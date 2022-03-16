package controller;

import model.Client;
import model.builder.ClientBuilder;
import model.validator.ClientInformationValidator;
import service.client.ClientService;
import view.client.information.UpdateInformationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateInformationController {
    private UpdateInformationView updateInformationView;
    private ClientInformationValidator clientInformationValidator;
    private ClientService<Client, Long> clientService;

    private final Long clientId;
    public UpdateInformationController(UpdateInformationView updateInformationView,
                                       ClientInformationValidator clientInformationValidator, ClientService<Client, Long> clientService, Long clientId) {
        this.updateInformationView = updateInformationView;
        this.clientInformationValidator = clientInformationValidator;
        this.clientService = clientService;
        this.clientId = clientId;

        updateInformationView.setAddInformationListener(new UpdateInformationButtonListener());
        updateInformationView.setCancelAddInformationListener(new CancelButtonListener());
        //updateInformationView.setFieldsCheckBoxListener(new FieldsCheckBoxListener());

    }

    private class FieldsCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateInformationView.setVisible(false);
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateInformationView.setVisible(false);
        }
    }

    private class UpdateInformationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = updateInformationView.getName();
            String address = updateInformationView.getAddress();
            String cnp = updateInformationView.getCNP();

            clientInformationValidator.validate(name, cnp);

            final List<String> errors = clientInformationValidator.getErrors();

            if(errors.isEmpty()) {
                Client client = new ClientBuilder()
                        .setName(name)
                        .setAddress(address)
                        .setCNP(cnp)
                        .build();
                boolean flag = clientService.update(clientId, client);
                if(flag) {
                    JOptionPane.showMessageDialog(updateInformationView.getContentPane(),
                            "Client updated successfully");
                    updateInformationView.setVisible(false);
                }

            } else {
                JOptionPane.showMessageDialog(updateInformationView.getContentPane(), clientInformationValidator.getFormattedErrors());
            }
        }
    }
}
