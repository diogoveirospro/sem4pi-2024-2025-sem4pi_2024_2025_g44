lexer grammar FigureLexer;
MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';
INT : ('0'..'9')+ ;
WS : [ \t\r\n]+ -> skip ;