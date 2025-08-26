package com.demoqa.steps;

import com.demoqa.hooks.Hooks;
import br.com.bookstore.automation.pages.FormPage;
import io.cucumber.java.pt.*;

public class FormSteps {

    private FormPage formPage;

    @Dado("que o usuário navega até o site")
    public void que_o_usuario_navega_ate_o_site() {
        Hooks.driver.get("https://demoqa.com/");
    }

    @Quando("clica no cartão Forms")
    public void clica_no_cartao_forms() {
        Hooks.driver.findElement(org.openqa.selenium.By.xpath("//h5[text()='Forms']")).click();
    }

    @Quando("seleciona a opção Practice Form")
    public void seleciona_a_opcao_practice_form() {
        Hooks.driver.findElement(org.openqa.selenium.By.xpath("//span[text()='Practice Form']")).click();
        formPage = new FormPage(Hooks.driver);
    }

    @Quando("preenche todos os campos com dados válidos")
    public void preenche_todos_os_campos_com_dados_validos() {
        formPage.preencherFormulario("Massa_Dados/arquivo.txt");
    }

    @Quando("submete o formulário")
    public void submete_o_formulario() {
        formPage.submeterFormulario();
    }

    @Então("um popup de sucesso deve ser exibido")
    public void um_popup_de_sucesso_deve_ser_exibido() {
        assert formPage.popupSucessoVisivel();
        formPage.fecharPopup();
    }
}