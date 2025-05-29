# Makefile for generating ANTLR4 code in Java for the Figure grammar

# Base name of grammars
GRAMMAR_BASE = Figure

# Output directory for the generated Java files
OUT_DIR = ../../java/gen

clean:
	@rm -f $(OUT_DIR)/$(GRAMMAR_BASE)*.java
	@rm -f $(OUT_DIR)/$(GRAMMAR_BASE)*.tokens
	@rm -f $(OUT_DIR)/$(GRAMMAR_BASE)*.interp

generate:
	@antlr4 -Dlanguage=Java -visitor -no-listener -o $(OUT_DIR) $(GRAMMAR_BASE)Lexer.g4 $(GRAMMAR_BASE)Parser.g4
