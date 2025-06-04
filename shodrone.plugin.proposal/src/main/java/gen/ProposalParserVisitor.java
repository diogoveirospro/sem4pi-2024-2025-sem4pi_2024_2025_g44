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
	 * Visit a parse tree produced by {@link ProposalParser#a1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA1(ProposalParser.A1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#a2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA2(ProposalParser.A2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#a3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA3(ProposalParser.A3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(ProposalParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#company}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompany(ProposalParser.CompanyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(ProposalParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#address}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddress(ProposalParser.AddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#vat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVat(ProposalParser.VatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#c1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC1(ProposalParser.C1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#c2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC2(ProposalParser.C2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#proposaldate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposaldate(ProposalParser.ProposaldateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#proposalnumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProposalnumber(ProposalParser.ProposalnumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(ProposalParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#day}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDay(ProposalParser.DayContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#month}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonth(ProposalParser.MonthContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#year}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYear(ProposalParser.YearContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#d1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitD1(ProposalParser.D1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#d2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitD2(ProposalParser.D2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#e1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE1(ProposalParser.E1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#e2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE2(ProposalParser.E2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#e3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE3(ProposalParser.E3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#f1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF1(ProposalParser.F1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#f2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF2(ProposalParser.F2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#f3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF3(ProposalParser.F3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#g1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitG1(ProposalParser.G1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#g2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitG2(ProposalParser.G2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#g3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitG3(ProposalParser.G3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#h}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitH(ProposalParser.HContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#i1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitI1(ProposalParser.I1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#i2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitI2(ProposalParser.I2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#j1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJ1(ProposalParser.J1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#j2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJ2(ProposalParser.J2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime(ProposalParser.TimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#hour}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHour(ProposalParser.HourContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#minute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinute(ProposalParser.MinuteContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#duration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDuration(ProposalParser.DurationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#k1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitK1(ProposalParser.K1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#k2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitK2(ProposalParser.K2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitL1(ProposalParser.L1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#l2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitL2(ProposalParser.L2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#m1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitM1(ProposalParser.M1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#m2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitM2(ProposalParser.M2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#n1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN1(ProposalParser.N1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#n2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN2(ProposalParser.N2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#o1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitO1(ProposalParser.O1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#o2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitO2(ProposalParser.O2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#p1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP1(ProposalParser.P1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProposalParser#p2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP2(ProposalParser.P2Context ctx);
}