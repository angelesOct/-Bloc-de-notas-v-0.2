/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import javax.swing.JOptionPane;
    import javax.swing.JFileChooser;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import models.ModelBloc;
    import views.ViewBlocNotas;
/**
 *
 * @author Octaviano
 */
public class ControllerBloc {
   ModelBloc modelBlock;
   ViewBlocNotas vieBlocNotas;
   
   ActionListener al = new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e) {
          if(e.getSource()== vieBlocNotas.jmi_leer) jmi_leer_action_performed();
          if(e.getSource()== vieBlocNotas.jmi_guardar) jmi_esribir_action_performed();   
       }   
   };

    public ControllerBloc(ModelBloc modelBlock, ViewBlocNotas vieBlocNotas) {
        vieBlocNotas.setVisible(true);
        this.modelBlock = modelBlock;
        this.vieBlocNotas = vieBlocNotas;
        this.vieBlocNotas.jmi_leer.addActionListener(al);
        this.vieBlocNotas.jmi_guardar.addActionListener(al);
    }
    /**
     * En este metodo se llama al metodo de readFile 
     * y se le agrega su parametro para que pueda realizar su funcion de manera correcta
     * @param path 
     */
    public void jmi_leer_action_performed(){
        this.readFile();
    }
    /**
     * En este metodo se llama al metodo de writeFile
     * y se le agrega su parametro para que pueda realizar su funcion de manera correcta
     */
    public void jmi_esribir_action_performed(){
        this.writeFile(vieBlocNotas.jta_contenido.getText());
    }
    /**
     * En este metodo se lee un archivo
     * El archivo se vera en un TextArea 
     * @param path 
     */
    public String readFile(){
        try{  
            String acumula_lineas ="";
            String row; // indica una fila
            try {
                JFileChooser fileChooser = new JFileChooser(); //muestra directamente la venatana de JFileChosser
                int seleccion = fileChooser.showOpenDialog(vieBlocNotas.jta_contenido); 
                
                File archivo = fileChooser.getSelectedFile();
                if (archivo != null){
                    FileReader archivos = new FileReader(archivo);
                    BufferedReader bufferedReader = new BufferedReader(archivos);
                        while ((row=bufferedReader.readLine())!= null){
                            
                           acumula_lineas+=row+"\n"; // si el archivo tiene mas filas con esta linea ira mostrando cada una de ellas
                        }
                        vieBlocNotas.jta_contenido.setText(acumula_lineas); //abre el archivo en la TextArea
                        bufferedReader.close();
                }
                

        } catch(FileNotFoundException err){
            JOptionPane.showMessageDialog(null,"File not found"+err.getMessage());
        }
            
        } catch(IOException err){
            JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
        } 
       return null;
        
    }//ReadFile    
    /**
     * Escribe nuevas lineas en el archivo
     */
    public String writeFile (String mensaje) {
        try{
            JFileChooser file=new JFileChooser();
            file.showSaveDialog(vieBlocNotas.jta_contenido);
            File guardar =file.getSelectedFile();
            if(guardar !=null){
              FileWriter  save=new FileWriter(guardar);
              save.write(mensaje);
              save.close();
              JOptionPane.showMessageDialog(null,"El archivo se a guardado Exitosamente");
              }              
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"File not found:"+e.getMessage());
        } 
       return null;   
    }//WriteFile    
}//class
