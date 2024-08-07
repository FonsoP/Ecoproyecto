package ep.ecoproyecto.logica.casillas;

import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.entidades.Jugador;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 * Maneja lo resalcionado con cargar, asignar y dibujar las casillas
 * @author C-A-F
 */
public class ManejadorCasillas  implements Dibujado{
    
    PanelJuego pJuego;
    public Casilla[] casilla;
    public int numCasillaMapa[][];
    public String mapa[]= new String[6];

    /**
     * Constructor de la clase, inicializa los mapas
     * @param pJuego Panel donde se ubicará la clase
     */
    public ManejadorCasillas(PanelJuego pJuego) {
        
        this.pJuego = pJuego;
        
        casilla = new Casilla[20];        
        
        //-----AGREGAR MUNDOS AQUI----------//
        
        mapa[0]="/mapas/mapaCentro.txt";
        mapa[1]="/mapas/mapapruebas.txt";
        mapa[2]="/mapas/tienda.txt";
        mapa[3]="/mapas/mapaDerecha.txt";
        mapa[4]="/mapas/mapaArriba.txt";
        mapa[5]="/mapas/mapaIzquierda.txt";
         
        
        
        cargarMapa(mapa[pJuego.mapaActual]);
        getImagenCasilla();
        
    }
    /**
     * inicializa las casillas
     */
    private void getImagenCasilla(){

            
            configuracion(0, "agua", true);       
            configuracion(1, "aguaA", true);
            configuracion(2, "aguaAD", true);
            configuracion(3, "aguaAb", true);
            configuracion(4, "aguaAbI", true);
            configuracion(5, "aguaD", true);
            configuracion(6, "aguaDA", true);
            configuracion(7, "aguaI", true);
            configuracion(8, "aguaIA", true);
            configuracion(9, "arbol", true);
            configuracion(10, "arena", false);
            configuracion(11, "asfalto", true);
            configuracion(12, "piso", false);          
            configuracion(13, "pasto", false);
            configuracion(14, "lodo", false);
            configuracion(15, "pared", true);
            configuracion(16, "pastoAma",false);
    }
    /**
     * asigna a cada tipo de casilla una imagen y si tendrán colision
     * @param id indice que ocupará la casilla
     * @param ruta direccion de la imagen que se le asigna
     * @param colision booleano para saber si chocaremos con esta o no
     */
    public void configuracion(int id,String ruta, boolean colision){
        
        Herramientas herramienta = new Herramientas();
        
        try{

            casilla[id] = new Casilla();
            casilla[id].imagen= ImageIO.read(getClass().getResource("/casillas/"+ruta+".jpg"));
            casilla[id].imagen = herramienta.imagenEscalada(casilla[id].imagen, pJuego.tamanioCasilla, pJuego.tamanioCasilla);
            casilla[id].colision = colision;
            
        }catch(IOException e){

        }
        
    }
    /**
     * Carga el mapa de un archivo de texto
     * @param direccion direccion del archivo del mapa
     */
    private void cargarMapa(String direccion){
        try{
            
            
            InputStream is = getClass().getResourceAsStream(direccion);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int colum=0;
                int fila=0;
                
                String line= br.readLine();
                
                if(line != null){
                    String[] arr=line.split(" ");

                    if(numCasillaMapa==null||numCasillaMapa.length != arr.length ){
                        numCasillaMapa=new int[arr.length][arr.length]  ;
                    }

                        while(line!=null &&colum <arr.length && fila < arr.length  ){

                            while(colum < arr.length){

                                String numeros[] = line.split(" ");

                                int num = Integer.parseInt(numeros[colum]);

                                numCasillaMapa[colum][fila]=num;
                                colum++;
                            }
                            if(colum==arr.length ){
                                colum=0;
                                fila++;

                            }
                    
                        line= br.readLine();
                    }
                }
            }
        }catch (IOException e) {
            
        } 
    }
    /**
     * Carga un mapa de nuevo
     */
    public void actualizar(){

        cargarMapa(mapa[pJuego.mapaActual]);        
    
    }
    /**
     * dibuja las casillas del mapa
     * @param g2 base para el dibujado en pantalla
     */
    @Override
    public void dibujado(Graphics2D g2){
        
        int columna=0;
        int fila=0;
        
        
        while (columna < numCasillaMapa.length && fila < numCasillaMapa.length){
        
            int numCasilla = numCasillaMapa[columna][fila];
                
                int PosicionX=columna*pJuego.tamanioCasilla;
                int PosicionY=fila*pJuego.tamanioCasilla;
                int PantallaX=PosicionX- pJuego.jugador.xMapa+pJuego.jugador.pantallaX;
                int PantallaY=PosicionY- pJuego.jugador.yMapa+pJuego.jugador.pantallaY;
                
                if((PosicionX+pJuego.tamanioCasilla > pJuego.jugador.xMapa-pJuego.jugador.pantallaX)&&
                        (PosicionX-pJuego.tamanioCasilla < pJuego.jugador.xMapa+pJuego.jugador.pantallaX)&&
                   (PosicionY+pJuego.tamanioCasilla > pJuego.jugador.yMapa-pJuego.jugador.pantallaY)&&
                        (PosicionY-pJuego.tamanioCasilla < pJuego.jugador.yMapa+pJuego.jugador.pantallaY)){     
                
                    g2.drawImage(casilla[numCasilla].imagen, PantallaX, PantallaY,null);
                    
                }
                
                columna++;


                if(columna==numCasillaMapa.length){
                    columna=0;
                    fila++;
                }
       }      
    }        
}
    

