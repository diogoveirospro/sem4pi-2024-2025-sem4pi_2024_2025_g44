// Generated from ProposalParser.g4 by ANTLR 4.13.2

package gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProposalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProposalParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProposalParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ProposalParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#proposalPT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalPT(ProposalParser.ProposalPTContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#proposalEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalEN(ProposalParser.ProposalENContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#proposalEN_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalEN_2(ProposalParser.ProposalEN_2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#companyBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompanyBlock(ProposalParser.CompanyBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#companyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompanyName(ProposalParser.CompanyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#companyAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompanyAddress(ProposalParser.CompanyAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#companyVat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompanyVat(ProposalParser.CompanyVatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ProposalParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#proposalId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalId(ProposalParser.ProposalIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#locationInfoPT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocationInfoPT(ProposalParser.LocationInfoPTContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#locationInfoEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocationInfoEN(ProposalParser.LocationInfoENContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#droneEntryPT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDroneEntryPT(ProposalParser.DroneEntryPTContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#droneEntryEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDroneEntryEN(ProposalParser.DroneEntryENContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#figureEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFigureEntry(ProposalParser.FigureEntryContext ctx);
}