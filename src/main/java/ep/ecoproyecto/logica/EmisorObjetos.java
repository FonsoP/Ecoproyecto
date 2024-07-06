package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.objetos.Casa;
import ep.ecoproyecto.logica.objetos.ObjetoCofre;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoMesa;
import ep.ecoproyecto.logica.objetos.ObjetoMoneda;
import ep.ecoproyecto.logica.objetos.ObjetoPuerta;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import java.awt.Graphics2D;

/**
 *
 * @author C-A-F
 */
public class EmisorObjetos  implements Dibujado{
    PanelJuego gp;
    
    public EmisorObjetos(PanelJuego gp){
        this.gp=gp;
    }
    
    public void establecerObj(){
        
        //----MUNDO 1:-----//
        int NumMap = 0;
       // gp.obj[NumMap][0]=new ObjetoRecogible("llave",20,9,gp);
       // gp.obj[NumMap][1]=new ObjetoRecogible("llave",5,7,gp);
        //gp.obj[NumMap][2]=new ObjetoPuerta("puerta",7,8,gp);
       // gp.obj[NumMap][3]=new ObjetoCofre("cofre",3,7,gp);
        gp.obj[NumMap][4]=new ObjetoEquipo("botas",16,6,gp);
        //gp.obj[NumMap][5]=new ObjetoMesa("mesa",9,9,gp);
      //  gp.obj[NumMap][6]=new ObjetoMoneda("coin",10,10,gp);
        gp.obj[NumMap][7]=new Casa("casa",11,8,gp);
        //----MUNDO 2:----- pruebas//
        NumMap = 1;
        
        gp.obj[NumMap][0]=new ObjetoRecogible("semilla",20,9,gp);
        gp.obj[NumMap][1]=new ObjetoRecogible("semilla",5,7,gp);
        gp.obj[NumMap][2]=new ObjetoRecogible("semilla",6,10,gp);
        
        gp.obj[NumMap][3]=new ObjetoRecogible("basura",8,8,gp);
        
        //----MUNDO 3:-----  tienda//   
        NumMap = 2;
        gp.obj[NumMap][5]=new ObjetoMesa("mesa",7,1,gp);
        /*
        //----MUNDO 4:-----derecha//
        NumMap = 3;
        
        //----MUNDO 5:-----arriba// 
        NumMap = 4;
        */
        
    }
    /*
    public void Agregar(){
            for(int i=0;i<gp.obj[0].length;i++){
                    gp.Entidadlista.add(gp.obj[0][i]);
            }
        
    }*/
    
    
    @Override
    public void dibujado(Graphics2D g2){
        
            for(int i=0;i<gp.obj[0].length;i++){
                if(gp.obj[gp.mapaActual][i]!=null){
                    int PantallaX=gp.obj[gp.mapaActual][i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
                    int PantallaY=gp.obj[gp.mapaActual][i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                    
                    int PantallaX2=gp.obj[gp.mapaActual][i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX+gp.obj[gp.mapaActual][i].hitBox.height;
                    int PantallaY2=gp.obj[gp.mapaActual][i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY+gp.obj[gp.mapaActual][i].hitBox.width;
                    
                    if((gp.obj[gp.mapaActual][i].xMapa+gp.tamanioCasilla+gp.obj[gp.mapaActual][i].hitBox.width > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.obj[gp.mapaActual][i].xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.obj[gp.mapaActual][i].yMapa+gp.tamanioCasilla+gp.obj[gp.mapaActual][i].hitBox.height > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.obj[gp.mapaActual][i].yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     
                        g2.drawImage(gp.obj[gp.mapaActual][i].down1, PantallaX, PantallaY, gp.obj[gp.mapaActual][i].hitBox.width,gp.obj[gp.mapaActual][i].hitBox.height,null);
                    }
                    
                    
                }
            }
            
    }
    
    
        
}

