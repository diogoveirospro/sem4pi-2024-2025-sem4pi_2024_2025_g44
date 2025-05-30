// Generated from DroneParser.g4 by ANTLR 4.13.2
package gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DroneParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DroneParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DroneParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(DroneParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DroneParser.ExprContext ctx);
}