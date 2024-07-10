/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 *
 * @author Cris
 */
public class Agujero extends Entidad{
    public String estado;
    
    public Agujero(PanelJuego gp, int x, int y) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.vel=0;
        this.hitBox=new Rectangle(0,0,gp.tamanioCasilla,gp.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        estado="Agujerovacio";
        
        this.Mensaje="Es un agujero, perfecto para plantar un Arbol";
        
        getImage();
    }
    
    
    public void getImage(){
        right1=right2=up1=up2=left1=left2=down2=down1=this.configuracion("/objetos/agujero/"+estado);
    }
    
}
