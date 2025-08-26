# Projeto de Automação Web – DemoQA

Automação de testes para o site DemoQA utilizando Java, Selenium WebDriver, Cucumber, Page Object Model e WebDriverManager.

### Tecnologias Utilizadas

* Java 17 
* Selenium WebDriver 4.x
* Cucumber 7.x
* WebDriverManager
* JUnit 5
* Faker (para geração de dados aleatórios)
* Page Object Model (para organização das páginas)

### Como Executar o Projeto

Clone o repositório
[git clone](https://github.com/PauloRobert/bookstore-automation-web)
`mvn clean install`

### Configure o navegador no arquivo config.properties
`browser=chrome`

`url=https://demoqa.com`
* Pode ser chrome, chrome-headless, firefox, firefox-headless, edge.

### Relatórios e screenshots

Screenshots de sucesso e falha são geradas automaticamente pelo Hooks.java.

O nome do arquivo é cucumber-reports.html e está na pasta target

### Observações

* Elementos cobertos por banners ou modais são tratados com safeClick, que tenta clicar mesmo se houver interceptação.
* Arquivos de massa de dados podem ser facilmente substituídos ou adicionados na pasta Massa_Dados. 
* Foi utilizado o padrão de Page Object Model.

### Idéias de melhorias futuras

1. Auto-healing de testes com **Healenium**: scripts que se ajustam quando seletores mudam, evitando falhas frequentes.
2. Paralelização de testes: rodar múltiplos testes simultaneamente para reduzir tempo de execução.
3. Cross-browser testing automatizado: testar diferentes navegadores e versões de forma integrada.
4. Monitoramento contínuo: alertas automáticos quando uma página ou funcionalidade quebra.
5. Reconhecimento de elementos dinâmicos: IA para localizar elementos que mudam de posição ou id.
6. Testes baseados em comportamento do usuário: simular interações reais usando análise de padrões.
7. Integração com dashboards inteligentes: gráficos que mostram tendências de falhas e cobertura.
8. Otimização de cobertura de testes: IA que sugere quais fluxos da aplicação precisam mais testes.

