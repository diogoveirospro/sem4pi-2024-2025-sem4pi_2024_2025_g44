parser grammar DroneParser;

@header {
package gen;
}

options { tokenVocab=DroneLexer; }

start
    : version_header NEWLINE*
      TYPES NEWLINE*
      type_description+
      VARIABLES NEWLINE*
      variable_declaration+
      INSTRUCTIONS NEWLINE*
      instruction+ EOF
    ;

version_header
    : IDENTIFIER+ PROGRAMMING LANGUAGE VERSION FLOAT
    ;

type_description
    : TYPE_NAME DASH description NEWLINE*
    ;

description
    : (~NEWLINE)+
    ;

variable_declaration
    : TYPE_NAME IDENTIFIER ASSIGN expression SEMICOLON NEWLINE*
    | LT TYPE_NAME GT LT IDENTIFIER GT ASSIGN expression SEMICOLON NEWLINE*
    ;

instruction
    : TAKEOFF LPAREN expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | LAND LPAREN expression RPAREN SEMICOLON NEWLINE*
    | MOVE LPAREN expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | MOVE LPAREN expression COMMA expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | MOVEPATH LPAREN array_of_positions COMMA expression RPAREN SEMICOLON NEWLINE*
    | MOVECIRCLE LPAREN expression COMMA expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | HOOVER LPAREN expression RPAREN SEMICOLON NEWLINE*
    | LIGHTSON LPAREN RPAREN SEMICOLON NEWLINE*
    | LIGHTSON LPAREN expression RPAREN SEMICOLON NEWLINE*
    | LIGHTSOFF LPAREN RPAREN SEMICOLON NEWLINE*
    | BLINK LPAREN expression RPAREN SEMICOLON NEWLINE*
    ;

expression
    : arithmetic
    ;

arithmetic
    : term ((ADD | DASH) term)*
    ;

term
    : factor ((MUL | DIV) factor)*
    ;

factor
    : number
    | PI
    | id=IDENTIFIER
    | vector
    | point
    | array_of_positions
    | LPAREN expression RPAREN
    ;

point
    : LPAREN FLOAT COMMA FLOAT COMMA FLOAT RPAREN
    ;

vector
    : point
    ;

array_of_positions
    : LPAREN LPAREN point (COMMA point)* RPAREN RPAREN
    ;

number
    : FLOAT
    | INTEGER
    ;