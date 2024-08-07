package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Actualizar;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Clase base para las entidades jugables y no jugables
 * @author C-A-F
 */

public class Entidad implements Actualizar{
    //
    public BufferedImage image;
    public String nombre;
    public boolean colision=false;
    //
    PanelJuego pJuego;
    public int xMapa,yMapa;
    public int vel;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction= "down";
    
    public int spriteCounter = 0 ;
    public int spriteNum = 1;
    public Rectangle hitBox;

    public int areadefectoX, areadefectoY;
    
    public int contadordeaccion=0;
    public Objetosclase inventario[]= new Objetosclase[10];
    
    //variables de dialogo
    public boolean dialogo=false;
    public int veloriginal;
    public String directionoriginal;
    
    public String mensaje;
    public boolean movimiento;
    
    
    /**
     * Constructor de la clase
     * @param pJuego panel donde estará
     */
    public Entidad(PanelJuego pJuego){
        this.pJuego=pJuego;
    }
    
    /**
     * actualiza las variables de la entidad
     */
    @Override
    public void actualizar(){
        
        colision=false;
        pJuego.colisiones.revisarColision(this);
        pJuego.colisiones.chequeoObjetos(this, colision);
        
        pJuego.colisiones.chequeojugador(this);
        
        if(colision==false){
                switch (direction) {
                        case "up" -> yMapa-=vel;
                        case "left" -> xMapa-=vel;
                        case "down" -> yMapa+=vel;
                        case "right" -> xMapa+=vel;
                    }
            }

            spriteCounter++;
            if (spriteCounter>10){
                if (spriteNum == 2 )
                {spriteNum=1;}
                else{
                if (spriteNum == 1)
                spriteNum=2;
                }
                spriteCounter = 0;
            }
    }
   
    /**
     * Escala una imagen al tamaño de la casilla
     * @param nombre ubicacion del archivo de la imagen
     * @return imagen en tamaño de la casilla
     */
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, pJuego.tamanioCasilla, pJuego.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
  
}
