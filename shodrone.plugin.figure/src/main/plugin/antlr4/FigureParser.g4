parser grammar FigureParser;

@header {
package gen;
}

options { tokenVocab=FigureLexer; }

start           : header mainBody EOF;

// The header section of the file
header          : DSL version dronetype;

version         : VERSION VERSIONNUMBER SEMICOLON;
dronetype       : DRONETYPE IDENTIFIER SEMICOLON;

// The main body of the file
mainBody        : positions velocities distance shapes before (group)* statement* pause lightsOnStatement after;

// Positions, velocities, and distances
positions       : (POSITION IDENTIFIER ASSIGN vector SEMICOLON)+;
velocities      : (VELOCITY IDENTIFIER ASSIGN ( PI DIV POSNUMBER | DOUBLENUMBER | POSNUMBER) SEMICOLON)+;
distance        : DISTANCE IDENTIFIER ASSIGN POSNUMBER SEMICOLON;

vector          : LPAREN (NEGNUMBER | POSNUMBER) COMMA (NEGNUMBER | POSNUMBER) COMMA (NEGNUMBER | POSNUMBER) RPAREN;

// Shapes
shapes          : (line | rectangle | circle | circumference)+;

line            : LINE IDENTIFIER LPAREN argumentList3 RPAREN SEMICOLON;
rectangle       : RECTANGLE IDENTIFIER LPAREN argumentList4 RPAREN SEMICOLON;
circle          : CIRCLE IDENTIFIER LPAREN argumentList3 RPAREN SEMICOLON;
circumference   : CIRCUMFERENCE IDENTIFIER LPAREN argumentList3 RPAREN SEMICOLON;

argumentList3   : expression COMMA expression COMMA IDENTIFIER;
argumentList4   : expression COMMA expression COMMA expression COMMA IDENTIFIER;
expression     : (IDENTIFIER | POSNUMBER | NEGNUMBER | DOUBLENUMBER) ((ADD | SUB | MUL | DIV) (IDENTIFIER | POSNUMBER | NEGNUMBER | DOUBLENUMBER))* ;

// Shapes Functions
shapeFunction   : lightsOn | lightsOff | move | rotate | movePos;

lightsOn        : LIGHTSON LPAREN color RPAREN SEMICOLON;
lightsOff       : LIGHTSOFF SEMICOLON;
move            : MOVE LPAREN vector COMMA POSNUMBER COMMA IDENTIFIER RPAREN SEMICOLON;
rotate          : ROTATE LPAREN IDENTIFIER COMMA IDENTIFIER COMMA (NEGNUMBER | POSNUMBER) MUL PI COMMA IDENTIFIER RPAREN SEMICOLON;
movePos         : MOVEPOS LPAREN IDENTIFIER COMMA IDENTIFIER RPAREN SEMICOLON;

color          : RED | YELLOW | GREEN;

// before section
before          : BEFORE (statement | group)* ENDBEFORE;

statement       : IDENTIFIER DOT shapeFunction;

// group section
group           : GROUP statement* ENDGROUP;

// pause
pause           : (PAUSE LPAREN POSNUMBER RPAREN SEMICOLON)?;

// lightson
lightsOnStatement : (IDENTIFIER DOT LIGHTSON LPAREN color RPAREN SEMICOLON)*;

// after section
after           : AFTER (statement | group)* ENDAFTER;


