# infraSoft PROJETO PLAYER DE MUSICA
*primeira parte*
interface básica (sem GUI), apenas modo texto simples, e as funcionalidades de adicionar e remover músicas da lista de reprodução

**segunda parte**
integralização com uma GUI

**terceira parte**
 funcionalidade de alternar entre reprodução aleatória e reprodução sequencial


@RESTRIÇÕES@
O uso de métodos e estruturas que abstraem o gerenciamento das threads e o controle de concorrência é proibido. Exemplo:
synchronized
ReadWriteLock e StampedLock
Variáveis atômicas (ex. AtomicBoolean)
wait/notify (usados em conjunto com o synchronized)
Qualquer questionamento sobre o uso de determinada estrutura que não foi demonstrada na aula monitoria nem listada aqui deve ser feita via Classroom aos monitores.

#CRITÉRIOS DE AVALIAÇÃO#
Código limpo e bem estruturado, com bons nomes pras variáveis e funções, comentários importantes e boa indentação; explicação do motivo de uma determinada estrutura ou função ter sido utilizada — um lock e uma variável condicional, por exemplo: por que foram necessários?
Uso adequado da concorrência, ou seja, o programa não será totalmente concorrente mas também não deverá funcionar com apenas uma ou duas threads
As funcionalidades devem ser implementadas com o auxílio de concorrência
A interface do programa apresenta o mínimo necessário para a demonstração das funcionalidades (adicionar, remover, play/pause, avançar/voltar, modo aleatório…)
