package br.senai.sp.jandira.tabuada.ui;

import br.senai.sp.jandira.tabuada.model.Usuario;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Optional;

public class TelaTabuada extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Definir tamanho da tela
        // stage.setWidth(500);
        // stage.setHeight(500);
        stage.setTitle("Tabuada");
        stage.setResizable(false);

        //Criar o root - componente de layout principal
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #2e5ef5;");

        //Criação da cena com o root dentro dela
        Scene scene = new Scene(root);

        //Criação do header da tela
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #1546dc;");

        //Conteúdo do header
        //Titulo
        Label lbltitulo = new Label("Tabuada");
        lbltitulo.setStyle("-fx-text-fill: white;-fx-font-size: 20px;-fx-font-weight: bold;");
        lbltitulo.setPadding(new Insets(8, 8, 0, 8));

        //Subtitulo
        Label lblSubtitulo = new Label("Crie um tipo de tabuada.");
        lblSubtitulo.setPadding(new Insets(0, 8, 8, 8));
        lblSubtitulo.setStyle("-fx-text-fill: white;");

        //Colocando os labels dentro do header
        header.getChildren().addAll(lbltitulo,lblSubtitulo);

        //Criação do grid de formulário
        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);
        gridFormulario.setPadding(new Insets(16, 16, 16, 8));
        gridFormulario.setStyle("-fx-background-color: #2e5ef5;");

        //Colocando o conteúdo do gridFormulario
        Label lblMultiplicando = new Label("Multiplicando:");
        TextField tfMultiplicando = new TextField();
        lblMultiplicando.setStyle("-fx-text-fill: white;");
        Label lblMenorMultiplicador = new Label("Menor Multiplicador:");
        TextField tfMenorMultiplicador = new TextField();
        lblMenorMultiplicador.setStyle("-fx-text-fill: white;");
        Label lblMaiorMultiplicador = new Label("Maior Multiplicador:");
        TextField tfMaiorMultiplicador = new TextField();
        lblMaiorMultiplicador.setStyle("-fx-text-fill: white;");

        //Colocando os componentes no gridFormulario
        gridFormulario.add(lblMultiplicando,0,0);
        gridFormulario.add(tfMultiplicando,1,0);
        gridFormulario.add(lblMenorMultiplicador,0,1);
        gridFormulario.add(tfMenorMultiplicador,1,1);
        gridFormulario.add(lblMaiorMultiplicador,0,2);
        gridFormulario.add(tfMaiorMultiplicador,1,2);

        //Criação da caixa dos botões
        Pane paneButtons = new Pane();
        paneButtons.setPadding(new Insets(16, 0, 16, 8));

        HBox boxBotoes = new HBox();
        boxBotoes.setSpacing(10);
        boxBotoes.setPadding(new Insets(8));
        paneButtons.getChildren().add(boxBotoes);

        //Colocando o conteúdo do boxBotoes
        Button btCalcular = new Button("Calcular");
        Button btLimpar = new Button("Limpar");
        Button btSair = new Button("Sair");

        //Colocando os componentes no boxBotoes
        boxBotoes.getChildren().add(btCalcular);
        boxBotoes.getChildren().add(btLimpar);
        boxBotoes.getChildren().add(btSair);

        //Criação da caixa de resultado
        VBox boxResultados = new VBox();
        //boxResultados.setPrefHeight(100);

        //Colocando o conteúdo do boxResultado
        Label lblResultados = new Label("Resultado:");
        lblResultados.setPadding(new Insets(8, 8, 8, 8));
        lblResultados.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold");
        ListView listaTabuada = new ListView();
        listaTabuada.setPadding(new Insets(8));
        listaTabuada.setPrefHeight(200);
        listaTabuada.setPrefWidth(100);
        listaTabuada.setStyle("-fx-background-color: #0092fc;");

        //Colocar os componentes do boxResultados
        boxResultados.getChildren().add(lblResultados);
        boxResultados.getChildren().add(listaTabuada);

        //Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(paneButtons);
        root.getChildren().add(boxResultados);

        //Colocamos a cena no palco
        stage.setScene(scene);

        stage.show();

        //Botão Calcular
        btCalcular.setOnAction(e -> {
            Usuario tabuada = new Usuario();

            tabuada.multiplicando =
                    Integer.parseInt(tfMultiplicando.getText()); // Transforma a String em Int

            tabuada.multiplicadorInicial =
                    Integer.parseInt(tfMenorMultiplicador.getText());

            tabuada.multiplicadorFinal =
                    Integer.parseInt(tfMaiorMultiplicador.getText());

            String[] resultado = tabuada.calcularInformacoes();
            listaTabuada.getItems().clear();
            listaTabuada.getItems().addAll(resultado);

            //Gravar os dados das tabuada em arquivo
            Path arquivo = Path.of("C:\\Users\\25203696\\DS1T\\tabuada\\dados_tabuada.csv");

            String dados = tfMultiplicando.getText() + ";" + tfMenorMultiplicador.getText() + ";" + tfMaiorMultiplicador.getText() + ";" + LocalDateTime.now() + "\n";

            try{
                Files.writeString(arquivo,dados,StandardOpenOption.APPEND); // APPEND é acrescentar
            }catch (IOException erro){
                System.out.println(erro.getMessage());
            }
        });

        //Botão Limpar
        btLimpar.setOnAction(e -> {
            tfMultiplicando.clear();
            tfMenorMultiplicador.clear();
            tfMaiorMultiplicador.clear();
            listaTabuada.getItems().clear();
            tfMultiplicando.requestFocus();
        });

        //Botão Sair
        btSair.setOnAction(e -> {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Você quer sair do programa?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> resposta = alerta.showAndWait();
            if (resposta.get() == ButtonType.YES) {
                System.exit(0);
            }
        });
    }
}