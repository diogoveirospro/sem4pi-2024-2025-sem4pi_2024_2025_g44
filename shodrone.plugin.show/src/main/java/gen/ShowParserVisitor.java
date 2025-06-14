// Generated from ShowParser.g4 by ANTLR 4.7.2

package gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ShowParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ShowParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ShowParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ShowParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ShowParser#figurelist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFigurelist(ShowParser.FigurelistContext ctx);
	/**
	 * Visit a parse tree produced by {@link ShowParser#figureitem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFigureitem(ShowParser.FigureitemContext ctx);
}