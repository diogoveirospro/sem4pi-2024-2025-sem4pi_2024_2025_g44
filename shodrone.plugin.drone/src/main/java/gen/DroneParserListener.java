// Generated from DroneParser.g4 by ANTLR 4.7.2

package gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DroneParser}.
 */
public interface DroneParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DroneParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(DroneParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(DroneParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#version_header}.
	 * @param ctx the parse tree
	 */
	void enterVersion_header(DroneParser.Version_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#version_header}.
	 * @param ctx the parse tree
	 */
	void exitVersion_header(DroneParser.Version_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#type_description}.
	 * @param ctx the parse tree
	 */
	void enterType_description(DroneParser.Type_descriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#type_description}.
	 * @param ctx the parse tree
	 */
	void exitType_description(DroneParser.Type_descriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(DroneParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(DroneParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(DroneParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(DroneParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(DroneParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(DroneParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(DroneParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(DroneParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(DroneParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(DroneParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(DroneParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(DroneParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(DroneParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(DroneParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#point}.
	 * @param ctx the parse tree
	 */
	void enterPoint(DroneParser.PointContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#point}.
	 * @param ctx the parse tree
	 */
	void exitPoint(DroneParser.PointContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#vector}.
	 * @param ctx the parse tree
	 */
	void enterVector(DroneParser.VectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#vector}.
	 * @param ctx the parse tree
	 */
	void exitVector(DroneParser.VectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#array_of_positions}.
	 * @param ctx the parse tree
	 */
	void enterArray_of_positions(DroneParser.Array_of_positionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#array_of_positions}.
	 * @param ctx the parse tree
	 */
	void exitArray_of_positions(DroneParser.Array_of_positionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DroneParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(DroneParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link DroneParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(DroneParser.NumberContext ctx);
}