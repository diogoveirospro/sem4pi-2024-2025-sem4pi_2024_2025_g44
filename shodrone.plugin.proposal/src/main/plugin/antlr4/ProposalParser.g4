parser grammar ProposalParser;
options { tokenVocab=ProposalLexer; }
start : expr* EOF ;
expr : expr (MUL|DIV) expr
| expr (ADD|SUB) expr
| INT
;