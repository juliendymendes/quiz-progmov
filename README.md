# quiz-progmov

Acadêmicos: Juliendy dos Santos Mendes da Silva e Alexandre Marcos Peixoto da Silva

## Visão Geral do app

Para pessoas que queiram testar seus conhecimentos em diversas áreas através de quizzes rápidos, este projeto é uma aplicação móvel que testa os conhecimentos dos jogadores com base em seus acertos.

## Papéis

<strong>Jogador</strong>: pode criar uma conta, responder quizzes, compartilhar sua pontuação e visualizar estatísticas como: tempo empregado para responder o quiz; pontuação.

## Requisitos Funcionais

### Acesso ao aplicativo
- <strong>RF-1</strong>: O app deve permitir que o jogador crie uma conta com nome, email, senha.
- <strong>RF-2</strong>: O app deve permitir que o usuário faça login com email e senha previamente cadastrados.

### Gerência de conta
- <strong>RF-3</strong>: O app deve permitir que o usuário altere nome, email e senha.
- <strong>RF-4</strong>: O app deve permitir que o usuário adicione uma foto ao perfil.

### Quiz
- <strong>RF-5</strong>: O app deve permitir que o usuário escolha entre áreas de conhecimentos
- <strong>RF-6</strong>: Cada quiz deve possuir 10 perguntas.
- <strong>RF-7</strong>: Para cada pergunta deve haver 4 (quatro) alternativas e 1 (uma) resposta correta.
- <strong>RF-8</strong>: Ao enviar uma resposta o app deve mostrar a próxima pergunta.

### Visualização de estatísticas
- <strong>RF-9</strong>: Ao final do quiz, o app deve mostrar os acertos, o tempo que levou para responder as 10 perguntas e uma mensagem informando se foi uma pontuação bem sucedida ou mal sucedida.
- <strong>RF-10</strong>: Se o número de acertos seja maior ou igual a 6, a mensagem é positiva. Caso contrário, ou seja, a pontuação menor que 6, a mensagem é negativa.
- <strong>RF-11</strong>: O app deve mostrar todas as estatísticas do usuário em um painel.

### Compartilhamento de pontuação
- <strong>RF-12</strong>: O app deve permitir que o usuário compartilhe em suas redes sociais a pontuação obtida em determinado quiz. 
- <strong>RF-13</strong>: As informações a serem compartilhadas devem ser as seguintes: nome do quiz, área de conhecimento e pontuação.

### Entradas do app
- Nome, email, senha e foto

### Saídas do app
- Acertos, tempo e mensagem de feedback

#### Dicionário
- Usuário/jogador: pessoa que pode responder quizzes
- Estatísticas: tempo empregado na resposta ao quiz, acertos e mensagem de feedback
