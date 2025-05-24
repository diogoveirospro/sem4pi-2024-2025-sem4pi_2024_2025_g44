lexer grammar FigureLexer;

DSL         : 'DSL';
VERSION     : 'version';
DRONETYPE   : 'DroneType';
POSITION    : 'Position';
VELOCITY    : 'Velocity';
DISTANCE    : 'Distance';
BEFORE      : 'before';
AFTER       : 'after';
GROUP       : 'group';
ENDBEFORE   : 'endbefore';
ENDAFTER    : 'endafter';
ENDGROUP    : 'endgroup';
MOVE        : 'move';
MOVEPOS     : 'movePos';
ROTATE      : 'rotate';
LIGHTSON    : 'lightsOn';
LIGHTSOFF   : 'lightsOff';
PAUSE       : 'pause';
PI          : 'PI';

DOT         : '.';
COMMA       : ',';
SEMICOLON   : ';';
LPAREN      : '(';
RPAREN      : ')';
ASSIGN      : '=';
ADD         : '+';
SUB         : '-';
MUL         : '*';
DIV         : '/';

LINE        : 'Line';
RECTANGLE   : 'Rectangle';
CIRCLE      : 'Circle';
CIRCUMFERENCE : 'Circumference';

RED         : 'RED';
YELLOW      : 'YELLOW';
GREEN       : 'GREEN';

VERSIONNUMBER   : DIGIT DOT DIGIT DOT DIGIT+;
NEGNUMBER       : SUB [1-9] DIGIT*;
POSNUMBER       : DIGIT+;
DOUBLENUMBER    : DIGIT+ DOT DIGIT+;
IDENTIFIER      : [a-zA-Z] [a-zA-Z0-9]*;
fragment DIGIT  : [0-9];

WS : [ \t\r\n]+ -> skip;
