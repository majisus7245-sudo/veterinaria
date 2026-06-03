package controllers;

import models.*;
import vistas.*;

//Controlador para manejar vistas
public class MainController {
  private ViewLogin vistaLogin;
  public MainController(){
    vistaLogin = new ViewLogin(this);
  }
  public void mostrarAplicacion(){
    vistaLogin.setVisible(true);
  }
  public void redireccional(Usuario usuario){
    if("1".equals(usuario.getRol())){
      ViewRecepcionista recepcionista = new ViewRecepcionista();
      RegistroController controlador = new RegistroController(recepcionista);
      recepcionista.setVisible(true);
      vistaLogin.dispose();
      return;
    }
  }
}
