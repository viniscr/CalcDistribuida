# CalcDistribuida
O objetivo deste projeto foi desenvolver um pequeno sistema de uma calculadora distribuída utilizando sockets.

O sistema/programa foi desenvolvido em Java e pode executar no Linux/ Windows.

O sistema é composto de:
- Um servidor concorrente principal que recebe as requisições via socket e encaminha as mesmas, via sockets para servidores escravos
- Servidores escravos que se registram no servidor principal e são os encarregados de realizar as operações
- Servidores escravos especializados que recebem requisições específicas, delegadas pelos servidor principal
- Clientes que fazem requisições para o servidor principal
- As 4 operações básicas são executadas nos servidores escravos básicos
- As operações de Porcentagem, raiz quadrada e potenciação são executadas nos servidores escravos especiais

Para testar o projeto:
1. Baixe o projeto do GitHub para o seu computador
2. Acesse a pasta test que está no projeto e contem os JAR's da aplicacao
3. A pasta test contem 3 subpastas (cliente, servidorPrincipal e servidorEscravo)
4. Entre em cada uma delas e altere o arquivo .properties de acordo com a sua preferência (Por padrão já estão definidas para rodar localmente)
5. Durante a alteração, certifique-se que as portas e endereços de (ADD, SUB, MUL, DIV) são as mesmas de BASICO.
   Durante a alteração, certifique-se que as portas e endereços de (RAIZ, POTE, E PORC) são as mesmas de ESPECIAL.
6. Feito isso:
    - Execute o jar da pasta servidorPrincipal (PrincipalServer.jar) passando a porta como parâmetro;
    - Logo após o da pasta servidorEscravo (EscravoServer.jar) passando (BASICO ou ESPECIAL) como parâmetro e;
    - Por ultimo o da pasta cliente (Client.jar).
