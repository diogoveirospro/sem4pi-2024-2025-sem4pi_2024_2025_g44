parser grammar ShowParser;

@header {
package gen;
}

options { tokenVocab=ShowLexer; }

start               :   SHOW PROPNUMBER SEMICOLON BREAKLINE figurelist EOF;

figurelist          :   FIGURELIST LCURLYBRACE BREAKLINE figureitem (BREAKLINE figureitem)* BREAKLINE RCURLYBRACE;

figureitem          :   FIGURECODE ARROW VERSIONNUMBER SEMICOLON;