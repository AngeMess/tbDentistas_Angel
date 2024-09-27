package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlDentistas;
import vista.Dentista; 


class ctrlDentistas implements MouseListener, KeyListener{
    
        
    private mdlDentistas modelo;
    private Dentista vista;
    
    public ctrlDentistas(mdlDentistas modelo, Dentista vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnEditar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.tbDentistas.addMouseListener(this);
        
        modelo.Mostrar(vista.tbDentistas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar){
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    modelo.setNombre_Dentista(vista.txtNombre.getText());
                    modelo.setEdad_Dentista(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Dentista(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo_Dentista(vista.txtCorreo.getText());
                    modelo.Guardar();
                    
                    modelo.Mostrar(vista.tbDentistas);
                    modelo.limpiar(vista);
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbDentistas);
                modelo.Mostrar(vista.tbDentistas);
                modelo.limpiar(vista);
            }
        }
        
        if (e.getSource() == vista.btnEditar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre_Dentista(vista.txtNombre.getText());
                    modelo.setEdad_Dentista(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Dentista(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo_Dentista(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.tbDentistas);
                    modelo.Mostrar(vista.tbDentistas);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbDentistas) {
            modelo.cargarDatosTabla(vista);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
             if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.tbDentistas, vista.txtBuscar);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
