/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//
package Entidades;
import Interfaces.Dibujado;
import ep.ecoproyecto.Herramientas;
import ep.ecoproyecto.PanelJuego;
import ep.ecoproyecto.KeyHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import objetos.Objetosclase;

/**
 *
 * @author Cris
 */
public class Jugador extends Entidad implements Dibujado{

    KeyHandler keyH;
    public final int pantallaX;
    public final int pantallaY;
    
    public Entidad inventario[]= new Entidad[10];
    public int llaves=0;
    public boolean interactuar;
    
    
    public Jugador(PanelJuego gp, KeyHandler keyH){
        
        super(gp);
        
        this.keyH=keyH;
        
        pantallaX=gp.screenWidth/2-(gp.tamanioCasilla/2);
        pantallaY=gp.screenHeight/2-(gp.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=8;
        hitBox.y=8;
        //tamanio de la hitbox
        hitBox.height=48;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        

       
        valoresporDefecto();
        getPlayerImage();
        
        
    }
    
    public void valoresporDefecto(){
        //posicion del jugador en el arreglo +1,
        xMapa=3*gp.tamanioCasilla+gp.tamanioCasilla;
        yMapa=3*gp.tamanioCasilla+gp.tamanioCasilla;

        vel=4;
        direction ="down"; 
        interactuar=false;
    }

    public String getDirection() {
        return direction;
    }
    
    public void getPlayerImage(){
        up1=configuracion("/player/jg_arr_01");
        up2=configuracion("/player/jg_arr_02");
        down1=configuracion("/player/jg_abj_01");
        down2=configuracion("/player/jg_abj_02");
        left1=configuracion("/player/jg_izq_01");
        left2=configuracion("/player/jg_izq_02");
        right1=configuracion("/player/jg_der_01");
        right2=configuracion("/player/jg_der_02");
    }
    
    public void update(){
        interactuar=false;
        if (keyH.upPressed==true||keyH.leftPressed==true||keyH.downPressed==true||keyH.rightPressed==true||keyH.ePressed==true){
            //actualizamos la posicion del jugador sumando o restando su velocidad
            if(keyH.upPressed==true){
                direction="up";
            }else if(keyH.leftPressed==true){
                direction="left";
            }else if(keyH.downPressed==true){
                direction="down";
            }else if(keyH.rightPressed==true){
                direction="right";
            }else if(keyH.ePressed==true){
                interactuar=true;
            }
            
            //colision Casillas
            colision=false;
            gp.colisiones.revisarColision(this);
            
            //colision NPC
            int entidadID=gp.colisiones.chequeoEntidades(this, gp.NPC);
            intereaccionNCP(entidadID);
            
            //colision objetos
            int objID=gp.colisiones.chequeoObjetos(this, true);
            ColisionconObjetos(objID);
            
            //colision eventos
            gp.ControlEventos.chequeoEvento();
            
            if(colision==false){
                switch (direction) {
                        case "up":
                            yMapa-=vel;
                            break;
                        case "left":
                            xMapa-=vel;
                        break;
                        case "down":
                            yMapa+=vel;
                        break;
                        case "right":
                            xMapa+=vel;
                        break;
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
    }
    
    public void ColisionconObjetos(int id){
        if(id!=999){
            //usa el nombre del objeto para saber con cual objeto colisiona 
            String objnombre=gp.obj[gp.MapaActual][id].nombre;
            
            //switch para el nombre
            //nota se puede usar un getclass para saber el tipo o usar 
            System.out.println(gp.obj[gp.MapaActual][id].nombre);
            switch(objnombre){
                case "llave":
                        llaves++;
                        gp.efectos(2);
                        gp.hud.mostrarmensaje("conseguiste una llave");
                        this.inventario[1]=gp.obj[gp.MapaActual][id];
                        gp.obj[gp.MapaActual][id]=null;
                        System.out.println("llaves: "+llaves);
                    break;
                case "puerta":
                        if(llaves>0){
                            llaves--;
                            gp.efectos(5);
                            gp.hud.mostrarmensaje("puerta abierta");
                            gp.obj[gp.MapaActual][id]=null;
                            System.out.println("llaves: "+llaves);
                            this.inventario[0]=gp.obj[gp.MapaActual][id];
                        }else{
                            gp.hud.mostrarmensaje("no tienes llaves para esta puerta");
                        }
                    break;    
                case "botas":
                        if(this.inventario[0]==null){
                            gp.efectos(4);
                            gp.hud.mostrarmensaje("conseguiste "+objnombre);
                            vel=vel+1;
                            this.inventario[0]=gp.obj[gp.MapaActual][id];
                            gp.obj[gp.MapaActual][id]=null;
                        }
                    break;
                case "cofre":
                            gp.hud.victoriamensaje=true;
                            gp.obj[gp.MapaActual][id]=null;
                    break;
            }
        }
    }
    
    public void RecogerObjeto(int i){
        if(i!=999){
            gp.obj[gp.MapaActual][i]=null;
        }
    }
    
    public void intereaccionNCP(int id) {
        if(id!=999){
            if (interactuar==true){
                if(direction=="right"){
                    gp.NPC[gp.MapaActual][id].direction="left";
                }
                if(direction=="left"){
                    gp.NPC[gp.MapaActual][id].direction="right";
                }
                if(direction=="up"){
                    gp.NPC[gp.MapaActual][id].direction="down";
                }
                if(direction=="down"){
                    gp.NPC[gp.MapaActual][id].direction="up";
                }
                gp.hud.mostrarmensaje(gp.NPC[gp.MapaActual][id].Mensaje);
            }
        }
    }
    
    public void dibujado(Graphics2D g2){

       BufferedImage image = null;  

        switch(direction){
            case "up" -> {     
                if (spriteNum==1){
                    image=up1;
                }
                  if (spriteNum==2){
                    image=up2;
                }
            break;
            }
            case "down" -> {
                if (spriteNum==1)
                    image=down1;
                if (spriteNum==2)
                    image=down2;                              
                break;
            }
            case "left" ->  {     
                if (spriteNum==1)
                    image=left1;
                if (spriteNum==2)
                    image=left2;
                break;
            }
            case "right" -> {      
                if (spriteNum==1)   
                    image=right1;
                if (spriteNum==2)   
                    image=right2;
                break;
            }
        }
       g2.drawImage(image,pantallaX,pantallaY,null); 
    }


}
