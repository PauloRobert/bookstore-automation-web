package com.demoqa.steps;

import com.demoqa.hooks.Hooks;
import com.demoqa.pages.FormPage;
import com.demoqa.utils.ConfigReader;
import io.cucumber.java.pt.*;
import org.openqa.selenium.TakesScreenshot;

public class FormSteps {

    private FormPage formPage;

    @Dado("que o usuário navega até o site")
    public void que_o_usuario_navega_ate_o_site() {
        String url = ConfigReader.getProperty("url");
        Hooks.driver.get(url);
        formPage = new FormPage(Hooks.driver);
    }

    @Quando("clica no cartão Forms")
    public void clica_no_cartao_forms() {
        formPage.acessarForms();
    }

    @Quando("seleciona a opção Practice Form")
    public void seleciona_a_opcao_practice_form() {
        formPage.acessarPracticeForm();
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