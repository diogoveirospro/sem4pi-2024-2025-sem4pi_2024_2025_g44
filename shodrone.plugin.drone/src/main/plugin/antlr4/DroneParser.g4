parser grammar DroneParser;
options { tokenVocab=DroneLexer; }
start : expr* EOF ;
expr : expr (MUL|DIV) expr
| expr (ADD|SUB) expr
| INT
;