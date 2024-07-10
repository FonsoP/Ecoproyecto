package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.logica.KeyHandler;
import ep.ecoproyecto.logica.net.packets.Packet02Mov;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import ep.ecoproyecto.logica.tipografia.Fuentes;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author C-A-F
 */

public class Jugador extends Entidad{

    KeyHandler keyH;
    public final int pantallaX;
    public final int pantallaY;
    protected String username;
    protected int mapa;
    
    public String sombreros[]= new String[10];
    // 1 llaves
    // 2 basura
    // 7 semillas
    // 9 dinero                                                                     
    // 8 red
    public int cantInventario[]= new int[5];
    public boolean interactuar;
    
    public int estado;
    public final int estadojuego=1;
    public final int estadotienda=2;
    
    //contador de culdaun en teclas
    public int contador;
    public boolean tecla=true;
    
    public BufferedImage sombrero;
    public String sombreroactual;

    
    public Jugador(PanelJuego gp) {
        super(gp);
        this.keyH=new KeyHandler();
        this.username="";
        
        pantallaX=gp.screenWidth/2-(gp.tamanioCasilla/2);
        pantallaY=gp.screenHeight/2-(gp.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=16;
        hitBox.y=16;
        //tamanio de la hitbox
        hitBox.height=52;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        estado=estadojuego;
        valoresporDefecto();
        getPlayerImage();
    }
    
    public Jugador(PanelJuego gp, KeyHandler keyH, String username){
        
        super(gp);
        
        this.keyH=keyH;
        this.username=username;
        this.mapa=0;
        pantallaX=gp.screenWidth/2-(gp.tamanioCasilla/2);
        pantallaY=gp.screenHeight/2-(gp.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=8;
        hitBox.y=4;
        //tamanio de la hitbox
        hitBox.height=56;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        sombreroactual="ninguno";
        
        
        valoresporDefecto();
        getPlayerImage();
        getsombre();
    }

    public int getMapa() {
        return mapa;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }
    
    private void valoresporDefecto(){
        //posicion del jugador en el arreglo +1,
        xMapa=10*gp.tamanioCasilla+gp.tamanioCasilla;
        yMapa=10*gp.tamanioCasilla+gp.tamanioCasilla;
        
        estado=estadojuego;
        
        vel=4;
        veloriginal=vel;
        direction ="down"; 
        
        if(gp.socketserver!=null){
        Packet02Mov packet=new Packet02Mov(username, this.xMapa, this.yMapa,this.direction);
        packet.writeData(PanelJuego.juego.socketcliente);
        }
    }

    public String getDirection() {
        return direction;
    }

    public String getUsername() {
        return username;
    }
    
    private void getPlayerImage(){
        
        up1=configuracion("/player/jg_arr_01");
        up2=configuracion("/player/jg_arr_02");
        down1=configuracion("/player/jg_abj_01");
        down2=configuracion("/player/jg_abj_02");
        left1=configuracion("/player/jg_izq_01");
        left2=configuracion("/player/jg_izq_02");
        right1=configuracion("/player/jg_der_01");
        right2=configuracion("/player/jg_der_02");
        
    }
    
    private void getsombre(){
        if(sombreroactual!="ninguno"){
            sombrero=configuracionsombrero("/gorros/"+sombreroactual);
        }
    }
    
    public BufferedImage configuracionsombrero(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, gp.tamanioCasilla, gp.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
    
    
    public void update(){
        if(keyH != null){        
            interactuar=false;
            if (keyH.upPressed==true||keyH.leftPressed==true||keyH.downPressed==true||keyH.rightPressed==true||keyH.ePressed==true){
                //actualizamos la posicion del jugador sumando o restando su velocidad
                
                switch(estado){
                    case 1 -> estadoJuego();
                    case 2 -> estadoTienda();
                }
                
                if(tecla==false){
                    aumentarcontador();
                }
            }    
        } 
    }
    public void aumentarcontador(){
        contador++;
        if(contador>10){
            tecla=true;
            contador=0;
        }
    }
    
    
    //la tienda se esta volviendo a abrir porque reconoce la precion de tecla mas de una vez asi que interactua denuevo con la tienda
    
    public void estadoJuego(){
        gp.hud.guardartienda();
        if(tecla==true){
                if(keyH.upPressed==true){
                    direction="up";
                }else if(keyH.leftPressed==true){
                    direction="left";
                }else if(keyH.downPressed==true){
                    direction="down";
                }else if(keyH.rightPressed==true){
                    direction="right";
                }else if(keyH.ePressed==true){
                    tecla=false;
                    interactuar=true;
                }
        }        
        //colision Casillas
        colision=false;
        gp.colisiones.revisarColision(this);
    
                //colision NPC

                intereaccionNCP(gp.colisiones.chequeoEntidades(this, gp.NPC));
                //colision objetos

                recogerobjetos(gp.colisiones.chequeoObjetos(this, true));
            
                //colision eventos
                gp.controlEventos.chequeoEvento();
            
            
                if(colision==false && interactuar==false){
                    switch (direction) {
                            case "up" -> {yMapa-=vel;
                                hitBox.x=16;
                                hitBox.width=30;
                            }
                            case "left" -> {
                                xMapa-=vel;
                                hitBox.x=14;
                                hitBox.width=36;
                            }
                                
                            case "down" ->{ 
                                yMapa+=vel;
                                hitBox.x=14;                                                            
                                hitBox.width=36;}
                            
                            case "right" -> {
                                xMapa+=vel;
                                hitBox.x=16;                              
                                hitBox.width=30;
                            }
                    }
                }
                
                Packet02Mov packet=new Packet02Mov(username, this.xMapa, this.yMapa,this.direction);
                packet.writeData(PanelJuego.juego.socketcliente);
                
                spriteCounter++;
                if (spriteCounter>10){
                    if (spriteNum == 2 ){
                        spriteNum=1;
                    }else{
                        if (spriteNum == 1)
                        spriteNum=2;
                    }
                    spriteCounter = 0;
                }
    }

    public void estadoTienda(){
        if(tecla==true){
            if(keyH.upPressed==true){

            }else if(keyH.downPressed==true){

            }
            if(keyH.rightPressed==true){
                gp.efectos(7);
                gp.hud.opcion++;
                tecla=false;
                if( gp.hud.opcion>4){
                    gp.hud.opcion=0;
                }
            }else if(keyH.leftPressed==true){
                gp.efectos(7);
                gp.hud.opcion--;
                tecla=false;
                if( gp.hud.opcion<0){
                    gp.hud.opcion=4;
                }
            }else if(keyH.ePressed==true){
                gp.efectos(7);
                tecla=false;
                if(gp.hud.opcion==0){
                    estado=estadojuego;
                }else{
                    int cont=0;
                    
                    for(Objetosclase obj:gp.NPC[0][1].inventario){
                        
                        if(obj!=null && cont==(gp.hud.opcion-1) ){
                            //System.out.println(obj.nombre);
                            if(!(obj.getPrecio()>this.cantInventario[4])&&(gp.NPC[2][1].inventario[cont]!=null)){
                                
                                this.cantInventario[4]=this.cantInventario[4]-obj.getPrecio();
                                switch(obj.nombre){
                                    case "calvo" -> {
                                        this.sombreros[0]= "calvo";
                                        gp.NPC[2][1].inventario[cont]=null;
                                        this.sombreroactual=this.sombreros[0];
                                        getsombre();
                                    }
                                    case "gCopa" -> {
                                        this.sombreros[1]="gCopa";
                                        gp.NPC[2][1].inventario[cont]=null;
                                        this.sombreroactual=this.sombreros[1];
                                        getsombre();
                                    }
                                    case "gPlaya" -> {
                                        this.sombreros[2]="gPlaya";
                                        gp.NPC[2][1].inventario[cont]=null;
                                        this.sombreroactual=this.sombreros[2];
                                        getsombre();
                                    }
                                    case "botas" -> {
                                        if(this.inventario[0]==null){
                                            gp.efectos(4);
                                            gp.hud.mostrarmensaje("conseguiste "+obj.nombre);
                                            vel=vel+2;
                                            this.inventario[0]=new ObjetoEquipo("Botas", 0, 0, gp);
                                            gp.NPC[2][1].inventario[cont]=null;
                                        }
                                    }
                                }
                            }else{
                                
                                if(gp.NPC[2][1].inventario[cont]==null){
                                    gp.hud.mostrarmensaje("Ya compraste esto");
                                }else{
                                    gp.hud.mostrarmensaje("Es muy caro");
                                }
                            }    
                        }
                        cont++;
                    }
                }    
            }
        }    
        gp.hud.mostrartienda();
    }
            
    public void recogerobjetos(int id){
        if(id!=999){
            //usa el nombre del objeto para saber con cual objeto colisiona 
            String objnombre=gp.obj[gp.mapaActual][id].nombre;
            
            //switch para el nombre
            //nota se puede usar un getclass para saber el tipo o usar 
            switch(objnombre){
                /*
                case "llave" -> {
                    //llaves++;
                    
                    this.cantInventario[1]++;
                    gp.efectos(2);
                    gp.hud.mostrarmensaje("conseguiste una llave");
                    this.inventario[1]=gp.obj[gp.mapaActual][id];
                    gp.obj[gp.mapaActual][id]=null;
                    System.out.println("llaves: "+this.cantInventario[1]);
                }
                case "puerta" -> {
                        if(this.cantInventario[1]>0){
                            this.cantInventario[1]--;
                            gp.efectos(5);
                            gp.hud.mostrarmensaje("puerta abierta");
                            gp.obj[gp.mapaActual][id]=null;
                            if(this.cantInventario[1]==0){
                                this.inventario[1]=null;
                            }
                        }else{
                            gp.hud.mostrarmensaje("no tienes llaves para esta puerta");
                            if(this.cantInventario[1]==0){
                                this.inventario[1]=null;
                            }
                        }
                    } */   
                case "botas" -> {
                    if(this.inventario[0]==null){
                        gp.efectos(4);
                        gp.hud.mostrarmensaje("conseguiste "+objnombre);
                        vel=vel+2;
                        this.inventario[0]=gp.obj[gp.mapaActual][id];
                        gp.obj[gp.mapaActual][id]=null;
                    }
                }
                case "cofre" -> {
                    gp.hud.victoriamensaje=true;
                    gp.obj[gp.mapaActual][id]=null;
                }
                case "coin" -> {
                    this.cantInventario[4]++;
                    gp.efectos(2);
                    gp.hud.victoriamensaje=true;
                    gp.obj[gp.mapaActual][id]=null;
                }
                case "semilla" -> {
                    this.cantInventario[1]++;
                    gp.efectos(6);
                    this.inventario[1]=gp.obj[gp.mapaActual][id];
                    gp.hud.victoriamensaje=true;
                    gp.obj[gp.mapaActual][id]=null;
                }
                case "basura" -> {
                    this.cantInventario[2]++;
                    gp.efectos(6);
                    this.inventario[2]=gp.obj[gp.mapaActual][id];
                    gp.hud.victoriamensaje=true;
                    gp.obj[gp.mapaActual][id]=null;
                }
            }
        }
    }
    
    public void recogerObjeto(int i){
        if(i!=999){
            gp.obj[gp.mapaActual][i]=null;
        }
    }
          
    public void intereaccionNCP(int id) {
        if(id!=999){
            if (interactuar==true){
                gp.efectos(7);
                if(gp.NPC[gp.mapaActual][id].movimiento==true){
                    if("right".equals(direction)){
                        gp.NPC[gp.mapaActual][id].direction="left";
                    }
                    if("left".equals(direction)){
                        gp.NPC[gp.mapaActual][id].direction="right";
                    }
                    if("up".equals(direction)){
                        gp.NPC[gp.mapaActual][id].direction="down";
                    }
                    if("down".equals(direction)){
                        gp.NPC[gp.mapaActual][id].direction="up";
                    }
                }
                switch (gp.NPC[gp.mapaActual][id]) {
                    case Tienda  aux -> {
                        gp.hud.mostrarmensaje("tienda");
                        estado=estadotienda;
                        }
                    case PuertaInteractuable  aux -> {
                            if(aux.Ztp==2){
                                gp.controlEventos.musicatienda();
                            }else if(gp.mapaActual==2){
                                gp.controlEventos.musicatienda();
                            }
                            gp.controlEventos.tpinteractuar(aux.Xtp,aux.Ytp, aux.Ztp);
                        }
                    case Papelera  aux -> {
                            if(this.cantInventario[2]!=0){
                                gp.hud.mostrarmensaje("depositaste: "+this.cantInventario[2]+" bolsas de basura");
                                this.inventario[2]=null;
                                this.cantInventario[2]=0;
                            }else{
                                gp.hud.mostrarmensaje(gp.NPC[gp.mapaActual][id].mensaje);
                            }
                        }
                    case Agujero  aux -> {
                            if(aux.estado=="Agujerovacio"){
                                if(this.cantInventario[1]!=0){
                                    this.cantInventario[1]--;
                                    if(this.cantInventario[1]==0){
                                        this.inventario[1]=null;
                                    }
                                    gp.hud.mostrarmensaje("Plantaste un Arbol");
                                    aux.estado="Agujerolleno";
                                    aux.getImage();
                                }else{
                                    gp.hud.mostrarmensaje(aux.mensaje);
                                }
                            }else{
                                gp.hud.mostrarmensaje("Espero que este Arbol cresca ");
                            }
                        }
                    case Tonina  aux -> {
                            if(gp.minijuego[3][0].Terminado==true){
                                if(aux.misioncumplida==false){
                                    gp.hud.mostrarmensaje("Muchas gracias, ten estas U.N.D");
                                    aux.misioncumplida=true;
                                    this.cantInventario[4]=this.cantInventario[4]+10;
                                }else{
                                    gp.hud.mostrarmensaje("Muchas gracias de nuevo");
                                }   
                            }else{
                                if(gp.minijuego[3][0].Empezado==false){
                                    gp.hud.mostrarmensaje("Hola, me ayudas a limpiar el agua? Usa esta red");
                                    this.inventario[3]= new ObjetoRecogible("Red", 1, 1, gp);
                                    gp.mini.activarmini(3, 0);
                                }else if(gp.minijuego[3][0].Empezado==true){
                                    gp.minijuego[3][0].interacion();
                                }
                            }
                        }
                    case Turpial  aux -> {
                            if(gp.minijuego[5][0].Terminado==true){
                                if(aux.misioncumplida==false){
                                    gp.hud.mostrarmensaje("Gracias, toma estas U.N.D");
                                    aux.misioncumplida=true;
                                    this.cantInventario[4]=this.cantInventario[4]+10;
                                }else{
                                    gp.hud.mostrarmensaje("Gracias de nuevo");
                                }
                            }else{
                                if(gp.minijuego[5][0].Empezado==false){
                                    gp.hud.mostrarmensaje("Hola, Podrias sembrar algunos arboles?");
                                    gp.mini.activarmini(5, 0);
                                }else if(gp.minijuego[5][0].Empezado==true){
                                    gp.minijuego[5][0].interacion();
                                }
                            }
                        }
                    case Zamuro  aux -> {
                            if(gp.minijuego[4][0].Terminado==true){
                                if(aux.misioncumplida==false){
                                    gp.hud.mostrarmensaje("Toma estas U.N.D");
                                    aux.misioncumplida=true;
                                    this.cantInventario[4]=this.cantInventario[4]+10;
                                }else{
                                    gp.hud.mostrarmensaje("Ahora esa basura es toda mia");
                                }
                            }else{
                                if(gp.minijuego[4][0].Empezado==false){
                                    gp.hud.mostrarmensaje("Oye, recoge esa basura por mi te dare U.N.D");
                                    gp.mini.activarmini(4, 0);
                                }else if(gp.minijuego[4][0].Empezado==true){
                                    gp.minijuego[4][0].interacion();
                                }
                            }
                        }
                    case Aguaconbasura aux ->{
                        if("Aguasucia".equals(aux.estado) && this.inventario[3]!=null){
                                    gp.hud.mostrarmensaje("Recogiste la basura");
                                    aux.estado="Agua";
                                    aux.getImage();
                        }else{
                                gp.hud.mostrarmensaje(aux.mensaje);
                            
                        }
                    }
                    default -> {
                        gp.hud.mostrarmensaje(gp.NPC[gp.mapaActual][id].mensaje);
                    }   
                       
                }
                
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
       
        if (gp.jugador.getMapa()==this.mapa) {   
            if(gp.jugador.equals(this)) {
                g2.drawImage(image,pantallaX,pantallaY,null);  
                    if(sombreroactual!="ninguno"){
                        g2.drawImage(sombrero,pantallaX,pantallaY,null);
                    }
                
                if (username != null){
                    Fuentes tipoFuente=new Fuentes();
                    g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,20)));
                    int textX = pantallaX + (image.getWidth() - g2.getFontMetrics().stringWidth(username))/2 ;
                    int textY = pantallaY - 5;

                    //Bordes Negros//
                     g2.setColor(Color.BLACK);
                     g2.drawString(username, textX - 2, textY - 2);
                     g2.drawString(username, textX - 2, textY + 2);
                     g2.drawString(username, textX + 2, textY - 2);
                     g2.drawString(username, textX + 2, textY + 2);
                     g2.drawString(username, textX, textY - 2);
                     g2.drawString(username, textX, textY + 2);
                     g2.drawString(username, textX - 2, textY);
                     g2.drawString(username, textX + 2, textY);

                     //Letras Blancas//
                     g2.setColor(Color.WHITE);
                     g2.drawString(username, textX, textY);




                }
            }else
            {
                int dibX=gp.jugador.pantallaX+this.xMapa-gp.jugador.xMapa;
                int dibY=gp.jugador.pantallaY+this.yMapa-gp.jugador.yMapa;
               // g2.drawImage(image,xMapa,yMapa,null);
                g2.drawImage(image,dibX,dibY,null);


                if (username != null){
                    Fuentes tipoFuente=new Fuentes();
                    g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,20)));
                    int textX = dibX + (image.getWidth() - g2.getFontMetrics().stringWidth(username))/2 ;
                    int textY = dibY - 5;

                    //Bordes Negros//
                     g2.setColor(Color.BLACK);
                     g2.drawString(username, textX - 2, textY - 2);
                     g2.drawString(username, textX - 2, textY + 2);
                     g2.drawString(username, textX + 2, textY - 2);
                     g2.drawString(username, textX + 2, textY + 2);
                     g2.drawString(username, textX, textY - 2);
                     g2.drawString(username, textX, textY + 2);
                     g2.drawString(username, textX - 2, textY);
                     g2.drawString(username, textX + 2, textY);

                     //Letras Blancas//
                     g2.setColor(Color.WHITE);
                     g2.drawString(username, textX, textY);
                }

            }
        }

    }
}
