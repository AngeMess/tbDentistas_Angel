package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.Dentista;

public class mdlDentistas {
    private String nombre;
    private int edad;
    private Double peso;
    private String correo;
    
    public String getNombre_Dentista() {
        return nombre;
    }
    public void setNombre_Dentista(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad_Dentista() {
        return edad;
    }
    public void setEdad_Dentista(int edad) {
        this.edad = edad;
    }
    
    public Double getPeso_Dentista() {
        return peso;
    }
    public void setPeso_Dentista(Double peso) {
        this.peso = peso;
    }
    
    public String getCorreo_Dentista() {
        return correo;
    }
    public void setCorreo_Dentista(String correo) {
        this.correo = correo;
    }
    
    public void Guardar(){
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "INSERT INTO tbDentista(UUID_Dentista, Nombre_Dentista, Edad_Dentista, Peso_Dentista, Correo_Dentista) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, getNombre_Dentista());
            pstmt.setString(2, UUID.randomUUID().toString());
            pstmt.setInt(3, getEdad_Dentista());
            pstmt.setDouble(4, getPeso_Dentista());
            pstmt.setString(5, getCorreo_Dentista());
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
        public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Dentista", "Nombre_Dentista", "Edad_Dentista", "Peso_Dentista", "Correo_Dentista"});
        try {
            String query = "SELECT * FROM tbDentista";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Dentista"), 
                    rs.getString("Nombre_Dentista"), 
                    rs.getInt("Edad_Dentista"), 
                    rs.getDouble("Peso_Dentista"),
                    rs.getString("Correo_Dentista")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
        public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "delete from tbDentista where UUID_Dentista = ?";
            PreparedStatement deleteDentista = conexion.prepareStatement(sql);
            deleteDentista.setString(1, miId);
            deleteDentista.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
        
        public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
   
                String sql = "update tbDentista set Nombre_Dentista= ?, Edad_Dentista = ?, Peso_Dentista = ?, Correo_Dentista = ? where UUID_Dentista = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_Dentista());
                updateUser.setInt(2, getEdad_Dentista());
                updateUser.setDouble(3, getPeso_Dentista());
                updateUser.setString(4, getCorreo_Dentista());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
        
        public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = ClaseConexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Dentista", "Nombre_Dentista", "Edad_Dentista", "Peso_Dentista", "Correo_Dentista"});
        try {
            String sql = "SELECT * FROM tbDentista WHERE nombre LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("UUID_Dentista"), rs.getString("Nombre_Dentista"), rs.getInt("Edad_Dentista"), rs.getDouble("Peso_Dentista"), rs.getString("Correo_Dentista")});
            }

            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
        
       public void limpiar(Dentista vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
       
           public void cargarDatosTabla(Dentista vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbDentistas.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.tbDentistas.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.tbDentistas.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.tbDentistas.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.tbDentistas.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.tbDentistas.getValueAt(filaSeleccionada, 4).toString();

            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }
}
