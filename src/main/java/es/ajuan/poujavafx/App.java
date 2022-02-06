package es.ajuan.poujavafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    Pane root = new Pane();
    ImageView fondo1; //IMAGEN DE FONDO1
    ImageView fondo2; //IMAGEN DE FONDO2
    ImageView pou; //IMAGEN DE PERSONAJE
    ImageView fresa; //IMAGEN DE FRUTA1
    ImageView naranja; //IMAGEN DE FRUTA2
    ImageView pizza; //IMAGEN DE FRUTA3
    ImageView pelota; //IMAGEN DE PELOTA
    
    //CREACION DE RECTANGULOS
    Rectangle rectPou = new Rectangle(80, 80);
    Rectangle rectFresa = new Rectangle(40, 40);
    Rectangle rectPizza = new Rectangle(40, 40);
    Rectangle rectPelota = new Rectangle(40, 40);
    Rectangle rectNaranja = new Rectangle(40, 40);
    
    //CREACION DE GRUPOS
    Group groupPou = new Group();
    Group groupPizza = new Group();
    Group groupNaranja = new Group();
    Group groupFresa = new Group();
    Group groupPelota = new Group();
    Label labelPuntos;
    
    
    //VARIABLES
    int score = 0;
    final int SCENE_TAM_X = 400; //TAMAÑO DE LA ESCENA X
    final int SCENE_TAM_Y = 600; //TAMAÑO DE LA ESCENA Y
    double fondo1PositionY = 0; //POSICION Y DEL FONDO1
    double fondo2PositionY = 600; //POSICION Y DEL FONDO2
    int pouCurrentSpeed = 0; //VELOCIDAD DEL POU
    int pouPositionX = 150; //POSICION X DEL POU
    int pouPositionY = 500; //POSICION Y DEL POU
    int pizzaPositionX = 50; //POSICION X DE LA PIZZA
    int pizzaPositionY = -60; //POSICION Y DE LA PIZZA
    int naranjaPositionX = 150; //POSICION X DE LA NARANJA
    int naranjaPositionY = -30; //POSICION Y DE LA NARANJA
    int pelotaPositionX = 250; //POSICION X DE LA PELOTA
    int pelotaPositionY = -100; //POSICION Y DE LA PELOTA
    int fresaPositionX = 350; //POSICION X DE LA FRESA
    int fresaPositionY = -200; //POSICION Y DE LA FRESA
    int numRandom1 = 0;
    int numRandom2 = 0;
    int numRandom3 = 0;
    int numRandom4 = 0;
    int  velocidad = 2;
    
    //METODO PARA EL LABEL DE PUNTOS DE LA PARTIDA
    private void crearLabelPuntos(){
        labelPuntos = new Label("PUNTOS: "+score);
        Font font = Font.font("Arial Black", FontWeight.BOLD, FontPosture.REGULAR, 25);
        labelPuntos.setFont(font); //ASIGNAMOS UNA FUENTE AL LABEL
        labelPuntos.setTextFill(Color.WHITE); //COLOR DEL LABEL
        labelPuntos.setTranslateX(150); //POSICION X DEL LABEL
        labelPuntos.setTranslateY(25); //POSICION Y DEL LABEL
        root.getChildren().add(labelPuntos); //AÑADIMOS EL LABEL AL ROOT
    }
    //METODO PARA EL CAMBIO DE PUNTOS DE LA PARTIDA
    private void cambiarLabelPuntos(){
        labelPuntos.setText("");
        labelPuntos.setText("PUNTOS: "+score);
    }
    
    //METODO PARA EL REINICIO DE LA PARTIDA
    private void resetGame() {
        score = 0;
        fondo1PositionY = 0;
        fondo2PositionY = 600;
        pouCurrentSpeed = 0;
        pouPositionX = 150;
        pouPositionY = 500;
        pizzaPositionX = 50;
        pizzaPositionY = -60;
        naranjaPositionX = 150;
        naranjaPositionY = -30;
        pelotaPositionX = 250;
        pelotaPositionY = -100;
        fresaPositionX = 350;
        fresaPositionY = -200;
        numRandom1 = 0;
        numRandom2 = 0;
        numRandom3 = 0;
        numRandom4 = 0;
        
      }
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, SCENE_TAM_X, SCENE_TAM_Y); //CREACION DE LA ESCENA
        stage.setTitle("POU"); //ASIGNAMOS UN NOMBRE A LA ESCENA
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false); //SU FUNCION ES QUE NO SE PUEDA REDIMENSIONAR LA ESCENA YA QUE QUEDARIA DESCUADRADO.
        Image fondoImg = new Image(getClass().getResourceAsStream("/images/fondo.png")); // CARGA LA IMAGEN DE FONDO
        Image pouImg = new Image(getClass().getResourceAsStream("/images/pou.png")); // CARGA LA IMAGEN DEL PERSONAJE
        Image pouEatImg = new Image(getClass().getResourceAsStream("/images/pouEat.png")); // CARGA LA IMAGEN DEL PERSONAJE COMIENDO
        Image pizzaImg = new Image(getClass().getResourceAsStream("/images/pizza.png")); // CARGA LA IMAGEN DE LA PIZZA
        Image naranjaImg = new Image(getClass().getResourceAsStream("/images/naranja.png")); // CARGA LA IMAGEN DE LA NARANJA
        Image fresaImg = new Image(getClass().getResourceAsStream("/images/fresa.png")); // CARGA LA IMAGEN FRESA
        Image pelotaImg = new Image(getClass().getResourceAsStream("/images/pelota.png")); // CARGA LA IMAGEN PELOTA
        fondo1 = new ImageView(fondoImg); // CREA EL OBJETO DEL FONDO1
        fondo2 = new ImageView(fondoImg); // CREA EL OBJETO DEL FONDO2
        root.getChildren().add(fondo1); // AÑADIMOS EL OBJETO AL ROOT
        pou = new ImageView(pouImg); // CREA EL OBJETO PERSONAJE
        pizza = new ImageView(pizzaImg); // CREA EL OBJETO PIZZA
        fresa = new ImageView(fresaImg); // CREA EL OBJETO FRESA
        naranja = new ImageView(naranjaImg); // CREA EL OBJETO NARANJA
        pelota = new ImageView(pelotaImg); // CREA EL OBJETO PELOTA
        root.getChildren().add(fondo2); // AÑADIMOS EL OBJETO FONDO2 AL ROOT
        root.getChildren().add(groupPou); // AÑADIMOS EL GRUPO POU AL ROOT
        groupPou.getChildren().addAll(pou,rectPou); //CREACION DEL GRUPO POU
        rectPou.setVisible(false); //RECTANGULO DE LAS COLISIONES DEL POU
        root.getChildren().add(groupPizza); // AÑADIMOS EL GRUPO PIZZA AL ROOT
        groupPizza.getChildren().addAll(pizza,rectPizza); //CREACION DEL GRUPO PIZZA
        rectPizza.setVisible(false); //RECTANGULO DE LAS COLISIONES DE LA PIZZA
        
        root.getChildren().add(groupFresa); // AÑADE EL OBJETO avion A LA PANTALLA
        groupFresa.getChildren().addAll(fresa,rectFresa); //CREACION DEL GRUPO FRESA
        rectFresa.setVisible(false); //RECTANGULO DE LAS COLISIONES DE FRESAS
        
        root.getChildren().add(groupNaranja); // AÑADE EL OBJETO avion A LA PANTALLA
        groupNaranja.getChildren().addAll(naranja,rectNaranja); //CREACION DEL GRUPO NARANJA
        rectNaranja.setVisible(false); //RECTANGULO DE LAS COLISIONES DE NARANJAS
        
        root.getChildren().add(groupPelota); // AÑADE EL OBJETO avion A LA PANTALLA
        groupPelota.getChildren().addAll(pelota,rectPelota); //CREACION DEL GRUPO PELOTA
        rectPelota.setVisible(false); //RECTANGULO DE LAS COLISIONES DE LA PELOTA
        
        //LLAMADA AL METODO DE LA CREACION DEL LABEL DE PUNTUACION
        crearLabelPuntos();
        
        //ASIGNACION DE POSICIONES DE LOS GRUPOS Y DEL FONDO
        fondo1.setLayoutY(fondo1PositionY);
        fondo2.setLayoutY(fondo2PositionY);
        groupPou.setLayoutX(pouPositionX);
        groupPou.setLayoutY(pouPositionY);
        groupNaranja.setLayoutX(naranjaPositionX);
        groupNaranja.setLayoutY(naranjaPositionY);
        groupFresa.setLayoutX(fresaPositionX);
        groupFresa.setLayoutY(fresaPositionY);
        groupPelota.setLayoutX(pelotaPositionX);
        groupPelota.setLayoutY(pelotaPositionY);
        groupPizza.setLayoutX(pizzaPositionX);
        groupPizza.setLayoutY(pizzaPositionY);
        
        //SCROLL DEL FONDO DE LA PANTALLA
        Timeline scrollFondo = new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      fondo1PositionY = fondo1PositionY -1;
                      fondo1.setLayoutY(fondo1PositionY);
                      fondo2PositionY = fondo2PositionY -1;
                      fondo2.setLayoutY(fondo2PositionY);
                      if (fondo1PositionY == -600) {
                          fondo1PositionY = 600;
                      } else if (fondo2PositionY == -600) {
                          fondo2PositionY = 600;
                      }
                  })
        );
        
        scrollFondo.setCycleCount(Timeline.INDEFINITE); // HACE QUE SE EJECUTE INDEFINIDAMENTE
        scrollFondo.play(); // EJECUTA EL SCROLL DEL FONDO
        
        //SCROLL DE LA CAIDA DE LOS OBJETOS
        Timeline scrollObjects = new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      fresaPositionY = fresaPositionY +6;
                      groupFresa.setLayoutY(fresaPositionY);
                      pelotaPositionY = pelotaPositionY +6;
                      groupPelota.setLayoutY(pelotaPositionY);
                      naranjaPositionY = naranjaPositionY +6;
                      groupNaranja.setLayoutY(naranjaPositionY);
                      pizzaPositionY = pizzaPositionY +6;
                      groupPizza.setLayoutY(pizzaPositionY);
                      if (fresaPositionY >= 630) {
                          fresaPositionY = -40;
                          numRandom1= (int) (Math.random() * 4) + 1; // GENERA UN NUMERO ALEATORIO ENTRE 1 Y 4
                          switch (numRandom1) {
                              case 1:
                                  groupFresa.setLayoutX(50);
                                  break;
                              case 2:
                                  groupFresa.setLayoutX(150);
                                  break;
                              case 3:
                                  groupFresa.setLayoutX(250);
                                  break;
                              case 4:
                                  groupFresa.setLayoutX(350);
                                  break;
                              default:
                                  break;
                          }
                          
                      }
                      if (naranjaPositionY >= 630) {
                          naranjaPositionY = -40;
                          numRandom2= (int) (Math.random() * 4) + 1;
                          switch (numRandom2) {
                              case 1:
                                  groupNaranja.setLayoutX(50);
                                  break;
                              case 2:
                                  groupNaranja.setLayoutX(150);
                                  break;
                              case 3:
                                  groupNaranja.setLayoutX(250);
                                  break;
                              case 4:
                                  groupNaranja.setLayoutX(350);
                                  break;
                              default:
                                  break;
                          }
                      }
                      if (pizzaPositionY >= 630) {
                          pizzaPositionY = -40;
                          numRandom3= (int) (Math.random() * 4) + 1;
                          switch (numRandom3) {
                              case 1:
                                  groupPizza.setLayoutX(50);
                                  break;
                              case 2:
                                  groupPizza.setLayoutX(150);
                                  break;
                              case 3:
                                  groupPizza.setLayoutX(250);
                                  break;
                              case 4:
                                  groupPizza.setLayoutX(350);
                                  break;
                              default:
                                  break;
                          }
                      }
                      if (pelotaPositionY >= 630) {
                          pelotaPositionY = -40;
                          numRandom4= (int) (Math.random() * 4) + 1;
                          switch (numRandom4) {
                              case 1:
                                  groupPelota.setLayoutX(50);
                                  break;
                              case 2:
                                  groupPelota.setLayoutX(150);
                                  break;
                              case 3:
                                  groupPelota.setLayoutX(250);
                                  break;
                              case 4:
                                  groupPelota.setLayoutX(350);
                                  break;
                              default:
                                  break;
                          }
                      }
                  })
        );
        
        scrollObjects.setCycleCount(Timeline.INDEFINITE); // DEFINE QUE SE EJECUTE INDEFINIDAMENTE
        scrollObjects.play(); // EJECUTA EL SCROLL DE CAIDA DE OBJETOS
        
        //ANIMACION DEL POU COMIENDO
        Timeline animacionComidaPou= new Timeline(
                  new KeyFrame(Duration.seconds(0.5), (ActionEvent ae) -> {
                      pou.setImage(pouImg); //ASIGNAMOS LA IMAGEN DEL POU COMIENDO
                  })
        );
        animacionComidaPou.setCycleCount(Timeline.INDEFINITE); // DEFINE QUE SE EJECUTE INDEFINIDAMENTE
        animacionComidaPou.play(); // EJECUTAR LA ANIMACION DEL POU COMIENDO
        
        //MOVIMIENTO DEL PERSONAJE
        scene.setOnKeyPressed((KeyEvent event) -> {
              switch(event.getCode()) {
                  case LEFT:
                      //PULSA LA TECLA HACIA LA IZQUIERDA
                      pouCurrentSpeed = -6;
                          
                      break;
                  case RIGHT:
                      //PULSA LA TECLA HACIA LA DERECHA
                          pouCurrentSpeed = 6;
                      break;
              }
        });
          scene.setOnKeyReleased((KeyEvent event) -> { // SI NO SE PULSA NINGUNA TECLA SE EJECUTA EL SIGUIENTE CODIGO
              pouCurrentSpeed = 0;
        });
          
          //TIMELINE DEL MOVIMIENTO DEL POU 
        Timeline movimientoPou= new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      pouPositionX += pouCurrentSpeed;
                      if(pouPositionX < 0){
                          pouPositionX = 0;
                      } else{
                          if(pouPositionX > 300){
                              pouPositionX = 300;
                          }
                      }
                      groupPou.setLayoutX(pouPositionX);
                  })
        );
        movimientoPou.setCycleCount(Timeline.INDEFINITE); // DEFINE QUE SE EJECUTE INDEFINIDAMENTE
        movimientoPou.play(); // EJECUTAR EL TIMELINE DEL MOVIMIENTO DEL POU
        
        
        //COLISIONES ENTRE EL PERSONAJE Y LSO OBJETOS
        Timeline detectarColision = new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      Shape colisionPelota = Shape.intersect(rectPou, rectPelota); // COMPRUEBA SI HAY COLISION ENTRE EL RECTANGULO DE LA PELOTA Y EL RECTANGULO DEL POU
                      boolean colisionVaciaPelota = colisionPelota.getBoundsInLocal().isEmpty(); // ALMACENA EN ESA VARIABLE TRUE O FALSE, DEPENDIENDO DE SI DETECTA O NO LA COLISION
                      if(colisionVaciaPelota == false){
                          System.out.println("HAS CHOCADO CON LA PELOTA"); //PINTA HAS CHOCADO CON LA PELOTA
                          
                          groupPelota.setLayoutY(3000); //SE LE ASIGNA AL GRUPO PELOTA LA Y EN 3000 PARA QUE SE VAYA FUERA DE LA PANTALLA.
                          groupPizza.setLayoutY(3000); //SE LE ASIGNA AL GRUPO PELOTA LA Y EN 3000 PARA QUE SE VAYA FUERA DE LA PANTALLA.
                          groupFresa.setLayoutY(3000); //SE LE ASIGNA AL GRUPO PELOTA LA Y EN 3000 PARA QUE SE VAYA FUERA DE LA PANTALLA.
                          groupNaranja.setLayoutY(3000); //SE LE ASIGNA AL GRUPO PELOTA LA Y EN 3000 PARA QUE SE VAYA FUERA DE LA PANTALLA.
                          resetGame(); //LLAMAMOS AL METODO DE REINICIO DE LA PARTIDA
                          cambiarLabelPuntos(); //LAMAMOS AL METODO DEL CAMBIO DE LA PUNTUACION
                      }
                      Shape colisionPizza = Shape.intersect(rectPou, rectPizza); // COMPRUEBA SI HAY COLISION ENTRE EL RECTANGULO DE LA PIZZA Y EL RECTANGULO DEL POU
                      boolean colisionVaciaPizza = colisionPizza.getBoundsInLocal().isEmpty(); // ALMACENA EN ESA VARIABLE TRUE O FALSE, DEPENDIENDO DE SI DETECTA O NO LA COLISION
                      if(colisionVaciaPizza == false){
                          pou.setImage(pouEatImg);
                          System.out.println("Has cogido la pizza"); //PINTA HAS COGIDO LA PIZZA
                          pizzaPositionY = 600; //LA PIZAA VUELVE A LA POSICION Y 600.
                          score += 1 ; // SUMA 1 A LA PUNTUACION
                          cambiarLabelPuntos(); //LAMAMOS AL METODO DEL CAMBIO DE LA PUNTUACION
                          System.out.println("Tienes "+score+" puntos"); //PINTA CUANTOS PUNTOS TENEMOS ACTUALMENTE
                      }
                      Shape colisionFresa = Shape.intersect(rectPou, rectFresa); // COMPRUEBA SI HAY COLISION ENTRE EL RECTANGULO DE LA FRESA Y EL RECTANGULO DEL POU
                      boolean colisionVaciaFresa = colisionFresa.getBoundsInLocal().isEmpty(); // ALMACENA EN ESA VARIABLE TRUE O FALSE, DEPENDIENDO DE SI DETECTA O NO LA COLISION
                      if(colisionVaciaFresa == false){
                          pou.setImage(pouEatImg);
                          System.out.println("Has cogido la fresa"); //PINTA HAS COGIDO LA FRESA
                          fresaPositionY = 600; //LA FRESA VUELVE A LA POSICION Y 600.
                          score += 1 ; // SUMA 1 A LA PUNTUACION
                          cambiarLabelPuntos(); //LAMAMOS AL METODO DEL CAMBIO DE LA PUNTUACION
                          System.out.println("Tienes "+score+" puntos"); //PINTA CUANTOS PUNTOS TENEMOS ACTUALMENTE
                      }
                      Shape colisionNaranja = Shape.intersect(rectPou, rectNaranja); // COMPRUEBA SI HAY COLISION ENTRE EL RECTANGULO DE LA NARANJA Y EL RECTANGULO DEL POU
                      boolean colisionVaciaNaranja = colisionNaranja.getBoundsInLocal().isEmpty(); // ALMACENA EN ESA VARIABLE TRUE O FALSE, DEPENDIENDO DE SI DETECTA O NO LA COLISION
                      if(colisionVaciaNaranja == false){
                          pou.setImage(pouEatImg);
                          System.out.println("Has cogido la naranja"); //PINTA HAS COGIDO LA NARANJA
                          naranjaPositionY = 600; //LA NARANJA VUELVE A LA POSICION Y 600.
                          score += 1 ; // SUMA 1 A LA PUNTUACION
                          cambiarLabelPuntos(); //LAMAMOS AL METODO DEL CAMBIO DE LA PUNTUACION
                          System.out.println("Tienes "+score+" puntos"); //PINTA CUANTOS PUNTOS TENEMOS ACTUALMENTE
                      }
                      
                  })
          );
        detectarColision.setCycleCount(Timeline.INDEFINITE); // DEFINIE QUE SE EJECUTE INDEFINIDAMENTE
        detectarColision.play(); // EJECUTAR EL TIMELINE DE LAS COLISIONES ENTRE EL PERSONAJE Y LOS OBJETOS
       
        
    }

    public static void main(String[] args) {
        launch();
    }

}