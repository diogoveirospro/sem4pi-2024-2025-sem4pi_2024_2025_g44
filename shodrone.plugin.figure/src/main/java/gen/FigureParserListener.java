// Generated from FigureParser.g4 by ANTLR 4.7.2

package gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FigureParser}.
 */
public interface FigureParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FigureParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(FigureParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(FigureParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(FigureParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(FigureParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#version}.
	 * @param ctx the parse tree
	 */
	void enterVersion(FigureParser.VersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#version}.
	 * @param ctx the parse tree
	 */
	void exitVersion(FigureParser.VersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#dronetype}.
	 * @param ctx the parse tree
	 */
	void enterDronetype(FigureParser.DronetypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#dronetype}.
	 * @param ctx the parse tree
	 */
	void exitDronetype(FigureParser.DronetypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#mainBody}.
	 * @param ctx the parse tree
	 */
	void enterMainBody(FigureParser.MainBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#mainBody}.
	 * @param ctx the parse tree
	 */
	void exitMainBody(FigureParser.MainBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#positions}.
	 * @param ctx the parse tree
	 */
	void enterPositions(FigureParser.PositionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#positions}.
	 * @param ctx the parse tree
	 */
	void exitPositions(FigureParser.PositionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#velocities}.
	 * @param ctx the parse tree
	 */
	void enterVelocities(FigureParser.VelocitiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#velocities}.
	 * @param ctx the parse tree
	 */
	void exitVelocities(FigureParser.VelocitiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#distance}.
	 * @param ctx the parse tree
	 */
	void enterDistance(FigureParser.DistanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#distance}.
	 * @param ctx the parse tree
	 */
	void exitDistance(FigureParser.DistanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#vector}.
	 * @param ctx the parse tree
	 */
	void enterVector(FigureParser.VectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#vector}.
	 * @param ctx the parse tree
	 */
	void exitVector(FigureParser.VectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#shapes}.
	 * @param ctx the parse tree
	 */
	void enterShapes(FigureParser.ShapesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#shapes}.
	 * @param ctx the parse tree
	 */
	void exitShapes(FigureParser.ShapesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(FigureParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(FigureParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#rectangle}.
	 * @param ctx the parse tree
	 */
	void enterRectangle(FigureParser.RectangleContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#rectangle}.
	 * @param ctx the parse tree
	 */
	void exitRectangle(FigureParser.RectangleContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#circle}.
	 * @param ctx the parse tree
	 */
	void enterCircle(FigureParser.CircleContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#circle}.
	 * @param ctx the parse tree
	 */
	void exitCircle(FigureParser.CircleContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#circumference}.
	 * @param ctx the parse tree
	 */
	void enterCircumference(FigureParser.CircumferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#circumference}.
	 * @param ctx the parse tree
	 */
	void exitCircumference(FigureParser.CircumferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#argumentList3}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList3(FigureParser.ArgumentList3Context ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#argumentList3}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList3(FigureParser.ArgumentList3Context ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#argumentList4}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList4(FigureParser.ArgumentList4Context ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#argumentList4}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList4(FigureParser.ArgumentList4Context ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FigureParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FigureParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#shapeFunction}.
	 * @param ctx the parse tree
	 */
	void enterShapeFunction(FigureParser.ShapeFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#shapeFunction}.
	 * @param ctx the parse tree
	 */
	void exitShapeFunction(FigureParser.ShapeFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#lightsOn}.
	 * @param ctx the parse tree
	 */
	void enterLightsOn(FigureParser.LightsOnContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#lightsOn}.
	 * @param ctx the parse tree
	 */
	void exitLightsOn(FigureParser.LightsOnContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#lightsOff}.
	 * @param ctx the parse tree
	 */
	void enterLightsOff(FigureParser.LightsOffContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#lightsOff}.
	 * @param ctx the parse tree
	 */
	void exitLightsOff(FigureParser.LightsOffContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#move}.
	 * @param ctx the parse tree
	 */
	void enterMove(FigureParser.MoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#move}.
	 * @param ctx the parse tree
	 */
	void exitMove(FigureParser.MoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#rotate}.
	 * @param ctx the parse tree
	 */
	void enterRotate(FigureParser.RotateContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#rotate}.
	 * @param ctx the parse tree
	 */
	void exitRotate(FigureParser.RotateContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#movePos}.
	 * @param ctx the parse tree
	 */
	void enterMovePos(FigureParser.MovePosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#movePos}.
	 * @param ctx the parse tree
	 */
	void exitMovePos(FigureParser.MovePosContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#color}.
	 * @param ctx the parse tree
	 */
	void enterColor(FigureParser.ColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#color}.
	 * @param ctx the parse tree
	 */
	void exitColor(FigureParser.ColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#before}.
	 * @param ctx the parse tree
	 */
	void enterBefore(FigureParser.BeforeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#before}.
	 * @param ctx the parse tree
	 */
	void exitBefore(FigureParser.BeforeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FigureParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FigureParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(FigureParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(FigureParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#pause}.
	 * @param ctx the parse tree
	 */
	void enterPause(FigureParser.PauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#pause}.
	 * @param ctx the parse tree
	 */
	void exitPause(FigureParser.PauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#lightsOnStatement}.
	 * @param ctx the parse tree
	 */
	void enterLightsOnStatement(FigureParser.LightsOnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#lightsOnStatement}.
	 * @param ctx the parse tree
	 */
	void exitLightsOnStatement(FigureParser.LightsOnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FigureParser#after}.
	 * @param ctx the parse tree
	 */
	void enterAfter(FigureParser.AfterContext ctx);
	/**
	 * Exit a parse tree produced by {@link FigureParser#after}.
	 * @param ctx the parse tree
	 */
	void exitAfter(FigureParser.AfterContext ctx);
}