# US253 - Configuration of a Drone’s Language

**As** a Drone Tech,
**I want** to specify in the system the programming language for a given drone model,
**So that** drones of this model can be used in figures/shows.

**Note:** A given programming language can be supported by several drone models.


### Grammar

```
(* Terminais *)
PI = "PI";

(* Programa *)
program ::= { statement } ;

(* Declarações *)
statement ::= variable_declaration | instruction ;

(* Tipos *)
type ::= "Position" | "Vector" | "LinearVelocity" | "AngularVelocity" | "Distance" | "Time" ;

(* Declaração de Variáveis *)
variable_declaration ::= type identifier "=" expression ";" ;

(* Identificadores *)
identifier ::= letter , { letter | digit | "_" } ;

(* Instruções *)
instruction ::=
    "takeOff" "(" expression "," expression ")" ";"
  | "land" "(" expression ")" ";"
  | "move" "(" expression "," expression ")" ";"
  | "move" "(" expression "," expression "," expression ")" ";"
  | "movePath" "(" array_of_positions "," expression ")" ";"
  | "hoover" "(" expression ")" ";"
  | "lightsOn" "(" ")" ";"
  | "lightsOff" "(" ")" ";"
  | "blink" "(" expression ")" ";" ;

(* Expressões *)
expression ::= arithmetic ;

arithmetic ::= term , { ("+" | "-") , term } ;
term       ::= factor , { ("*" | "/") , factor } ;
factor     ::= number
             | PI
             | identifier
             | vector
             | position
             | array_of_positions
             | "(" expression ")" ;

(* Literais Position e Vector *)
position ::= "(" float "," float "," float ")" ;
vector   ::= "(" float "," float "," float ")" ;

(* Arrays de Positions com par extra de parênteses *)
array_of_positions ::= "(" "(" position , { "," position } ")" ")" ;

(* Tokens léxicos *)
number  ::= float | integer ;
float   ::= digit , { digit } , "." , digit , { digit } ;
integer ::= digit , { digit } ;

(* Caracteres *)
letter ::= "A".."Z" | "a".."z" ;
digit  ::= "0".."9" ;

````


### Antilr of the grammar
````
grammar DroneOne;

// Programa inicial
program : statement* EOF ;

statement
    : variableDeclaration
    | instruction
    ;

variableDeclaration
    : type identifier '=' expression ';'
    ;

instruction
    : 'takeOff' '(' expression ',' expression ')' ';'
    | 'land' '(' expression ')' ';'
    | 'move' '(' expression ',' expression ')' ';'
    | 'move' '(' expression ',' expression ',' expression ')' ';'
    | 'movePath' '(' arrayOfPositions ',' expression ')' ';'
    | 'hoover' '(' expression ')' ';'
    | 'lightsOn' '(' ')' ';'
    | 'lightsOff' '(' ')' ';'
    | 'blink' '(' expression ')' ';'
    ;

type
    : 'Position'
    | 'Vector'
    | 'LinearVelocity'
    | 'AngularVelocity'
    | 'Distance'
    | 'Time'
    ;

expression
    : arithmetic
    ;

arithmetic
    : term (('+' | '-') term)*
    ;

term
    : factor (('*' | '/') factor)*
    ;

factor
    : number
    | PI
    | identifier
    | vector
    | position
    | arrayOfPositions
    | '(' expression ')'
    ;

position
    : '(' float ',' float ',' float ')'
    ;

vector
    : '(' float ',' float ',' float ')'
    ;

arrayOfPositions
    : '(' '(' position (',' position)* ')' ')'
    ;

number
    : float
    | integer
    ;

float
    : DIGIT+ '.' DIGIT+
    ;

integer
    : DIGIT+
    ;

identifier
    : LETTER (LETTER | DIGIT | '_')*
    ;

PI : 'PI' ;

// Tokens léxicos
fragment LETTER : [a-zA-Z] ;
fragment DIGIT  : [0-9] ;

// Ignorar espaços em branco e comentários
WS : [ \t\r\n]+ -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
````