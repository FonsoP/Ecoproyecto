
package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.logica.entidades.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author C-A-F
 */
public class InterfazJugador implements Dibujado{
    public PanelJuego gp;
    public Font fuente;
    public Graphics2D g2;
    BufferedImage imagen;
    public boolean mensajeOn=false;
    public boolean tiendaOn=false;
    public String mensaje="";
    int contmensajes=0;
    public boolean victoriamensaje=false;
    public int opcion=0;
    

    public InterfazJugador(PanelJuego gp) {
        this.gp = gp;
        fuente=new Font("Arial",Font.PLAIN,40);
    }
    
    public void mostrarmensaje(String texto) {
        mensaje=texto;
        mensajeOn= true;
    }
    
    public void mostrartienda() {
        if(tiendaOn==false){
            tiendaOn=true;
        }
    }
    
    public void guardartienda() {
        if(tiendaOn==true){
            tiendaOn=false;
        }
    }

    
    public void dibujado(Graphics2D g2,Jugador jugador){
        
        g2.setFont(fuente);
        g2.setColor(Color.white);
        g2.drawString("posicion X:"+(gp.jugador.xMapa/64)+" Y "+(gp.jugador.yMapa/64), gp.screenWidth-800, gp.screenHeight-50);

        g2.setFont(fuente);
        g2.setColor(Color.white);
        //g2.drawImage(llaveimagen, gp.tamanioCasilla/2, gp.tamanioCasilla/2, gp.tamanioCasilla,gp.tamanioCasilla,null);
        //g2.drawString("x = "+gp.jugador.llaves, gp.tamanioCasilla*2, gp.tamanioCasilla);   
        
        //mostrar mensajes de NPC
        if(mensajeOn==true){
            int tarjeta=mensaje.length();
            g2.setColor(Color.blue);
            g2.fillRect(gp.screenWidth/3-8, gp.tamanioCasilla/6-5, tarjeta*15+8, gp.tamanioCasilla-6);
            
            g2.setColor(Color.black);
            g2.fillRect(gp.screenWidth/3-5, gp.tamanioCasilla/6, tarjeta*15, gp.tamanioCasilla-16);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(30F));
            
            g2.drawString(mensaje, gp.screenWidth/3, gp.tamanioCasilla/2+5);   
            contmensajes++;
            if(contmensajes>60){
                contmensajes=0;
                mensajeOn=false;
            }
        }

        //dibujado de inventario
        if(mensajeOn==false){
            dibujadoinventario(g2,jugador); 
            
            if(tiendaOn==true ){
                tienda(g2);
            }
        }
    }
    
    
    public void dibujadoinventario(Graphics2D g2,Jugador jugador){
            int MarcoX=gp.tamanioCasilla*4;
            int MarcoY=gp.tamanioCasilla/4;
            int MarcoAncho=gp.screenWidth/2;
            int MarcoAlto=gp.tamanioCasilla;
    
            Color c= new Color(82,183,136);
            g2.setColor(c);
            g2.fillRect(MarcoX, MarcoY, MarcoAncho, MarcoAlto);
            int cont=0;

            
            for(Entidad obj:jugador.inventario){
                if(obj!=null){
                    g2.drawImage(obj.down1, gp.tamanioCasilla*4+cont, gp.tamanioCasilla/4, gp.tamanioCasilla,gp.tamanioCasilla,null);
                }
                cont=cont+gp.tamanioCasilla;
            }    
    }
    
    public void tienda(Graphics2D g2){
                int MarcoX=gp.tamanioCasilla*4;
                int MarcoY=gp.tamanioCasilla/4+gp.tamanioCasilla+30;
                int MarcoAncho=gp.screenWidth/2;
                int MarcoAlto=gp.tamanioCasilla;

                //Color c= new Color(9,28,21);
                g2.setColor(Color.ORANGE);
                g2.fillRect(MarcoX, MarcoY, MarcoAncho, MarcoAlto);
                
                int cont=1;
                /*
                for(Entidad obj:jugador.inventario){
                    if(obj!=null){
                        g2.drawImage(obj.down1, gp.tamanioCasilla*4+cont, gp.tamanioCasilla/4, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
                    cont=cont+gp.tamanioCasilla;
                }
                */
                g2.setColor(Color.WHITE);
                g2.drawString("^", MarcoX+32+(opcion*32), MarcoY+MarcoAlto+32);
                
                g2.setColor(Color.ORANGE);
                g2.fillRect(gp.tamanioCasilla, MarcoY, gp.tamanioCasilla*2, gp.tamanioCasilla);
                g2.setColor(Color.WHITE);
                g2.drawString("texto", gp.tamanioCasilla+5, MarcoY+32);
                

    }
    

    @Override
    public void dibujado(Graphics2D g2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}