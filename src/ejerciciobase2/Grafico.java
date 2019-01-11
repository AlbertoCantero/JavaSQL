package ejerciciobase2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Grafico extends javax.swing.JFrame {

    Connection conexion;
    Statement sentencia;
    
    public Grafico() {
        initComponents();
        PrepararBaseDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jButton1.setText("Ver Datos Servicios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jButton2.setText("Ver Datos Clientes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
        String info="";
        String cadfecha;
        String caddia;
        String cadmes;
        String cadanio;
        String cadcoste;
        try{
        
            ResultSet r=sentencia.executeQuery("select * from servicios order by cantidad");
            r.beforeFirst();
            while (r.next()){
                cadfecha=r.getString("fecha");
                cadanio=cadfecha.substring(0,4);
                cadmes=cadfecha.substring(5,7);
                caddia=cadfecha.substring(8,10);
                cadfecha=caddia+"/"+cadmes+"/"+cadanio;
                
                cadcoste=r.getString("cantidad");
                cadcoste=cadcoste.replace('.',',');
                
                info=info+cadfecha+" "+r.getString("tipo")+" "+cadcoste+"\n";
            }
            JOptionPane.showMessageDialog(null,info);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al consultar la tabla servicios");
        
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       
        try{
        conexion.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"No se pudo cerrar la conexión con la base de datos");
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String info="";
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Grafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables

    private void PrepararBaseDatos() {
        
        String DBURL="jdbc:mysql://localhost/manempsa";
        String usuario="root";
        String password="";
        
        //Cargar el controlador
        try{
        
            String controlador="com.mysql.jdbc.Driver";
            Class.forName(controlador).newInstance();
        
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error al cargar el controlador");
        }
           
        //Crear el objeto sentencia
        try{
         
            conexion=DriverManager.getConnection(DBURL,usuario,password);
             
        }catch(SQLException ex){
         
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null,ex);
             
        }  
        
        try{
            sentencia=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch(SQLException ex){
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
}
