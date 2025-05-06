package com.conversor;

import javax.swing.*;
import java.awt.event.*;
import java.net.http.*;
import java.net.URI;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Conversor de Monedas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(null);

            JLabel labelAmount = new JLabel("Monto:");
            labelAmount.setBounds(20, 20, 100, 25);
            frame.add(labelAmount);

            JTextField fieldAmount = new JTextField();
            fieldAmount.setBounds(120, 20, 150, 25);
            frame.add(fieldAmount);

            JLabel labelFrom = new JLabel("De:");
            labelFrom.setBounds(20, 60, 100, 25);
            frame.add(labelFrom);

            JComboBox<String> comboFrom = new JComboBox<>(new String[]{"USD", "EUR", "BRL", "BOB", "ARS"});
            comboFrom.setBounds(120, 60, 80, 25);
            frame.add(comboFrom);

            JLabel labelTo = new JLabel("A:");
            labelTo.setBounds(210, 60, 30, 25);
            frame.add(labelTo);

            JComboBox<String> comboTo = new JComboBox<>(new String[]{"USD", "EUR", "BRL", "BOB", "ARS"});
            comboTo.setBounds(240, 60, 80, 25);
            frame.add(comboTo);

            JButton buttonConvert = new JButton("Convertir");
            buttonConvert.setBounds(120, 100, 100, 30);
            frame.add(buttonConvert);

            JLabel labelResult = new JLabel("Resultado:");
            labelResult.setBounds(20, 140, 350, 25);
            frame.add(labelResult);

            buttonConvert.addActionListener(e -> {
                try {
                    double amount = Double.parseDouble(fieldAmount.getText());
                    String from = (String) comboFrom.getSelectedItem();
                    String to = (String) comboTo.getSelectedItem();

                    HttpClient client = HttpClient.newHttpClient();
                    String url = String.format("https://v6.exchangerate-api.com/v6/bc03cc380c4d5c7d23ff4901/latest/%s", from);
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

                    if (json.get("result").getAsString().equals("success")) {
                        JsonObject rates = json.getAsJsonObject("conversion_rates");
                        double rate = rates.get(to).getAsDouble();
                        double converted = amount * rate;
                        labelResult.setText(String.format("Resultado: %.2f %s = %.2f %s", amount, from, converted, to));
                    } else {
                        labelResult.setText("Error al obtener la tasa de cambio.");
                        System.out.println("Respuesta de la API: " + response.body());
                    }
                } catch (Exception ex) {
                    labelResult.setText("Error en la conversión.");
                    ex.printStackTrace();
                }
            });

            frame.setVisible(true);
        });
    }
}
