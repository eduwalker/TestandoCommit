package org.example;

import org.example.modeloOlx.OlxModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppOlx {

    public static void main(String[] args) throws SQLException {





        Scanner entrada = new Scanner(System.in);

        //INICIANDO UM CONTROLADOR E SETANDO URL.
        ChromeDriver navegador = new ChromeDriver();


        navegador.get("https://mt.olx.com.br/imoveis");
        //navegador.get("https://www.olx.com.br/");

        //CONFIGURANDO PARA EXTRAIR TITULO E URL.
        String title = navegador.getTitle();
        navegador.getCurrentUrl();


        //LOCALIZANDO CAIXA DE PESQUISA
       // WebElement busca = navegador.findElement(By.id("searchtext"));

        //DEFININDO UM METODO DE PESQUISA E DIGITACAO
       // String pesquisa = JOptionPane.showInputDialog("Oque deseja pesquisar no OLX ?");
        //busca.sendKeys("Casas");

        //LOCALIZANDO BOTAO DE PESQUISAR E CLICANDO
       // WebElement btPesquisa = navegador.findElement(By.className("searchSubmitBtn"));
       // btPesquisa.click();



        //SELECIONANDO TABELA MATRIZ
        WebElement elemento = navegador.findElement(By.cssSelector("#ad-list"));

        //List<WebElement> elementList = (List<WebElement>) elemento.findElement(By.cssSelector("li"));
        List<WebElement> listElement = (List<WebElement>) elemento.findElements(By.cssSelector("li"));

        System.out.println(listElement);

            List<OlxModel> olxModel = new ArrayList<>();

        listElement.forEach(el -> {
                        OlxModel modelo = new OlxModel();
                       String[] linhas = el.getText().split("\n");

                       if(linhas.length < 7) {
                           return;
                       }
                        modelo.qtdFotos = Integer.valueOf(linhas[0]);
                        modelo.descricao = linhas[1];
                        modelo.area = linhas[2];
                        modelo.valor = linhas[3];
                        modelo.profile = linhas[4];
                        modelo.bairro = linhas[5];
                        modelo.dataHora = linhas[6];
                        olxModel.add(modelo);
                }

        );


        for (OlxModel olx: olxModel) {
            System.out.println("\n - - - - - -Anuncio - - - - - -");
            System.out.println("Quantidade de Fotos " + olx.qtdFotos);
            System.out.println("Description: " + olx.descricao);
            System.out.println("Tamanho: " + olx.area);
            System.out.println("Valor: " + olx.valor);
            System.out.println("Perfil: " + olx.profile);
            System.out.println("Localization: " + olx.bairro);
            System.out.println("Data do Anuncio: " + olx.dataHora);

        }


       // navegador.quit();
        entrada.close();
    }
}
