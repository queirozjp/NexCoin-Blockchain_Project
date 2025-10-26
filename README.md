# 🪙 Projeto NexCoin: Implementação Educacional de Blockchain

[![Java](https://img.shields.io/badge/Language-Java-007396?style=flat-square&logo=java)](https://www.java.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## ⚠️ Aviso Importante - Propósito Educacional

**Este projeto é estritamente educacional.** Ele foi desenvolvido para demonstrar a implementação de conceitos básicos de tecnologia blockchain, criptografia de chave pública e validação de transações.

**Não utilize este código, as chaves ou qualquer conceito relacionado em ambientes de produção, para armazenar valor real ou em qualquer aplicação que exija segurança de nível comercial.**

## 📖 Sobre o Projeto

NexCoin é uma implementação em Java que simula uma criptomoeda básica (uma "coin") e sua estrutura de dados de blockchain. O projeto visa ser uma ferramenta de aprendizado prático, cobrindo os seguintes conceitos centrais:

* **Estrutura de Dados Blockchain:** Implementação de blocos (Blocks) encadeados e a própria cadeia (Blockchain).
* **Criptografia de Chave Pública:** Utilização de pares de chaves pública/privada para a criação de carteiras (Wallets) e assinatura de transações.
* **Validação de Transações:** Mecanismos para garantir que as transações são autênticas (assinadas corretamente) e que a carteira de origem possui fundos.
* **Proof-of-Work (Mineração):** Simulação do processo de mineração para adicionar novos blocos à cadeia e confirmar transações pendentes.
* **Integridade da Cadeia:** Funcionalidade para verificar se a cadeia de blocos não foi adulterada (verificação de hashes).

## 📂 Estrutura do Projeto

A estrutura de pacotes e classes reflete a organização lógica dos componentes do blockchain:

![estrutura](https://github.com/queirozjp/NexCoin-Blockchain_Project/blob/b0f048fd13a192c4915e06d1858341968ad9e79e/images/Screenshot%202025-10-25%20230647.png)
## ⚙️ Funcionalidades Principais

A aplicação é executada via linha de comando e oferece um menu interativo para explorar os conceitos:

| Opção | Funcionalidade | Conceitos Demonstrados |
| :---: | :--- | :--- |
| **1** | Criar uma nova carteira | Geração de par de chaves pública/privada. |
| **2** | Visualizar saldo da carteira | Modelo de Contas |
| **5** | Fazer uma nova transação | Assinatura de transação com chave privada. |
| **7** | Minerar transações | Algoritmo de Proof-of-Work e adição de bloco. |
| **8** | Visualizar blockchain | Exibição da cadeia e dos dados dos blocos. |
| **9** | Verificar integridade | Verificação de hashes sequenciais dos blocos. |

## 🕹️ Testes

## 🧩 Interface de Testes

### Menu
![Menu](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/menu.png)

### Criar Carteira
![Create Wallet](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/CreateWallet.png)

### Buscar Saldo
![Fetch Wallet](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/fetch.png)

### Mostrar Carteiras
![Display Wallets](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/displaywallets.png)

### Mostrar Chave Pública
![Display Public Key](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/displayPublicKey.png)

### Transações
![Transaction](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/transaction.png)

### Transações Criadas
![Create Wallet Transactions](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/createWalletTransactions.png)

### Transações Exibidas
![Display Transactions](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/displayTransactions.png)

### Mineração
![Mining](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/mining.png)

### Blockchain Válida
![Blockchain Valid](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/blockchainValid.png)

### Erro de Transação
![Transaction Error](https://raw.githubusercontent.com/queirozjp/NexCoin-Blockchain_Project/main/images/TransactionError.png)

## ▶️ Como Executar

1.  **Pré-requisitos:** Certifique-se de ter o **Java Development Kit (JDK)** instalado (versão 8 ou superior).
2.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/queirozjp/NexCoin-Blockchain_Project
    cd NexCoin
    ```
3.  **Compilar e Executar (Dependendo do seu ambiente, como Maven/Gradle ou IDE):**
    * *Se estiver usando o VS Code ou IDE:* Execute a classe `NexCoinApplication.java`.
    * *Se for via terminal (exemplo simplificado):*
        ```bash
        # Compile
        javac -d bin -cp src src/com/nexcoin/**/*.java 
        # Execute
        java -cp bin com.nexcoin.NexCoinApplication
        ```
4.  Siga as instruções do menu interativo para criar carteiras, fazer transações e minerar blocos.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT.
