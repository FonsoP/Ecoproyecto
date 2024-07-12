package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
/**
 *
 * @author C-A-F
 */
public class ObjetoRecogible extends Objetosclase{
    
    public ObjetoRecogible(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        if(nombre.equals("Calvo")||nombre.equals("GCopa")||nombre.equals("GPlaya")){
            down1= this.configuracion("/gorros/"+nombre);
        }else {
            down1= this.configuracion("/objetos/"+nombre);
        }
        colision=false;
        
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    
    
    @Override
    public int getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    

    
}