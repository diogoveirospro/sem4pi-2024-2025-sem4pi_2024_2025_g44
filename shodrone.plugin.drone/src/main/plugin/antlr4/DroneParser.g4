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
    : (TYPE_NAME | PLACEHOLDER) (IDENTIFIER | PLACEHOLDER) ASSIGN (expression | PLACEHOLDER) SEMICOLON NEWLINE*
    | LT (TYPE_NAME | PLACEHOLDER) GT LT (IDENTIFIER | PLACEHOLDER) GT ASSIGN (expression | PLACEHOLDER) SEMICOLON NEWLINE*
    ;

instruction
    : TAKEOFF LPAREN expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | LAND LPAREN expression RPAREN SEMICOLON NEWLINE*
    | MOVE LPAREN expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | MOVE LPAREN expression COMMA expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | MOVEPATH LPAREN expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | MOVECIRCLE LPAREN expression COMMA expression COMMA expression RPAREN SEMICOLON NEWLINE*
    | HOOVER LPAREN expression RPAREN SEMICOLON NEWLINE*
    | LIGHTSON LPAREN RPAREN SEMICOLON NEWLINE*
    | LIGHTSON LPAREN expression RPAREN SEMICOLON NEWLINE*
    | LIGHTSOFF LPAREN RPAREN SEMICOLON NEWLINE*
    | BLINK LPAREN expression RPAREN SEMICOLON NEWLINE*
    ;

expression
    : arithmetic
    | array_of_positions
    | PLACEHOLDER
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
    : LPAREN signed_number COMMA signed_number COMMA signed_number RPAREN
    ;

vector
    : point
    ;

array_of_positions
    : LPAREN point (COMMA point)* RPAREN
    ;

signed_number
    : DASH? number
    ;

number
    : FLOAT
    | INTEGER
    ;