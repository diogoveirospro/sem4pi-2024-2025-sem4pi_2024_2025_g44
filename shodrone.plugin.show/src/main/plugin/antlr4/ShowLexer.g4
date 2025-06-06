lexer grammar ShowLexer;

SHOW            : 'Show';
FIGURELIST      : 'FigureList';

// IDS
PROPNUMBER      : 'PROP' DASH DIGIT DIGIT DIGIT DIGIT;
FIGURECODE      : 'FIG' DASH DIGIT DIGIT DIGIT DIGIT;

DOT             : '.';
COMMA           : ',';
SEMICOLON       : ';';
LCURLYBRACE     : '{';
RCURLYBRACE     : '}';
DASH            : '-';
ARROW           : '->';

VERSIONNUMBER   : DIGIT DOT DIGIT DOT DIGIT+;
DIGIT           : [0-9];

BREAKLINE       : '\r'? '\n';
WS              : [ \t]+ -> skip ;