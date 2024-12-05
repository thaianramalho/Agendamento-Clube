# Aplicativo para Agendamento de Atividades Esportivas
## Feito por: Thaian Ramalho, Ayrton Rodrigues e Samir Ribeiro
# Guia: Como Clonar o Repositório e Abrir no Android Studio

## 1. Pré-requisitos
- **Android Studio** instalado ([Download aqui](https://developer.android.com/studio)).
- **Git** instalado ([Download aqui](https://git-scm.com/)).

---

## 2. Clonar o Repositório
1. **Obter o link do repositório**:
   - Acesse o repositório no GitHub ou outro serviço de hospedagem de código.
   - Copie o link HTTPS ou SSH do repositório.

2. **Clonar o repositório**:
   - Abra o terminal ou prompt de comando.
   - Execute o comando abaixo substituindo `<URL_DO_REPOSITORIO>` pelo link copiado:
     ```bash
     git clone <URL_DO_REPOSITORIO>
     ```
   - Aguarde o download dos arquivos.

---

## 3. Abrir o Projeto no Android Studio
1. **Iniciar o Android Studio**:
   - Abra o Android Studio no seu computador.

2. **Importar o projeto**:
   - No menu principal, clique em **File > Open**.
   - Navegue até a pasta onde o repositório foi clonado.
   - Selecione a pasta principal do projeto (onde o arquivo `build.gradle` está localizado) e clique em **OK**.

3. **Aguardar o carregamento**:
   - O Android Studio iniciará o processo de sincronização do Gradle. Aguarde até que a sincronização seja concluída.

---

## 4. Configurar o Emulador ou Dispositivo Físico
1. **Configurar o Emulador (opcional)**:
   - No Android Studio, clique em **Device Manager**.
   - Crie um novo emulador configurando o modelo e a versão do Android desejados.
   - Inicie o emulador.

2. **Conectar um dispositivo físico (opcional)**:
   - Ative o modo **Desenvolvedor** e **Depuração USB** no dispositivo.
   - Conecte o dispositivo ao computador via USB.

---

## 5. Executar o Projeto
- Clique no botão **Run** (ícone de ▶️ na barra superior) ou pressione **Shift + F10**.
- Escolha o dispositivo ou emulador na lista de dispositivos disponíveis.
- Aguarde a instalação e execução do aplicativo.

---

## Problemas Comuns
1. **Erro de Gradle**:
   - Certifique-se de que sua conexão com a internet está ativa.
   - Atualize o Gradle clicando em **Try Again** ou **Update Gradle**.

2. **Erro ao clonar**:
   - Verifique se o Git está instalado e configurado corretamente.
   - Certifique-se de que o link do repositório está correto.

3. **Dispositivo não detectado**:
   - Para dispositivos físicos, instale os drivers USB corretos do fabricante.

---



## Objetivo
Desenvolver um aplicativo para agendamento de atividades esportivas em um complexo esportivo utilizando **Kotlin**, **XML** para a interface gráfica e **SQLite** como banco de dados. O sistema deve atender dois tipos de usuários: **administradores** e **usuários finais**, garantindo segurança e uma experiência amigável.

---

## Funcionalidades do Aplicativo

### 1. Cadastro de Administradores
- **Funcionalidades:**
  - Administradores podem cadastrar novas atividades esportivas oferecidas pelo complexo (ex.: vôlei, futebol, etc.).
  - As informações cadastradas devem ser salvas no banco de dados.

---

### 2. Cadastro de Usuários Finais
- **Requisitos para Cadastro:**
  - CPF (utilizado como login único no sistema);
  - Nome completo;
  - Telefone;
  - E-mail;
  - Data de nascimento;
  - Senha (armazenada com criptografia segura).

- **Validações:**
  - Garantir a unicidade do CPF no sistema.
  - Implementar criptografia para o armazenamento de senhas.

---

### 3. Login Seguro
- **Funcionalidades:**
  - Login para administradores e usuários finais.
  - Verificação das credenciais no banco de dados.
  - Garantir autenticação segura.

---

### 4. Cadastramento de Modalidades Esportivas
- **Funcionalidades:**
  - Interface para administradores cadastrarem modalidades esportivas (ex.: Futebol, Vôlei, Futsal).
  - Validação para impedir o cadastro de modalidades com nomes duplicados.
  - Modalidades cadastradas serão usadas no agendamento.

---

### 5. Calendário de Agendamentos
- **Funcionalidades:**
  - **Usuários Finais:**
    - Acesso a um calendário interativo:
      - Dias livres para agendamento: Não marcados.
      - Dias ocupados: Marcados em vermelho.
  - **Administradores:**
    - Visualizar todos os agendamentos realizados.

---

### 6. Agendamento de Atividades
- **Funcionalidades:**
  - Usuários finais poderão:
    1. Selecionar uma data disponível no calendário.
    2. Escolher uma modalidade esportiva cadastrada.
  - Salvar o agendamento no banco de dados.
  - Marcar a data como ocupada no calendário.
- **Observação:**
  - Agendamentos são diários (sem horário de início e fim) e limitados a um por dia.

---
