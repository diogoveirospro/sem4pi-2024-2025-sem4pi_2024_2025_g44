lexer grammar DroneLexer;

PI : 'PI';

TYPES        : 'Types';
VARIABLES    : 'Variables';
INSTRUCTIONS : 'Instructions';
PROGRAMMING  : 'programming';
LANGUAGE     : 'language';
VERSION      : 'version';

TAKEOFF      : 'takeOff';
LAND         : 'land';
MOVE         : 'move';
MOVEPATH     : 'movePath';
MOVECIRCLE   : 'moveCircle';
HOOVER       : 'hoover';
LIGHTSON     : 'lightsOn';
LIGHTSOFF    : 'lightsOff';
BLINK        : 'blink';

DOT         : '.';
COMMA       : ',';
SEMICOLON   : ';';
LPAREN      : '(';
RPAREN      : ')';
ASSIGN      : '=';
ADD         : '+';
DASH        : '-';
MUL         : '*';
DIV         : '/';
LT          : '<';
GT          : '>';

TYPE_NAME : 'Position'
    | 'Point'
    | 'Vector'
    | 'LinearVelocity'
    | 'AngularVelocity'
    | 'Distance'
    | 'Time'
    ;

PLACEHOLDER : LT [a-zA-Z0-9_ ]+ GT;

IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_]*;

FLOAT : [0-9]+ '.' [0-9]+;

INTEGER : [0-9]+;

WS : [ \t]+ -> skip;

NEWLINE : [\r\n]+;