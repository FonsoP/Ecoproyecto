package ep.ecoproyecto.gui;

import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;
import ep.ecoproyecto.logica.tipografia.Fuentes;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *Clase del menú para dar opciones de inicio del juego e inforrmacion
 * @author C-A-F
 */

public class MenuIni extends javax.swing.JFrame {

   
    Fuentes tipoFuente=new Fuentes();
    ImageIcon img = new ImageIcon("/player/jg_abj_01.png");
    JFrame ventana= new JFrame();
    PanelJuego panelDeJuego= new PanelJuego(ventana);
    String nomb;
    
    /**
     * Creates new form Menu
     */
    
    public MenuIni() {
        initComponents();
        titulo.setFont(tipoFuente.fuente(tipoFuente.upheaval,0,60));
        this.setTitle("ECOPROYECTO");
        titulo.setForeground(Color.white);
        this.setLocationRelativeTo(null);
        
        JpanelImagen jImg= new JpanelImagen(jPanel1,"/fondo/ecocrosin.jpg" );
        
        jPanel1.add(jImg).repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        jugarButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();
        acercadeButton = new javax.swing.JButton();
        ayudaButton = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage((new ImageIcon(getClass().getResource("/player/jg_abj_01.png"))).getImage());

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 640));

        titulo.setBackground(new java.awt.Color(204, 204, 204));
        titulo.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N
        titulo.setText("ECO CROSSING");

        jugarButton.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jugarButton.setForeground(new java.awt.Color(60, 63, 65));
        jugarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/jugar.jpg"))); // NOI18N
        jugarButton.setBorder(null);
        jugarButton.setBorderPainted(false);
        jugarButton.setContentAreaFilled(false);
        jugarButton.setIconTextGap(0);
        jugarButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jugarButton.setMaximumSize(new java.awt.Dimension(170, 55));
        jugarButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/jugarSelec.jpg"))); // NOI18N
        jugarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarButtonActionPerformed(evt);
            }
        });

        salirButton.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        salirButton.setForeground(new java.awt.Color(60, 63, 65));
        salirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/salir.jpg"))); // NOI18N
        salirButton.setBorder(null);
        salirButton.setBorderPainted(false);
        salirButton.setContentAreaFilled(false);
        salirButton.setIconTextGap(0);
        salirButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        salirButton.setMaximumSize(new java.awt.Dimension(170, 55));
        salirButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/salirSelec.jpg"))); // NOI18N
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        acercadeButton.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        acercadeButton.setForeground(new java.awt.Color(60, 63, 65));
        acercadeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/acerca.jpg"))); // NOI18N
        acercadeButton.setBorder(null);
        acercadeButton.setBorderPainted(false);
        acercadeButton.setContentAreaFilled(false);
        acercadeButton.setIconTextGap(0);
        acercadeButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        acercadeButton.setMaximumSize(new java.awt.Dimension(170, 55));
        acercadeButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/acercaSelec.jpg"))); // NOI18N
        acercadeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercadeButtonActionPerformed(evt);
            }
        });

        ayudaButton.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        ayudaButton.setForeground(new java.awt.Color(60, 63, 65));
        ayudaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/ayuda.jpg"))); // NOI18N
        ayudaButton.setBorder(null);
        ayudaButton.setBorderPainted(false);
        ayudaButton.setContentAreaFilled(false);
        ayudaButton.setIconTextGap(0);
        ayudaButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        ayudaButton.setMaximumSize(new java.awt.Dimension(170, 55));
        ayudaButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/menu/ayudaSelec.jpg"))); // NOI18N
        ayudaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayudaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ayudaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acercadeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(198, 458, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jugarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(jugarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ayudaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(acercadeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jugarButton.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1039, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     *inicia el juego si se presionó jugar
    *@param evt Indicador de acciones
    */
    private void jugarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarButtonActionPerformed
   
        int respServer = JOptionPane.showConfirmDialog(this, "Quieres iniciar un server?", "Inicio de Server",JOptionPane.YES_NO_CANCEL_OPTION);
        switch (respServer) {
            case JOptionPane.YES_OPTION ->{
                iniciarServidor();
                panelDeJuego.socketCliente = new Cliente("localhost",panelDeJuego);
                panelDeJuego.socketCliente.start();
            }
            case JOptionPane.NO_OPTION -> {
                if (!iniciarCliente())
                    return;
            }
            default ->{
                return;
            }
        }
        do{
            nomb=JOptionPane.showInputDialog(this, "Por favor, introduzca su nombre de usuario:","Nombre de Usuario",JOptionPane.QUESTION_MESSAGE);        
        }while(nomb==null || nomb.trim().isEmpty());
        
        configurarVentana();
        iniciarJuego();
    }//GEN-LAST:event_jugarButtonActionPerformed
    /** 
     * Inicia un servidor en el equipo
     */
    private void iniciarServidor(){
        panelDeJuego.socketserver = new Servidor(panelDeJuego);
        panelDeJuego.socketserver.start();
    }
    
    /**
     * Inicia el cliente con ajustes para entrar o no a un servidor
     * @return devuelve verdadero si no se conecta a un sevidor
     */
    
    private boolean iniciarCliente(){
        int respCliente = JOptionPane.showConfirmDialog(this, "Quieres ingresar a un server?", "Ingresar a un server", JOptionPane.YES_NO_OPTION);
        
        switch (respCliente) {
            case JOptionPane.YES_OPTION -> {
                String IPServer = JOptionPane.showInputDialog(this, "Introduzca la IP del server a la que se quiere conectar:", "IP Server", JOptionPane.INFORMATION_MESSAGE);
                if (IPServer == null || IPServer.trim().isEmpty()){
                    return false;
                }
                panelDeJuego.socketCliente = new Cliente(IPServer, panelDeJuego);
            }
            case JOptionPane.NO_OPTION -> panelDeJuego.socketCliente = new Cliente("localhost",panelDeJuego);
            default -> {
                return false;
            }
        }
        
        panelDeJuego.socketCliente.start();
        return true;
    }
    
    /**
     * configura las opciones de la ventana
     */
    
    private void configurarVentana(){
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("ECOPROYECTO");
        ventana.setIconImage((new ImageIcon(getClass().getResource("/player/jg_abj_01.png"))).getImage());

        panelDeJuego.config.cargarConfig();
        
        if (panelDeJuego.pantallaCompleta) {
            ventana.setUndecorated(true);
            panelDeJuego.setPantallaCompleta();
            
        }
    }
    /**
     * Inicia los parametros del juego y muestra el frame en pantalla
     */
    private void iniciarJuego(){
        panelDeJuego.configuracionDeJuego();
        
        panelDeJuego.inicioJugador(nomb);

        panelDeJuego.gameThread = new Thread(panelDeJuego);
        panelDeJuego.gameThread.start();
        
        
        this.setVisible(false);
        ventana.add(panelDeJuego);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        panelDeJuego.reproducirMusica(panelDeJuego.musica);
    }
    
    /**
     *para la ejecución si se presiona salir
    *@param evt Indicador de acciones

    */
    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        this.dispose();
        ventana.dispose();
    }//GEN-LAST:event_salirButtonActionPerformed
    /**
     *Muestra la informacion del grupo si se preciona acerca de
    *@param evt Indicador de acciones

    */
    private void acercadeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercadeButtonActionPerformed
        String mensaje = "<html><body>" +
                "<h2>ECOCROSSING</h2>" +
                "<p><b>Versión:</b> 0.1.5</p>" +
                "<p><b>Lenguaje de Programación:</b> Java</p>" +
                "<p><b>Desarrollado por:</b> Cristian Baczek, Alfonso Palma y Fernando Pérez</p>" +
                "<p><b>Descripción:</b> EcoCrossing es una iniciativa para concientizar sobre las ODS a través de un juego interactivo.</p>" +
                "<p><b>Agradecimientos especiales a:</b> RyiSnow y vanZeben.</p>" +
                "</body></html>";
        
        // Cuadro de Dialogo//
        JOptionPane.showMessageDialog(this, mensaje, "Acerca de ECOCROSSING", JOptionPane.INFORMATION_MESSAGE, img);
    }//GEN-LAST:event_acercadeButtonActionPerformed
/**
     *Muestra las instrucciones
    *@param evt Indicador de acciones

    */
    private void ayudaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaButtonActionPerformed
        String mensaje = "<html><body>" +
                "<h2>Ayuda - ECOCROSSING</h2>" +
                "<p><b>CÓMO JUGAR:</b></p>" +
                "<p>- Usa las <b>teclas de flechas</b> o el <b>WASD</b> para mover tu personaje.</p>" +
                "<p>- Usa <b>ESC</b> o <b>P</b> para ingresar al menu de opciones." +
                "<p>- Usa <b>E</b> para interactuar con elementos de tu entorno.</p>" +
                "<p>- Usa <b>ENTER</b> para confirmar alguna opción.</p>" +
                "<p>- Viaja a otras islas y ve completando las misiones para conseguir Unidades de Credito" +
                "<p></p>" +
                "<p><b>MENÚ PRINCIPAL</b></p>" +
                "<p>- <b>Jugar:</b> Inicia el Juego.</p>" +
                "<p>- <b>Ayuda:</b> Muestra esta pantalla de ayuda.</p>" +
                "<p>- <b>Acerca de:</b> Muestra informacion sobre el juego y los desarrolladores.</p>" +
                "<p>- <b>Salir:</b> Cierra la aplicación.</p>" +
                "</body></html>";
        
        // Cuadro de Dialogo//
        JOptionPane.showMessageDialog(this, mensaje, "Ayuda ECOCROSSING", JOptionPane.INFORMATION_MESSAGE, img);
    }//GEN-LAST:event_ayudaButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuIni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuIni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuIni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuIni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        //</editor-fold>

        /* Create and display the form */
        
        
        java.awt.EventQueue.invokeLater(() -> {
            new MenuIni().setVisible(true);
        })
                ;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acercadeButton;
    private javax.swing.JButton ayudaButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jugarButton;
    private javax.swing.JButton salirButton;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables

   


}
