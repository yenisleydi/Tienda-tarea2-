package Vista;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{
    private JButton aceptar,regresar;
    private JLabel vacio,vacio1,usuario,contraseña;
    private JTextField texto1, texto2;
    private JPanel panelButton, panelContainer, panelContent,panelLabel,panelTexto,panel1;

    public Login() {
        initButton();
        initPanels();
        setupFrame();
    }

    public void initButton() {
        aceptar= new JButton("Aceptar");
        regresar= new JButton("Regresar");
        aceptar.addActionListener(e -> {
            String usuario = texto1.getText();
            String contraseña = texto2.getText();
            if (validarContraseña(usuario, contraseña)) {
                this.setVisible(false);
                new VistaAdministrador().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        regresar.addActionListener(e -> {
            this.setVisible(false);
            new VistaTienda().setVisible(true);
        });
    }

    public void initPanels() {
        panelButton = new JPanel();
        panelContainer = new JPanel();
        panelContent =new JPanel();
        panelLabel = new JPanel();
        panelTexto =new JPanel();
        panel1 = new JPanel();
        vacio =new JLabel(" ");
        vacio1 =new JLabel(" ");
        usuario = new JLabel("Usuario");
        contraseña =new JLabel("Contraseña");
        texto1 =new JTextField();
        texto2 = new JTextField();
    }

    private void setupFrame() {
        setTitle("Formulario");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(400, 470);

        panelLabel.setLayout(new GridLayout(2,0));
        panelLabel.add(usuario);
        panelLabel.add(contraseña);

        panelTexto.setLayout(new GridLayout(2,0));
        panelTexto.add(texto1);
        panelTexto.add(texto2);

        panel1.setLayout(new GridLayout(1,2));
        panel1.add(panelLabel);
        panel1.add(panelTexto);

        panelButton.setLayout(new GridLayout(4,0));
        panelButton.add(vacio1);
        panelButton.add(aceptar);
        panelButton.add(vacio);
        panelButton.add(regresar);


        panelContainer.setLayout(new BorderLayout());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("C:\\Users\\livia\\Documents\\Paradigmas\\admin.jpg");
        JLabel imageLabel = new JLabel(new ImageIcon(image));

        panelContent.setLayout(new BorderLayout());
        panelContent.add(imageLabel, BorderLayout.NORTH);
        panelContent.add(panel1,BorderLayout.CENTER);
        panelContent.add(panelButton, BorderLayout.SOUTH);

        panelContainer.add(panelContent, BorderLayout.CENTER);

        getContentPane().add(panelContainer);

        setVisible(true);
    }

    private boolean validarContraseña(String usuario, String contraseña) {
        if (usuario.equals("admin") && contraseña.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }
}
