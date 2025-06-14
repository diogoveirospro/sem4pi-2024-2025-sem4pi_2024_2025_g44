// Generated from ShowParser.g4 by ANTLR 4.7.2

package gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShowParser}.
 */
public interface ShowParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShowParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ShowParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShowParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ShowParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShowParser#figurelist}.
	 * @param ctx the parse tree
	 */
	void enterFigurelist(ShowParser.FigurelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShowParser#figurelist}.
	 * @param ctx the parse tree
	 */
	void exitFigurelist(ShowParser.FigurelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShowParser#figureitem}.
	 * @param ctx the parse tree
	 */
	void enterFigureitem(ShowParser.FigureitemContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShowParser#figureitem}.
	 * @param ctx the parse tree
	 */
	void exitFigureitem(ShowParser.FigureitemContext ctx);
}