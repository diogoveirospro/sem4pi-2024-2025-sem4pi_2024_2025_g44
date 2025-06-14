// Generated from DroneParser.g4 by ANTLR 4.7.2

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
	 * Visit a parse tree produced by {@link DroneParser#version_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion_header(DroneParser.Version_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#type_description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_description(DroneParser.Type_descriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(DroneParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(DroneParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(DroneParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(DroneParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(DroneParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(DroneParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(DroneParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoint(DroneParser.PointContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#vector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVector(DroneParser.VectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#array_of_positions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_of_positions(DroneParser.Array_of_positionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DroneParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(DroneParser.NumberContext ctx);
}