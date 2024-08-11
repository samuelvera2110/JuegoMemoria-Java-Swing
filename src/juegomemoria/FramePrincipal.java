/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegomemoria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class FramePrincipal extends JFrame implements ActionListener {

    private JButton[][] botones;
    private Color[] colores;
    private JButton carta1;
    private JButton carta2;
    private Color[] coloresDisponibles;

    FramePrincipal() {
        initComponents();
        this.setTitle("JUEGO DE MEMORIA");
        this.setSize(600, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initComponents() {
        this.setLayout(new GridLayout(2, 4));
        colores = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        coloresDisponibles = new Color[8];
        iniciarColores();
        iniciarBotones();
    }

    private void iniciarColores() {
        for (int i = 0; i < 8; i++) {
            coloresDisponibles[i] = colores[i / 2];
        }

        for (int i = 0; i < 8; i++) {
            int j = (int) (Math.random() * 8);
            Color temp = coloresDisponibles[i];
            coloresDisponibles[i] = coloresDisponibles[j];
            coloresDisponibles[j] = temp;
        }
    }

    private void iniciarBotones() {
        botones = new JButton[2][4];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.GRAY);
                botones[i][j].setPreferredSize(new Dimension(100, 100));
                botones[i][j].addActionListener(this);
                this.add(botones[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        if (carta1 == null) {
            carta1 = boton;
            boton.setBackground(coloresDisponibles[getIndiceBoton(boton)]);
        } else if (carta2 == null) {
            carta2 = boton;
            boton.setBackground(coloresDisponibles[getIndiceBoton(boton)]);


            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verificarCartas();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private int getIndiceBoton(JButton boton) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (botones[i][j] == boton) {
                    return i * 4 + j;
                }
            }
        }
        return -1;
    }

    private void verificarCartas() {
        if (carta1 != null && carta2 != null) {
            if (carta1.getBackground().equals(carta2.getBackground())) {
                carta1.setEnabled(false);
                carta2.setEnabled(false);
            } else {
                carta1.setBackground(Color.GRAY);
                carta2.setBackground(Color.GRAY);
            }
            carta1 = null;
            carta2 = null;
        }
    }

}
