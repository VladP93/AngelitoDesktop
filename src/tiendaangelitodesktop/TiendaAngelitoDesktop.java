/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendaangelitodesktop;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author vladi
 */
public class TiendaAngelitoDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Tema agregado
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TiendaAngelitoDesktop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TiendaAngelitoDesktop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TiendaAngelitoDesktop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TiendaAngelitoDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        FrmPrincipal frame = new FrmPrincipal();
        frame.setVisible(true);
    }
    
}
