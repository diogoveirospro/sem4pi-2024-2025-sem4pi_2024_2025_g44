package gen;
// Generated from FigureParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FigureParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FigureParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FigureParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(FigureParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(FigureParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion(FigureParser.VersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#dronetype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDronetype(FigureParser.DronetypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#mainBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainBody(FigureParser.MainBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#positions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositions(FigureParser.PositionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#velocities}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVelocities(FigureParser.VelocitiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#distance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistance(FigureParser.DistanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#vector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVector(FigureParser.VectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#shapes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShapes(FigureParser.ShapesContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(FigureParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#rectangle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRectangle(FigureParser.RectangleContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#circle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCircle(FigureParser.CircleContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#circumference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCircumference(FigureParser.CircumferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#argumentList3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList3(FigureParser.ArgumentList3Context ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#argumentList4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList4(FigureParser.ArgumentList4Context ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(FigureParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#shapeFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShapeFunction(FigureParser.ShapeFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#lightsOn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLightsOn(FigureParser.LightsOnContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#lightsOff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLightsOff(FigureParser.LightsOffContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(FigureParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#rotate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRotate(FigureParser.RotateContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#movePos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovePos(FigureParser.MovePosContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(FigureParser.ColorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#before}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBefore(FigureParser.BeforeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(FigureParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(FigureParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#pause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPause(FigureParser.PauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#lightsOnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLightsOnStatement(FigureParser.LightsOnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FigureParser#after}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfter(FigureParser.AfterContext ctx);
}