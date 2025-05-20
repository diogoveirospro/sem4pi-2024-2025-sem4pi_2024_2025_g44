parser grammar ShowParser;
options { tokenVocab=ShowLexer; }
start : expr* EOF ;
expr : expr (MUL|DIV) expr
| expr (ADD|SUB) expr
| INT
;