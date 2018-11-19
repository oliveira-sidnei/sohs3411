# sohs3411
Capítulo 4

1)	Qual a importância de um sistema de nomeação para sistemas distribuídos? 
Eles são usados para compartilhar recursos, identificar entidades de maneira única, fazer referência a localização e outras funções.

2)	O que significa resolver um nome?
Converte-lo em dados sobre o recurso ou objeto nomeado, frequentemente para invocar uma ação sobre ele. 

3)	O que é um endereço para um sistema de nomeação? 
Endereço é o nome de um ponto de acesso.

4)	Por que não é comum a utilização do endereço do ponto de acesso de uma entidade como nome para esta entidade? 
Pois uma entidade pode mudar seu ponto de acesso com o tempo e além disso essa nomeação frequentemente não é amigável para seres humanos. 

5)	O que é um nome independente de localização? 
É um nome de uma entidade que é independente dos endereços dessa entidade, tornando-o mais fácil e flexível de utilizar.

6)	O que caracteriza um identificador? 
É um tipo de nome especial que identifica exclusivamente uma entidade. É necessário atender aos três pontos: identifica apenas uma entidade, a entidade possui apenas este identificador e um identificador representa sempre a mesma entidade, ou seja, nunca é reutilizado.

7)	O que é um nome amigável a serem humanos? Cite um exemplo. 
Ao contrário de endereços e identificadores são nomes representados por uma cadeia de caracteres. Um exemplo é o acesso ao site da fazenda que ao invés de se utilizar o IP é usado o nome www.fazenda.gov.br.

8)	Qual a característica de um esquema de nomeação simples? 
São simples cadeias aleatórias de bits com nomes não estruturados. Esses nomes não possuem nenhuma informação de como localizar o ponto de acesso da entidade associada.

9)	Explique como o ponto de acesso de uma entidade pode ser localizado quando se utiliza um esquema de nomeação simples. 
O ponto de acesso de uma entidade é localizado através da utilização dos recursos de broadcasting ou Multicasting.

10)	Como funciona a localização do endereço do ponto de acesso de uma entidade por broadcasting? 
Uma mensagem que contém o identificador de entidade é enviada por broadcast a todas as máquinas da rede e cada uma delas verifica se tem essa entidade. Somente as máquinas que podem oferecer o ponto de acesso para a entidade enviam uma mensagem de resposta contendo o endereço daquele ponto.

11)	Por que a localização do endereço do ponto de acesso de uma entidade por broadcasting não é viável em uma grande rede? 
Pois ele consome muita largura de banda e também faz com que uma grande quantidade de hospedeiros sejam interrompidos por requisições que não podem responder.

12)	Como funciona a localização do endereço do ponto de acesso de uma entidade por ponteiros repassadores? 
Quando uma entidade se move de A para B deixa para trás uma referência sobre a sua nova localização, assim, tão logo uma entidade seja localizada, um cliente pode consultar o endereço corrente da entidade percorrendo uma cadeia de ponteiros repassadores.

13)	Como funciona a localização do endereço do ponto de acesso de uma entidade em uma abordagem baseada em localização nativa? 
Cada hospedeiro usa um endereço IP fixo toda comunicação referente a este endereço de IP é dirigido ao agente nativo do hóspede. Sempre que este hospedeiro passar para uma outra rede ele requisita um endereço temporário esse endereço externo (CoA) é registrado no agente nativo. Quando o ponto de acesso precisa ser localizado o pacote é enviado ao agente nativo que repassa para o ponto de acesso.

14)	Em tabelas hash distribuídas (DHT), pode ser utilizada uma tabela e derivação para a localização a entidade responsável por determinada chave. Qual a vantagem na utilização da tabela de derivação? Com a utilização da tabela de derivação cada nó tem muito mais informação do que apenas o seu predecessor e sucessor. Dessa forma cada nó tem referências (atalhos) pra uma quantidade de atalhos de forma que em busca ele não precise correr nó a nó, podendo fazer saltos para o mais próximo possível do que se procura. 
Com a utilização da tabela de derivação cada nó tem muito mais informação do que apenas o seu predecessor e sucessor. Dessa forma cada nó tem referências (atalhos) pra uma quantidade de atalhos de forma que em busca ele não precise correr nó a nó, podendo fazer saltos para o mais próximo possível do que se procura. 

15)	Em um esquema de nomeação simples a busca por uma entidade pode utilizar uma abordagem hierárquica. Como é possível a utilização de uma abordagem hierárquica se o nome da entidade não fornece informações sobre a localização da entidade? 
Cada entidade está localizada em um domínio e é representada por um registro de localização ele irá consultar sempre a um nível acima

16)	Como uma entidade pode ser localizada em um esquema de nomeação simples utilizando abordagem hierárquica? 
Primeiramente é feita a busca localmente e conforme vai se buscando a área de busca vai expandindo para um nível superior a cada vez que a consulta é passada para diretório de nível mais alto.

17)	Qual a diferença entre um esquema de nomeação simples e um esquema de nomeação estruturada? 
A nomeação simples possui um padrão de escrita bom para máquinas e a nomeação estruturada utiliza nomes amigáveis para os seres humanos.

18)	O que se entende por resolução de nomes em um esquema de nomeação estruturada? 
É o processo de busca de um nome.

19)	Na resolução de nomes com nomeação estruturada é possível a fusão de diferentes espaços de nomes de forma transparente. Explique a afirmação.
É possível graças a montagem entre sistemas de arquivos que corresponde a deixar que um nó de diretório armazene o identificador de um nó de diretório de um espaço de nomes diferente, ao qual nos referimos como espaço de nomes externo. O nó de diretório que armazena o identificador de nó é denominado ponto de montar. O nó de diretório no espaço de nomes externo é denominado ponto de montagem.

20)	O que é o NFS (Network File System)? 
É um sistema distribuído de arquivo que vem com um protocolo que descreve com precisão como um cliente pode acessar um arquivo em um servidor (remoto) de arquivos.

21) Espaços de nomes costumam ser organizados em camadas. Qual a função da camada: 
• global – é formada por nós de níveis mais altos, isto é, o nó-raiz e outros nós de diretório próximos ao nó-raiz, ou seja, seus filhos. Costumam ser altamente estáveis, no sentido que as tabelas de diretório raramente mudam.
• administrativa - formada por nós de diretório que, juntos, são gerenciados por uma única organização. Representam grupos de entidades que pertencem à mesma organização ou unidade administrativa.
• gerencial - consiste em nós cujo comportamento típico é a mudança periódica. A camada inclui hospedeiros na rede local e nós que representam arquivos compartilhados.

22)	A resolução de nomes pode ser iterativa ou recursiva. Qual a diferença? 
Na resolução iterativa de nomes, um resolvedor de nomes entrega o nome completo ao servidor-raiz de nomes. Adotamos como premissa que o endereço em que o servidor-raiz pode ser contatado é bem conhecido. O servidor-raiz resolverá o nome de caminho até onde puder e retornará o resultado ao cliente. 
Com a resolução recursiva de nomes, em vez de retornar cada resultado intermediário de volta ao resolvedor de nomes do cliente, um servidor de nomes passa o resultado para o próximo servidor de nomes que encontrar.

23)	O que é nomeação baseada em atributos?
Um modo de fornecer descrição para o que está sendo procurado, a descrição da entidade é feita em termos de pares (atributo, valor), nesta abordagem é adotada como premissa que uma entidade tem um conjunto associado de atributos, cada um desses atributos diz algo sobre a entidade, quando o usuario especifica os valores que determinado atributo deve ter, acontece uma restrição no conjunto de entidades nas quais há interesse. Cabe ao sistema de nomeação retornar uma ou mais entidades que atendam à descrição do usuário.

24)	Cite uma vantagem de um serviço de diretórios sobre um serviço comum de nomeação.
Retorna conjuntos de atributos de todos os objetos encontrados, que correspondam a alguns atributos especificados. Assim, o cliente pode especificarque apenas um subconjunto dos atributos é de interesse.

25)	O que caracteriza um serviço de descoberta?
É caracterizado por ter um ambiente de interligação em rede espontânea, este precisa contar com a comunicação por multicast, pelo menos na primeira vez que acessa o serviço de descoberta local.

Capítulo 5

1)	Por que a sincronização entre processos em sistemas distribuídos é mais complexa do que a sincronização entre processos em sistemas centralizados? 
Em um sistema centralizado o tempo não é ambíguo, pois quando um processo necessitar saber a hora basta fazer uma chamada ao sistema e o núcleo responde, porém em um sistema distribuído o horário em cada computador pode não ser o mesmo, por isso conseguir um acordo nos horários não é algo trivial.

2)	Como é o funcionamento de um relógio de computador?
Um temporizador de computador usualmente é um cristal de quartzo lapidado e usinado com precisão. Associados com cada cristal há dois registradores, um contador e um registrador de retenção. Cada oscilação do cristal reduz uma unidade do contador. Quando o contador chega a zero é gerada uma interrupção e o contador é recarregado pelo registrador de retenção. Desse modo, é possível programar um temporizador para gerar uma interrupção com qualquer frequência desejada. Cada interrupção é denominada ciclo de relógio.

3)	O que se entende por defasagem de relógio? 
Por mais que todos os relógios de computadores funcionem da mesma maneira e a frequência ao qual o oscilador trabalha é razoavelmente estável é impossível garantir que que todos os osciladores trabalhem na mesma frequência. Por isso acaba se tendo uma defasagem em cada relógio de forma gradativa ao passar do tempo.

4)	Para um algoritmo de sincronização de relógios, o que é o deslocamento de um relógio?
O deslocamento em relação a uma hora específica t é Cp(t) - t.
É a diferença entre uma especifica e a hora perfeita.

5)	Como o NTP faz para sincronizar a hora com um servidor de hora? 
Ele verifica a hora entre pares de servidores, após a anotação de 8 pares de valor é calculada a média da diferença dos valores. Em seguida é feito o ajuste com base nesse novo horário.

6)	O que são os estratos do NTP? 
O NTP divide os servidores em estratos, quanto maior o nível do estrato mais confiável é esse servidor. 

7)	O NTP possui diferentes modos de sincronização. O que caracteriza o  modo de chamada de procedimento para o NTP?
Um servidor que aceita requisições de outros computadores, os quais ele processa respondendo com seu carimbo de tempo (leitura corrente do relógio). Esse modo é conveniente quando é exigida uma precisão melhor do que a que pode ser obtida com o multicast – ou quando multicast não é suportado por hardware.

8)	Quais critérios um cliente NTP utiliza para seleção de seu par de sincronização?


9)	Diz-se que o algoritmo de Berkeley, ao contrário do algoritmo do NTP, não é um algoritmo passivo. O que isto significa? 
O servidor de tempo é ativo e consulta todas as máquinas de tempos em tempos para perguntar qual é a hora que cada uma está marcando. Com base nas respostas, ele calcula um horário médio e diz a todas as outras máquinas que adiantem seus relógios até o novo horário ou atrasem seus relógios até que tenham obtido alguma redução especificada. Esse método é adequado para um sistema no qual nenhuma máquina tenha receptor WWV.

10)	Em 1978 Lamport provou que não é necessário que os relógios de hospedeiros estejam em perfeita sincronia para determinar a ordem dos acontecimentos em um sistema distribuído. Em que se baseia a proposta de Lamport? 
Lamport se baseia na utilização de um relógio lógico onde cada processo tem uma ordem na cadeia de eventos. Se A acontece ante de B e B antes de C não existe a necessidade de se saber a hora exata em que cada um ocorrerá, pois já se sabe a ordem que os eventos acontecerão.
 
11)	Por que é necessária a implementação da exclusão mútua em sistemas distribuídos? 
Devido a colaboração e concorrência entre vários processos em muitos casos isso significa que em algum momento eles precisarão acessar o mesmo recurso simultaneamente, por isso essa solução é adotada para evitar que acessos concorrente corrompam ou tornem um recurso inconsistente.
12)	Em que consiste a implementação de exclusão mútua baseada em ficha para um sistema distribuído? 
Consegue-se a exclusão mútua com a passagem de uma mensagem especial entre os processos, conhecida como ficha. Há só uma ficha disponível, e quem quer que a tenha pode acessar o recurso compartilhado. Ao terminar, a ficha é passada adiante para o processo seguinte. Se um processo que tenha a ficha não estiver interessado em acessar o recurso, ele apenas a passa adiante.

13)	Em que consiste a implementação de exclusão mútua baseada em permissão para um sistema distribuído? 
Um processo que quiser acessar o recurso em primeiro lugar solicita a permissão de outros processos.
Um processo é eleito como o coordenador e, sempre que um processo (processo 1) quiser acessar um recurso compartilhado, envia uma mensagem de requisição ao coordenador declarando qual recurso quer acessar e solicitando permissão. Se nenhum outro processo estiver acessando aquele recurso naquele momento, o coordenador devolve uma resposta concedendo a permissão. Quando a resposta chega, o processo requisitante pode seguir adiante.

14)	Como funciona o algoritmo descentralizado de permissão para implementação da exclusão mútua em um sistema distribuído? 
Em essência, a solução desses algoritmos amplia o coordenador central. Adota-se como premissa que cada recurso é replicado n vezes. Toda réplica tem seu próprio coordenador para controlar o acesso por processos concorrentes.
Sempre que um processo quiser acessar o recurso, ele vai precisar apenas obter um voto majoritário de m > n/2 coordenadores. Diferente do esquema centralizado, quando um coordenador não der permissão para acessar um recurso, ele informará ao requisitante.

15)	Como funciona o algoritmo distribuído de permissão para implementação da exclusão mútua em um sistema distribuído? 
O algoritmo requer que haja uma ordenação total de todos os eventos no sistema. Isto é, para qualquer par de eventos não pode haver ambiguidade sobre qual realmente aconteceu em primeiro lugar. O algoritmo de Lamport é um modo de conseguir essa ordenação e pode ser usado para fornecer marcas de tempo para exclusão mútua distribuída.
Quando um processo quer acessar um recurso compartilhado, monta uma mensagem que contém o nome do recurso, seu número de processo e a hora corrente (lógica). Depois, envia a mensagem a todos os outros processos, fato que, conceitualmente, inclui ele mesmo. Adota-se como premissa que o envio de mensagens é confiável.
Quando um processo recebe uma mensagem de requisição de um outro processo, a ação que ele executa depende de seu próprio estado em relação ao recurso nomeado na mensagem.

16)	Como funciona o algoritmo do valentão para eleição de um coordenador em um sistema distribuído? 
Quando um processo nota que o coordenador não está mais respondendo as requisições ele inicia uma eleição. Caso o processo inativo volte ele convoca nova eleição e o processo de número mais alto executando naquele momento ganhará a eleição.
