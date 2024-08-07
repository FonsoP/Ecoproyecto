package ep.ecoproyecto.gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *Esta clase sirve para añadir imagenes al fondo de los paneles
 * @author C-A-F
 */
public class JpanelImagen extends JLabel {
    private int x,y;
    private final String path;
    
    /**
    * Constructor
    *@param panel el panel sobre el que se trabajará
    *@param path es la direccion de la imagen a mostrar 

    */
    public JpanelImagen(JPanel panel,String path) {
        this.path = path;
        this.x = panel.getWidth();
        this.y=panel.getHeight();
        this.setSize(x, y);
    }
    
    /**
     * metodo para dibujar la imagen en el panel
    *@param g base para los elementos graficos
    */ 
    
    @Override
    public void paint(Graphics g) {
        ImageIcon img =new ImageIcon(getClass().getResource(path)); 
        g.drawImage(img.getImage(), 0, 0, x,y,null);
    }
    
    
    
}
