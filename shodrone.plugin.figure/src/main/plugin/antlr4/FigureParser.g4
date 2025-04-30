parser grammar FigureParser;
options { tokenVocab=FigureLexer; }
start : expr* EOF ;
expr : expr (MUL|DIV) expr
| expr (ADD|SUB) expr
| INT
;