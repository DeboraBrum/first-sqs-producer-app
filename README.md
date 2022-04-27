# AWS SQS
## Sobre o serviço
O serviço de SQS oferece uma fila hospedada segura, durável e disponível que permite integrar e desacoplar sistemas de software e componentes distribuídos.

## AWS SQS versus SNS
O serviço SNS é um serviço com funcionalidade pub/sub e envia mensagens para muitos inscritos de diferentes tipos (SQS, Lambda, Email etc), enquanto o serviço SQS é um serviço de fila para processamento de mensagens, onde é necessário que outro sistema faça a leitura dessas mensagens e apague as que já foram lidas.
Para decidir qual serviço é melhor para uma solução, podemos fazer uma pergunta: 
- Se acontecer algum problema, outros sistemas devem ser notificados ou um sistema deve ser notificado? 
  - Caso a resposta seja para outros sistemas, a melhor opção é utilizar o SNS, pois envia notificações para diferentes inscritos;
  - Caso a resposta seja para um sistema, a melhor opção é utilizar o SQS, pois possui apenas um sistema que recebe essa mensagem e a processa. 

## Prática

Para criar uma nova fila, entramos na parte do site referente ao SQS e seguimos os seguintes passos:

- Clicar em Create queue;
- Selecionar o tipo (Standard ou FIFO);
- Digitar o nome da fila;
- Definir as configurações
  - O tempo definido em visibility timeout é o tempo de expiração da mensagem. (A amazon lê as mensagens por status, caso seja lida e não excluída por esse tempo, o status da mensagem volta como não lido);
  - O tempo "Message retention period" é quanto tempo essa mensagem vai ser mantida no ar, caso não haja exclusão;
  - Delivey delay é o tempo que vamos entregar a mensagem;
  - Receive message wait time é o tempo que vamos receber a mensagem;
  - Maximum message size é o tamanho máximo da mensagem.
- Após definir os tempos, escolhemos a policy;
- Clicar em Create queue.

Para enviar mensagens para a fila:
- Clicamos na fila desejada;
- Clicamos em Send and receive message;
- Digitamos a mensagem desejada;
- Clicamos em Send message;

Para ler mensagens da fila:
- Clicamos na fila desejada;
- Clicamos em Send and receive message;
- Clicamos em Poll for messages;
- Após o Poll ser realizado, as mensagens serão atualizadas e podemos clicar nas mensagens da fila;
- Ao clicar na mensagem desejada, abrirá uma caixa de diálogo informando o conteúdo da mensagem;
  
Para apagar mensagens da fila:
- Clicamos na fila desejada;
- Clicamos em Send and receive message;
- Clicamos em Poll for messages;
- Marcamos o check da mensagem desejada;
- Clicamos em delete;
- Confirmamos o delete.

#### Comandos pelo AWS CLI
Para entrar na linha de comando, é necessário ter o AWS CLI instalado na máquina e rodar os seguintes comandos no terminal: 
```shell
aws configure
AWS Access Key ID : ***
AWS Secret Access Key: ***
Default region name: us-east-1
Default output format: json
```
Para criar uma fila:
```shell
AWS sqs create-queue --queue-name="nome-fila" --region=us-east-1 
```
Para criar uma fila FIFO:
```shell
aws sqs create-queue --queue-name="nome-fila.fifo"--attributes="{\"FifoQueue\":\"true\", \"ContentBasedDeduplication\":\"true\"}"--region=us-east-1
```
Após criar, será retornado a url da fila. (importante guardar essa informação)

Para listar as listas:
```shell
AWS sqs list-queues
```

Para enviar mensagens:
```shell
AWS sqs send-message --message-body="texto-mgs" --queue-url="url-da-fila-criada"
```
Para ler mensagem:
```shell
aws sqs receive-message --queue-url="url-da-fila" --wait-time-seconds=20
```
Para deletar mensagem:
```shell
aws sqs delete-message --queue-url="url-da-fila" --receipt-handle="receipt-handle-da-msg"
```
Para deletar a fila:
```shell
aws sqs delete-queue --queue-url="url-da-fila"
```