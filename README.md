# BleachBraveSoulsBot
Um pequeno bot para Bleach Brave Souls for Android.

Um BOT para Bleach Brave Souls for Android. Funciona utilizando APIs de reconhecimento de imagem e Telegram Bot. Funciona exclusivamente com Droid4X Android Emulator on Windows rodando com a resolução 1240x700. Funciona movendo o mouse da maquina Host, no caso, exclusivamente, Windows. Para que tudo funcione, o Bot deve ser inicializado com direitos de Administrador, se não, o Bot não será capaz de mover o mouse na tela. O emulador de Android deve estar, na maior parte do tempo, em primeiro plano. Este pode ser minimizado, todavia, caso seja minimizado no tempo errado, o BOT pode parar e terá de ser reiniciado. Para inicializar o bot mais facilmente, o ideal eh criar um atalho para o bot na sua área de trabalho, como o seguinte comando antes do caminho do jar: "javaw -jar" e a caixa de iniciar com direitos de administrador marcada.

Para usar o recurso de notificações e interação do Telegram BOT, voce deve preencher o arquivo token.txt gerado automaticamente na primeira vez que inicializa o bot. Na primeira linha, voce deve inserir o token do seu proprio telegram BOT e na segunda linha, seu chatId. Sem o seu chat ID, o bot será incapaz de enviar notificações para você.

Aqui você pode ver como criar seu proprio bot: https://core.telegram.org/bots

Dependencias do projeto:
sikulixapi.jar // API de reconhecimento de imagem e interação com interfaces
java-telegram-bot-api-2.1.1.jar // API para funcionamento do TelegramBot
okhttp-3.4.1.jar // Dependencia herdada da API do TelegramBot
okio-1.10.0.jar // Dependencia herdada da API do TelegramBot
gson-2.6.2.jar // Dependencia herdada da API do TelegramBot

Todos os devidos créditos aos ciradores de cada uma das dependencias, sem eles esse projeto nao se concretizaria tão rápido ! :)
