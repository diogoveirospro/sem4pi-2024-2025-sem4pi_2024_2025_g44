package gen;
// Generated from FigureParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FigureParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DSL=1, VERSION=2, DRONETYPE=3, POSITION=4, VELOCITY=5, DISTANCE=6, BEFORE=7, 
		AFTER=8, GROUP=9, ENDBEFORE=10, ENDAFTER=11, ENDGROUP=12, MOVE=13, MOVEPOS=14, 
		ROTATE=15, LIGHTSON=16, LIGHTSOFF=17, PAUSE=18, PI=19, DOT=20, COMMA=21, 
		SEMICOLON=22, LPAREN=23, RPAREN=24, ASSIGN=25, ADD=26, SUB=27, MUL=28, 
		DIV=29, LINE=30, RECTANGLE=31, CIRCLE=32, CIRCUMFERENCE=33, RED=34, YELLOW=35, 
		GREEN=36, VERSIONNUMBER=37, NEGNUMBER=38, POSNUMBER=39, DOUBLENUMBER=40, 
		IDENTIFIER=41, WS=42;
	public static final int
		RULE_start = 0, RULE_header = 1, RULE_version = 2, RULE_dronetype = 3, 
		RULE_mainBody = 4, RULE_positions = 5, RULE_velocities = 6, RULE_distance = 7, 
		RULE_vector = 8, RULE_shapes = 9, RULE_line = 10, RULE_rectangle = 11, 
		RULE_circle = 12, RULE_circumference = 13, RULE_argumentList3 = 14, RULE_argumentList4 = 15, 
		RULE_expression = 16, RULE_shapeFunction = 17, RULE_lightsOn = 18, RULE_lightsOff = 19, 
		RULE_move = 20, RULE_rotate = 21, RULE_movePos = 22, RULE_color = 23, 
		RULE_before = 24, RULE_statement = 25, RULE_group = 26, RULE_pause = 27, 
		RULE_lightsOnStatement = 28, RULE_after = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "header", "version", "dronetype", "mainBody", "positions", "velocities", 
			"distance", "vector", "shapes", "line", "rectangle", "circle", "circumference", 
			"argumentList3", "argumentList4", "expression", "shapeFunction", "lightsOn", 
			"lightsOff", "move", "rotate", "movePos", "color", "before", "statement", 
			"group", "pause", "lightsOnStatement", "after"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'DSL'", "'version'", "'DroneType'", "'Position'", "'Velocity'", 
			"'Distance'", "'before'", "'after'", "'group'", "'endbefore'", "'endafter'", 
			"'endgroup'", "'move'", "'movePos'", "'rotate'", "'lightsOn'", "'lightsOff'", 
			"'pause'", "'PI'", "'.'", "','", "';'", "'('", "')'", "'='", "'+'", "'-'", 
			"'*'", "'/'", "'Line'", "'Rectangle'", "'Circle'", "'Circumference'", 
			"'RED'", "'YELLOW'", "'GREEN'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DSL", "VERSION", "DRONETYPE", "POSITION", "VELOCITY", "DISTANCE", 
			"BEFORE", "AFTER", "GROUP", "ENDBEFORE", "ENDAFTER", "ENDGROUP", "MOVE", 
			"MOVEPOS", "ROTATE", "LIGHTSON", "LIGHTSOFF", "PAUSE", "PI", "DOT", "COMMA", 
			"SEMICOLON", "LPAREN", "RPAREN", "ASSIGN", "ADD", "SUB", "MUL", "DIV", 
			"LINE", "RECTANGLE", "CIRCLE", "CIRCUMFERENCE", "RED", "YELLOW", "GREEN", 
			"VERSIONNUMBER", "NEGNUMBER", "POSNUMBER", "DOUBLENUMBER", "IDENTIFIER", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FigureParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FigureParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public MainBodyContext mainBody() {
			return getRuleContext(MainBodyContext.class,0);
		}
		public TerminalNode EOF() { return getToken(FigureParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			header();
			setState(61);
			mainBody();
			setState(62);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeaderContext extends ParserRuleContext {
		public TerminalNode DSL() { return getToken(FigureParser.DSL, 0); }
		public VersionContext version() {
			return getRuleContext(VersionContext.class,0);
		}
		public DronetypeContext dronetype() {
			return getRuleContext(DronetypeContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(DSL);
			setState(65);
			version();
			setState(66);
			dronetype();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VersionContext extends ParserRuleContext {
		public TerminalNode VERSION() { return getToken(FigureParser.VERSION, 0); }
		public TerminalNode VERSIONNUMBER() { return getToken(FigureParser.VERSIONNUMBER, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(VERSION);
			setState(69);
			match(VERSIONNUMBER);
			setState(70);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DronetypeContext extends ParserRuleContext {
		public TerminalNode DRONETYPE() { return getToken(FigureParser.DRONETYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public DronetypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dronetype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitDronetype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DronetypeContext dronetype() throws RecognitionException {
		DronetypeContext _localctx = new DronetypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dronetype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(DRONETYPE);
			setState(73);
			match(IDENTIFIER);
			setState(74);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MainBodyContext extends ParserRuleContext {
		public PositionsContext positions() {
			return getRuleContext(PositionsContext.class,0);
		}
		public VelocitiesContext velocities() {
			return getRuleContext(VelocitiesContext.class,0);
		}
		public DistanceContext distance() {
			return getRuleContext(DistanceContext.class,0);
		}
		public ShapesContext shapes() {
			return getRuleContext(ShapesContext.class,0);
		}
		public BeforeContext before() {
			return getRuleContext(BeforeContext.class,0);
		}
		public PauseContext pause() {
			return getRuleContext(PauseContext.class,0);
		}
		public LightsOnStatementContext lightsOnStatement() {
			return getRuleContext(LightsOnStatementContext.class,0);
		}
		public AfterContext after() {
			return getRuleContext(AfterContext.class,0);
		}
		public List<GroupContext> group() {
			return getRuleContexts(GroupContext.class);
		}
		public GroupContext group(int i) {
			return getRuleContext(GroupContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MainBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitMainBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainBodyContext mainBody() throws RecognitionException {
		MainBodyContext _localctx = new MainBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_mainBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			positions();
			setState(77);
			velocities();
			setState(78);
			distance();
			setState(79);
			shapes();
			setState(80);
			before();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==GROUP) {
				{
				{
				setState(81);
				group();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(87);
					statement();
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(93);
			pause();
			setState(94);
			lightsOnStatement();
			setState(95);
			after();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PositionsContext extends ParserRuleContext {
		public List<TerminalNode> POSITION() { return getTokens(FigureParser.POSITION); }
		public TerminalNode POSITION(int i) {
			return getToken(FigureParser.POSITION, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(FigureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FigureParser.IDENTIFIER, i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(FigureParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(FigureParser.ASSIGN, i);
		}
		public List<VectorContext> vector() {
			return getRuleContexts(VectorContext.class);
		}
		public VectorContext vector(int i) {
			return getRuleContext(VectorContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(FigureParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(FigureParser.SEMICOLON, i);
		}
		public PositionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitPositions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositionsContext positions() throws RecognitionException {
		PositionsContext _localctx = new PositionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_positions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				match(POSITION);
				setState(98);
				match(IDENTIFIER);
				setState(99);
				match(ASSIGN);
				setState(100);
				vector();
				setState(101);
				match(SEMICOLON);
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITION );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VelocitiesContext extends ParserRuleContext {
		public List<TerminalNode> VELOCITY() { return getTokens(FigureParser.VELOCITY); }
		public TerminalNode VELOCITY(int i) {
			return getToken(FigureParser.VELOCITY, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(FigureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FigureParser.IDENTIFIER, i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(FigureParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(FigureParser.ASSIGN, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(FigureParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(FigureParser.SEMICOLON, i);
		}
		public List<TerminalNode> PI() { return getTokens(FigureParser.PI); }
		public TerminalNode PI(int i) {
			return getToken(FigureParser.PI, i);
		}
		public List<TerminalNode> DIV() { return getTokens(FigureParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(FigureParser.DIV, i);
		}
		public List<TerminalNode> POSNUMBER() { return getTokens(FigureParser.POSNUMBER); }
		public TerminalNode POSNUMBER(int i) {
			return getToken(FigureParser.POSNUMBER, i);
		}
		public List<TerminalNode> DOUBLENUMBER() { return getTokens(FigureParser.DOUBLENUMBER); }
		public TerminalNode DOUBLENUMBER(int i) {
			return getToken(FigureParser.DOUBLENUMBER, i);
		}
		public VelocitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_velocities; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitVelocities(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VelocitiesContext velocities() throws RecognitionException {
		VelocitiesContext _localctx = new VelocitiesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_velocities);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(107);
				match(VELOCITY);
				setState(108);
				match(IDENTIFIER);
				setState(109);
				match(ASSIGN);
				setState(115);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PI:
					{
					setState(110);
					match(PI);
					setState(111);
					match(DIV);
					setState(112);
					match(POSNUMBER);
					}
					break;
				case DOUBLENUMBER:
					{
					setState(113);
					match(DOUBLENUMBER);
					}
					break;
				case POSNUMBER:
					{
					setState(114);
					match(POSNUMBER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(117);
				match(SEMICOLON);
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VELOCITY );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DistanceContext extends ParserRuleContext {
		public TerminalNode DISTANCE() { return getToken(FigureParser.DISTANCE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(FigureParser.ASSIGN, 0); }
		public TerminalNode POSNUMBER() { return getToken(FigureParser.POSNUMBER, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public DistanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distance; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitDistance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistanceContext distance() throws RecognitionException {
		DistanceContext _localctx = new DistanceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_distance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(DISTANCE);
			setState(123);
			match(IDENTIFIER);
			setState(124);
			match(ASSIGN);
			setState(125);
			match(POSNUMBER);
			setState(126);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VectorContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FigureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FigureParser.COMMA, i);
		}
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public List<TerminalNode> NEGNUMBER() { return getTokens(FigureParser.NEGNUMBER); }
		public TerminalNode NEGNUMBER(int i) {
			return getToken(FigureParser.NEGNUMBER, i);
		}
		public List<TerminalNode> POSNUMBER() { return getTokens(FigureParser.POSNUMBER); }
		public TerminalNode POSNUMBER(int i) {
			return getToken(FigureParser.POSNUMBER, i);
		}
		public VectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitVector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VectorContext vector() throws RecognitionException {
		VectorContext _localctx = new VectorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_vector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(LPAREN);
			setState(129);
			_la = _input.LA(1);
			if ( !(_la==NEGNUMBER || _la==POSNUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(130);
			match(COMMA);
			setState(131);
			_la = _input.LA(1);
			if ( !(_la==NEGNUMBER || _la==POSNUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(132);
			match(COMMA);
			setState(133);
			_la = _input.LA(1);
			if ( !(_la==NEGNUMBER || _la==POSNUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(134);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShapesContext extends ParserRuleContext {
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public List<RectangleContext> rectangle() {
			return getRuleContexts(RectangleContext.class);
		}
		public RectangleContext rectangle(int i) {
			return getRuleContext(RectangleContext.class,i);
		}
		public List<CircleContext> circle() {
			return getRuleContexts(CircleContext.class);
		}
		public CircleContext circle(int i) {
			return getRuleContext(CircleContext.class,i);
		}
		public List<CircumferenceContext> circumference() {
			return getRuleContexts(CircumferenceContext.class);
		}
		public CircumferenceContext circumference(int i) {
			return getRuleContext(CircumferenceContext.class,i);
		}
		public ShapesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shapes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitShapes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShapesContext shapes() throws RecognitionException {
		ShapesContext _localctx = new ShapesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_shapes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(140);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LINE:
					{
					setState(136);
					line();
					}
					break;
				case RECTANGLE:
					{
					setState(137);
					rectangle();
					}
					break;
				case CIRCLE:
					{
					setState(138);
					circle();
					}
					break;
				case CIRCUMFERENCE:
					{
					setState(139);
					circumference();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(142); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16106127360L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LineContext extends ParserRuleContext {
		public TerminalNode LINE() { return getToken(FigureParser.LINE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public ArgumentList3Context argumentList3() {
			return getRuleContext(ArgumentList3Context.class,0);
		}
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(LINE);
			setState(145);
			match(IDENTIFIER);
			setState(146);
			match(LPAREN);
			setState(147);
			argumentList3();
			setState(148);
			match(RPAREN);
			setState(149);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RectangleContext extends ParserRuleContext {
		public TerminalNode RECTANGLE() { return getToken(FigureParser.RECTANGLE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public ArgumentList4Context argumentList4() {
			return getRuleContext(ArgumentList4Context.class,0);
		}
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public RectangleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rectangle; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitRectangle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RectangleContext rectangle() throws RecognitionException {
		RectangleContext _localctx = new RectangleContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rectangle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(RECTANGLE);
			setState(152);
			match(IDENTIFIER);
			setState(153);
			match(LPAREN);
			setState(154);
			argumentList4();
			setState(155);
			match(RPAREN);
			setState(156);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CircleContext extends ParserRuleContext {
		public TerminalNode CIRCLE() { return getToken(FigureParser.CIRCLE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public ArgumentList3Context argumentList3() {
			return getRuleContext(ArgumentList3Context.class,0);
		}
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public CircleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circle; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitCircle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CircleContext circle() throws RecognitionException {
		CircleContext _localctx = new CircleContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_circle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(CIRCLE);
			setState(159);
			match(IDENTIFIER);
			setState(160);
			match(LPAREN);
			setState(161);
			argumentList3();
			setState(162);
			match(RPAREN);
			setState(163);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CircumferenceContext extends ParserRuleContext {
		public TerminalNode CIRCUMFERENCE() { return getToken(FigureParser.CIRCUMFERENCE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public ArgumentList3Context argumentList3() {
			return getRuleContext(ArgumentList3Context.class,0);
		}
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public CircumferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circumference; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitCircumference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CircumferenceContext circumference() throws RecognitionException {
		CircumferenceContext _localctx = new CircumferenceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_circumference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(CIRCUMFERENCE);
			setState(166);
			match(IDENTIFIER);
			setState(167);
			match(LPAREN);
			setState(168);
			argumentList3();
			setState(169);
			match(RPAREN);
			setState(170);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentList3Context extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FigureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FigureParser.COMMA, i);
		}
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public ArgumentList3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitArgumentList3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentList3Context argumentList3() throws RecognitionException {
		ArgumentList3Context _localctx = new ArgumentList3Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_argumentList3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			expression();
			setState(173);
			match(COMMA);
			setState(174);
			expression();
			setState(175);
			match(COMMA);
			setState(176);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentList4Context extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FigureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FigureParser.COMMA, i);
		}
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public ArgumentList4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList4; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitArgumentList4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentList4Context argumentList4() throws RecognitionException {
		ArgumentList4Context _localctx = new ArgumentList4Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_argumentList4);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			expression();
			setState(179);
			match(COMMA);
			setState(180);
			expression();
			setState(181);
			match(COMMA);
			setState(182);
			expression();
			setState(183);
			match(COMMA);
			setState(184);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(FigureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FigureParser.IDENTIFIER, i);
		}
		public List<TerminalNode> POSNUMBER() { return getTokens(FigureParser.POSNUMBER); }
		public TerminalNode POSNUMBER(int i) {
			return getToken(FigureParser.POSNUMBER, i);
		}
		public List<TerminalNode> NEGNUMBER() { return getTokens(FigureParser.NEGNUMBER); }
		public TerminalNode NEGNUMBER(int i) {
			return getToken(FigureParser.NEGNUMBER, i);
		}
		public List<TerminalNode> DOUBLENUMBER() { return getTokens(FigureParser.DOUBLENUMBER); }
		public TerminalNode DOUBLENUMBER(int i) {
			return getToken(FigureParser.DOUBLENUMBER, i);
		}
		public List<TerminalNode> ADD() { return getTokens(FigureParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(FigureParser.ADD, i);
		}
		public List<TerminalNode> SUB() { return getTokens(FigureParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(FigureParser.SUB, i);
		}
		public List<TerminalNode> MUL() { return getTokens(FigureParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(FigureParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(FigureParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(FigureParser.DIV, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4123168604160L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1006632960L) != 0)) {
				{
				{
				setState(187);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1006632960L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(188);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4123168604160L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShapeFunctionContext extends ParserRuleContext {
		public LightsOnContext lightsOn() {
			return getRuleContext(LightsOnContext.class,0);
		}
		public LightsOffContext lightsOff() {
			return getRuleContext(LightsOffContext.class,0);
		}
		public MoveContext move() {
			return getRuleContext(MoveContext.class,0);
		}
		public RotateContext rotate() {
			return getRuleContext(RotateContext.class,0);
		}
		public MovePosContext movePos() {
			return getRuleContext(MovePosContext.class,0);
		}
		public ShapeFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shapeFunction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitShapeFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShapeFunctionContext shapeFunction() throws RecognitionException {
		ShapeFunctionContext _localctx = new ShapeFunctionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_shapeFunction);
		try {
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LIGHTSON:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				lightsOn();
				}
				break;
			case LIGHTSOFF:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				lightsOff();
				}
				break;
			case MOVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				move();
				}
				break;
			case ROTATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(197);
				rotate();
				}
				break;
			case MOVEPOS:
				enterOuterAlt(_localctx, 5);
				{
				setState(198);
				movePos();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LightsOnContext extends ParserRuleContext {
		public TerminalNode LIGHTSON() { return getToken(FigureParser.LIGHTSON, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public LightsOnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lightsOn; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitLightsOn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LightsOnContext lightsOn() throws RecognitionException {
		LightsOnContext _localctx = new LightsOnContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lightsOn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(LIGHTSON);
			setState(202);
			match(LPAREN);
			setState(203);
			color();
			setState(204);
			match(RPAREN);
			setState(205);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LightsOffContext extends ParserRuleContext {
		public TerminalNode LIGHTSOFF() { return getToken(FigureParser.LIGHTSOFF, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public LightsOffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lightsOff; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitLightsOff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LightsOffContext lightsOff() throws RecognitionException {
		LightsOffContext _localctx = new LightsOffContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_lightsOff);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(LIGHTSOFF);
			setState(208);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoveContext extends ParserRuleContext {
		public TerminalNode MOVE() { return getToken(FigureParser.MOVE, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public VectorContext vector() {
			return getRuleContext(VectorContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(FigureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FigureParser.COMMA, i);
		}
		public TerminalNode POSNUMBER() { return getToken(FigureParser.POSNUMBER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public MoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_move; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoveContext move() throws RecognitionException {
		MoveContext _localctx = new MoveContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(MOVE);
			setState(211);
			match(LPAREN);
			setState(212);
			vector();
			setState(213);
			match(COMMA);
			setState(214);
			match(POSNUMBER);
			setState(215);
			match(COMMA);
			setState(216);
			match(IDENTIFIER);
			setState(217);
			match(RPAREN);
			setState(218);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RotateContext extends ParserRuleContext {
		public TerminalNode ROTATE() { return getToken(FigureParser.ROTATE, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(FigureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FigureParser.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FigureParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FigureParser.COMMA, i);
		}
		public TerminalNode MUL() { return getToken(FigureParser.MUL, 0); }
		public TerminalNode PI() { return getToken(FigureParser.PI, 0); }
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public TerminalNode NEGNUMBER() { return getToken(FigureParser.NEGNUMBER, 0); }
		public TerminalNode POSNUMBER() { return getToken(FigureParser.POSNUMBER, 0); }
		public RotateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rotate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitRotate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RotateContext rotate() throws RecognitionException {
		RotateContext _localctx = new RotateContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_rotate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(ROTATE);
			setState(221);
			match(LPAREN);
			setState(222);
			match(IDENTIFIER);
			setState(223);
			match(COMMA);
			setState(224);
			match(IDENTIFIER);
			setState(225);
			match(COMMA);
			setState(226);
			_la = _input.LA(1);
			if ( !(_la==NEGNUMBER || _la==POSNUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(227);
			match(MUL);
			setState(228);
			match(PI);
			setState(229);
			match(COMMA);
			setState(230);
			match(IDENTIFIER);
			setState(231);
			match(RPAREN);
			setState(232);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MovePosContext extends ParserRuleContext {
		public TerminalNode MOVEPOS() { return getToken(FigureParser.MOVEPOS, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(FigureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FigureParser.IDENTIFIER, i);
		}
		public TerminalNode COMMA() { return getToken(FigureParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public MovePosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_movePos; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitMovePos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MovePosContext movePos() throws RecognitionException {
		MovePosContext _localctx = new MovePosContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_movePos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(MOVEPOS);
			setState(235);
			match(LPAREN);
			setState(236);
			match(IDENTIFIER);
			setState(237);
			match(COMMA);
			setState(238);
			match(IDENTIFIER);
			setState(239);
			match(RPAREN);
			setState(240);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColorContext extends ParserRuleContext {
		public TerminalNode RED() { return getToken(FigureParser.RED, 0); }
		public TerminalNode YELLOW() { return getToken(FigureParser.YELLOW, 0); }
		public TerminalNode GREEN() { return getToken(FigureParser.GREEN, 0); }
		public ColorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitColor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorContext color() throws RecognitionException {
		ColorContext _localctx = new ColorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_color);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 120259084288L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BeforeContext extends ParserRuleContext {
		public TerminalNode BEFORE() { return getToken(FigureParser.BEFORE, 0); }
		public TerminalNode ENDBEFORE() { return getToken(FigureParser.ENDBEFORE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<GroupContext> group() {
			return getRuleContexts(GroupContext.class);
		}
		public GroupContext group(int i) {
			return getRuleContext(GroupContext.class,i);
		}
		public BeforeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_before; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitBefore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeforeContext before() throws RecognitionException {
		BeforeContext _localctx = new BeforeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_before);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(BEFORE);
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==GROUP || _la==IDENTIFIER) {
				{
				setState(247);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(245);
					statement();
					}
					break;
				case GROUP:
					{
					setState(246);
					group();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(252);
			match(ENDBEFORE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FigureParser.IDENTIFIER, 0); }
		public TerminalNode DOT() { return getToken(FigureParser.DOT, 0); }
		public ShapeFunctionContext shapeFunction() {
			return getRuleContext(ShapeFunctionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(IDENTIFIER);
			setState(255);
			match(DOT);
			setState(256);
			shapeFunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(FigureParser.GROUP, 0); }
		public TerminalNode ENDGROUP() { return getToken(FigureParser.ENDGROUP, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(GROUP);
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(259);
				statement();
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(265);
			match(ENDGROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PauseContext extends ParserRuleContext {
		public TerminalNode PAUSE() { return getToken(FigureParser.PAUSE, 0); }
		public TerminalNode LPAREN() { return getToken(FigureParser.LPAREN, 0); }
		public TerminalNode POSNUMBER() { return getToken(FigureParser.POSNUMBER, 0); }
		public TerminalNode RPAREN() { return getToken(FigureParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(FigureParser.SEMICOLON, 0); }
		public PauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitPause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PauseContext pause() throws RecognitionException {
		PauseContext _localctx = new PauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_pause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PAUSE) {
				{
				setState(267);
				match(PAUSE);
				setState(268);
				match(LPAREN);
				setState(269);
				match(POSNUMBER);
				setState(270);
				match(RPAREN);
				setState(271);
				match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LightsOnStatementContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(FigureParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FigureParser.IDENTIFIER, i);
		}
		public List<TerminalNode> DOT() { return getTokens(FigureParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(FigureParser.DOT, i);
		}
		public List<TerminalNode> LIGHTSON() { return getTokens(FigureParser.LIGHTSON); }
		public TerminalNode LIGHTSON(int i) {
			return getToken(FigureParser.LIGHTSON, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(FigureParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(FigureParser.LPAREN, i);
		}
		public List<ColorContext> color() {
			return getRuleContexts(ColorContext.class);
		}
		public ColorContext color(int i) {
			return getRuleContext(ColorContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(FigureParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(FigureParser.RPAREN, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(FigureParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(FigureParser.SEMICOLON, i);
		}
		public LightsOnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lightsOnStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitLightsOnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LightsOnStatementContext lightsOnStatement() throws RecognitionException {
		LightsOnStatementContext _localctx = new LightsOnStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_lightsOnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(274);
				match(IDENTIFIER);
				setState(275);
				match(DOT);
				setState(276);
				match(LIGHTSON);
				setState(277);
				match(LPAREN);
				setState(278);
				color();
				setState(279);
				match(RPAREN);
				setState(280);
				match(SEMICOLON);
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AfterContext extends ParserRuleContext {
		public TerminalNode AFTER() { return getToken(FigureParser.AFTER, 0); }
		public TerminalNode ENDAFTER() { return getToken(FigureParser.ENDAFTER, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<GroupContext> group() {
			return getRuleContexts(GroupContext.class);
		}
		public GroupContext group(int i) {
			return getRuleContext(GroupContext.class,i);
		}
		public AfterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_after; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FigureParserVisitor ) return ((FigureParserVisitor<? extends T>)visitor).visitAfter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AfterContext after() throws RecognitionException {
		AfterContext _localctx = new AfterContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_after);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(AFTER);
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==GROUP || _la==IDENTIFIER) {
				{
				setState(290);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(288);
					statement();
					}
					break;
				case GROUP:
					{
					setState(289);
					group();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(295);
			match(ENDAFTER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u012a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004S\b\u0004\n\u0004\f\u0004V\t\u0004"+
		"\u0001\u0004\u0005\u0004Y\b\u0004\n\u0004\f\u0004\\\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005h\b\u0005\u000b\u0005"+
		"\f\u0005i\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006t\b\u0006\u0001\u0006"+
		"\u0004\u0006w\b\u0006\u000b\u0006\f\u0006x\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0004"+
		"\t\u008d\b\t\u000b\t\f\t\u008e\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u00be\b\u0010\n\u0010\f\u0010\u00c1\t\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00c8\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u00f8\b\u0018\n\u0018\f\u0018"+
		"\u00fb\t\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0005\u001a\u0105\b\u001a\n\u001a"+
		"\f\u001a\u0108\t\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0111\b\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u011b\b\u001c\n\u001c\f\u001c\u011e\t\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u0123\b\u001d\n\u001d\f\u001d"+
		"\u0126\t\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0000\u0000\u001e\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:\u0000\u0004\u0001\u0000&\'\u0001\u0000&)\u0001\u0000"+
		"\u001a\u001d\u0001\u0000\"$\u0121\u0000<\u0001\u0000\u0000\u0000\u0002"+
		"@\u0001\u0000\u0000\u0000\u0004D\u0001\u0000\u0000\u0000\u0006H\u0001"+
		"\u0000\u0000\u0000\bL\u0001\u0000\u0000\u0000\ng\u0001\u0000\u0000\u0000"+
		"\fv\u0001\u0000\u0000\u0000\u000ez\u0001\u0000\u0000\u0000\u0010\u0080"+
		"\u0001\u0000\u0000\u0000\u0012\u008c\u0001\u0000\u0000\u0000\u0014\u0090"+
		"\u0001\u0000\u0000\u0000\u0016\u0097\u0001\u0000\u0000\u0000\u0018\u009e"+
		"\u0001\u0000\u0000\u0000\u001a\u00a5\u0001\u0000\u0000\u0000\u001c\u00ac"+
		"\u0001\u0000\u0000\u0000\u001e\u00b2\u0001\u0000\u0000\u0000 \u00ba\u0001"+
		"\u0000\u0000\u0000\"\u00c7\u0001\u0000\u0000\u0000$\u00c9\u0001\u0000"+
		"\u0000\u0000&\u00cf\u0001\u0000\u0000\u0000(\u00d2\u0001\u0000\u0000\u0000"+
		"*\u00dc\u0001\u0000\u0000\u0000,\u00ea\u0001\u0000\u0000\u0000.\u00f2"+
		"\u0001\u0000\u0000\u00000\u00f4\u0001\u0000\u0000\u00002\u00fe\u0001\u0000"+
		"\u0000\u00004\u0102\u0001\u0000\u0000\u00006\u0110\u0001\u0000\u0000\u0000"+
		"8\u011c\u0001\u0000\u0000\u0000:\u011f\u0001\u0000\u0000\u0000<=\u0003"+
		"\u0002\u0001\u0000=>\u0003\b\u0004\u0000>?\u0005\u0000\u0000\u0001?\u0001"+
		"\u0001\u0000\u0000\u0000@A\u0005\u0001\u0000\u0000AB\u0003\u0004\u0002"+
		"\u0000BC\u0003\u0006\u0003\u0000C\u0003\u0001\u0000\u0000\u0000DE\u0005"+
		"\u0002\u0000\u0000EF\u0005%\u0000\u0000FG\u0005\u0016\u0000\u0000G\u0005"+
		"\u0001\u0000\u0000\u0000HI\u0005\u0003\u0000\u0000IJ\u0005)\u0000\u0000"+
		"JK\u0005\u0016\u0000\u0000K\u0007\u0001\u0000\u0000\u0000LM\u0003\n\u0005"+
		"\u0000MN\u0003\f\u0006\u0000NO\u0003\u000e\u0007\u0000OP\u0003\u0012\t"+
		"\u0000PT\u00030\u0018\u0000QS\u00034\u001a\u0000RQ\u0001\u0000\u0000\u0000"+
		"SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000"+
		"\u0000UZ\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WY\u00032\u0019"+
		"\u0000XW\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000"+
		"\u0000\u0000Z[\u0001\u0000\u0000\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001"+
		"\u0000\u0000\u0000]^\u00036\u001b\u0000^_\u00038\u001c\u0000_`\u0003:"+
		"\u001d\u0000`\t\u0001\u0000\u0000\u0000ab\u0005\u0004\u0000\u0000bc\u0005"+
		")\u0000\u0000cd\u0005\u0019\u0000\u0000de\u0003\u0010\b\u0000ef\u0005"+
		"\u0016\u0000\u0000fh\u0001\u0000\u0000\u0000ga\u0001\u0000\u0000\u0000"+
		"hi\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000"+
		"\u0000j\u000b\u0001\u0000\u0000\u0000kl\u0005\u0005\u0000\u0000lm\u0005"+
		")\u0000\u0000ms\u0005\u0019\u0000\u0000no\u0005\u0013\u0000\u0000op\u0005"+
		"\u001d\u0000\u0000pt\u0005\'\u0000\u0000qt\u0005(\u0000\u0000rt\u0005"+
		"\'\u0000\u0000sn\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000sr\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0005\u0016\u0000\u0000"+
		"vk\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000"+
		"\u0000xy\u0001\u0000\u0000\u0000y\r\u0001\u0000\u0000\u0000z{\u0005\u0006"+
		"\u0000\u0000{|\u0005)\u0000\u0000|}\u0005\u0019\u0000\u0000}~\u0005\'"+
		"\u0000\u0000~\u007f\u0005\u0016\u0000\u0000\u007f\u000f\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0005\u0017\u0000\u0000\u0081\u0082\u0007\u0000\u0000"+
		"\u0000\u0082\u0083\u0005\u0015\u0000\u0000\u0083\u0084\u0007\u0000\u0000"+
		"\u0000\u0084\u0085\u0005\u0015\u0000\u0000\u0085\u0086\u0007\u0000\u0000"+
		"\u0000\u0086\u0087\u0005\u0018\u0000\u0000\u0087\u0011\u0001\u0000\u0000"+
		"\u0000\u0088\u008d\u0003\u0014\n\u0000\u0089\u008d\u0003\u0016\u000b\u0000"+
		"\u008a\u008d\u0003\u0018\f\u0000\u008b\u008d\u0003\u001a\r\u0000\u008c"+
		"\u0088\u0001\u0000\u0000\u0000\u008c\u0089\u0001\u0000\u0000\u0000\u008c"+
		"\u008a\u0001\u0000\u0000\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e"+
		"\u008f\u0001\u0000\u0000\u0000\u008f\u0013\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0005\u001e\u0000\u0000\u0091\u0092\u0005)\u0000\u0000\u0092\u0093"+
		"\u0005\u0017\u0000\u0000\u0093\u0094\u0003\u001c\u000e\u0000\u0094\u0095"+
		"\u0005\u0018\u0000\u0000\u0095\u0096\u0005\u0016\u0000\u0000\u0096\u0015"+
		"\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u001f\u0000\u0000\u0098\u0099"+
		"\u0005)\u0000\u0000\u0099\u009a\u0005\u0017\u0000\u0000\u009a\u009b\u0003"+
		"\u001e\u000f\u0000\u009b\u009c\u0005\u0018\u0000\u0000\u009c\u009d\u0005"+
		"\u0016\u0000\u0000\u009d\u0017\u0001\u0000\u0000\u0000\u009e\u009f\u0005"+
		" \u0000\u0000\u009f\u00a0\u0005)\u0000\u0000\u00a0\u00a1\u0005\u0017\u0000"+
		"\u0000\u00a1\u00a2\u0003\u001c\u000e\u0000\u00a2\u00a3\u0005\u0018\u0000"+
		"\u0000\u00a3\u00a4\u0005\u0016\u0000\u0000\u00a4\u0019\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a6\u0005!\u0000\u0000\u00a6\u00a7\u0005)\u0000\u0000\u00a7"+
		"\u00a8\u0005\u0017\u0000\u0000\u00a8\u00a9\u0003\u001c\u000e\u0000\u00a9"+
		"\u00aa\u0005\u0018\u0000\u0000\u00aa\u00ab\u0005\u0016\u0000\u0000\u00ab"+
		"\u001b\u0001\u0000\u0000\u0000\u00ac\u00ad\u0003 \u0010\u0000\u00ad\u00ae"+
		"\u0005\u0015\u0000\u0000\u00ae\u00af\u0003 \u0010\u0000\u00af\u00b0\u0005"+
		"\u0015\u0000\u0000\u00b0\u00b1\u0005)\u0000\u0000\u00b1\u001d\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\u0003 \u0010\u0000\u00b3\u00b4\u0005\u0015\u0000"+
		"\u0000\u00b4\u00b5\u0003 \u0010\u0000\u00b5\u00b6\u0005\u0015\u0000\u0000"+
		"\u00b6\u00b7\u0003 \u0010\u0000\u00b7\u00b8\u0005\u0015\u0000\u0000\u00b8"+
		"\u00b9\u0005)\u0000\u0000\u00b9\u001f\u0001\u0000\u0000\u0000\u00ba\u00bf"+
		"\u0007\u0001\u0000\u0000\u00bb\u00bc\u0007\u0002\u0000\u0000\u00bc\u00be"+
		"\u0007\u0001\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00c1"+
		"\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c0!\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c8\u0003$\u0012\u0000\u00c3\u00c8\u0003&\u0013"+
		"\u0000\u00c4\u00c8\u0003(\u0014\u0000\u00c5\u00c8\u0003*\u0015\u0000\u00c6"+
		"\u00c8\u0003,\u0016\u0000\u00c7\u00c2\u0001\u0000\u0000\u0000\u00c7\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c6\u0001\u0000\u0000\u0000\u00c8#\u0001"+
		"\u0000\u0000\u0000\u00c9\u00ca\u0005\u0010\u0000\u0000\u00ca\u00cb\u0005"+
		"\u0017\u0000\u0000\u00cb\u00cc\u0003.\u0017\u0000\u00cc\u00cd\u0005\u0018"+
		"\u0000\u0000\u00cd\u00ce\u0005\u0016\u0000\u0000\u00ce%\u0001\u0000\u0000"+
		"\u0000\u00cf\u00d0\u0005\u0011\u0000\u0000\u00d0\u00d1\u0005\u0016\u0000"+
		"\u0000\u00d1\'\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005\r\u0000\u0000"+
		"\u00d3\u00d4\u0005\u0017\u0000\u0000\u00d4\u00d5\u0003\u0010\b\u0000\u00d5"+
		"\u00d6\u0005\u0015\u0000\u0000\u00d6\u00d7\u0005\'\u0000\u0000\u00d7\u00d8"+
		"\u0005\u0015\u0000\u0000\u00d8\u00d9\u0005)\u0000\u0000\u00d9\u00da\u0005"+
		"\u0018\u0000\u0000\u00da\u00db\u0005\u0016\u0000\u0000\u00db)\u0001\u0000"+
		"\u0000\u0000\u00dc\u00dd\u0005\u000f\u0000\u0000\u00dd\u00de\u0005\u0017"+
		"\u0000\u0000\u00de\u00df\u0005)\u0000\u0000\u00df\u00e0\u0005\u0015\u0000"+
		"\u0000\u00e0\u00e1\u0005)\u0000\u0000\u00e1\u00e2\u0005\u0015\u0000\u0000"+
		"\u00e2\u00e3\u0007\u0000\u0000\u0000\u00e3\u00e4\u0005\u001c\u0000\u0000"+
		"\u00e4\u00e5\u0005\u0013\u0000\u0000\u00e5\u00e6\u0005\u0015\u0000\u0000"+
		"\u00e6\u00e7\u0005)\u0000\u0000\u00e7\u00e8\u0005\u0018\u0000\u0000\u00e8"+
		"\u00e9\u0005\u0016\u0000\u0000\u00e9+\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0005\u000e\u0000\u0000\u00eb\u00ec\u0005\u0017\u0000\u0000\u00ec\u00ed"+
		"\u0005)\u0000\u0000\u00ed\u00ee\u0005\u0015\u0000\u0000\u00ee\u00ef\u0005"+
		")\u0000\u0000\u00ef\u00f0\u0005\u0018\u0000\u0000\u00f0\u00f1\u0005\u0016"+
		"\u0000\u0000\u00f1-\u0001\u0000\u0000\u0000\u00f2\u00f3\u0007\u0003\u0000"+
		"\u0000\u00f3/\u0001\u0000\u0000\u0000\u00f4\u00f9\u0005\u0007\u0000\u0000"+
		"\u00f5\u00f8\u00032\u0019\u0000\u00f6\u00f8\u00034\u001a\u0000\u00f7\u00f5"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8\u00fb"+
		"\u0001\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb\u00f9"+
		"\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005\n\u0000\u0000\u00fd1\u0001"+
		"\u0000\u0000\u0000\u00fe\u00ff\u0005)\u0000\u0000\u00ff\u0100\u0005\u0014"+
		"\u0000\u0000\u0100\u0101\u0003\"\u0011\u0000\u01013\u0001\u0000\u0000"+
		"\u0000\u0102\u0106\u0005\t\u0000\u0000\u0103\u0105\u00032\u0019\u0000"+
		"\u0104\u0103\u0001\u0000\u0000\u0000\u0105\u0108\u0001\u0000\u0000\u0000"+
		"\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000"+
		"\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000"+
		"\u0109\u010a\u0005\f\u0000\u0000\u010a5\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0005\u0012\u0000\u0000\u010c\u010d\u0005\u0017\u0000\u0000\u010d"+
		"\u010e\u0005\'\u0000\u0000\u010e\u010f\u0005\u0018\u0000\u0000\u010f\u0111"+
		"\u0005\u0016\u0000\u0000\u0110\u010b\u0001\u0000\u0000\u0000\u0110\u0111"+
		"\u0001\u0000\u0000\u0000\u01117\u0001\u0000\u0000\u0000\u0112\u0113\u0005"+
		")\u0000\u0000\u0113\u0114\u0005\u0014\u0000\u0000\u0114\u0115\u0005\u0010"+
		"\u0000\u0000\u0115\u0116\u0005\u0017\u0000\u0000\u0116\u0117\u0003.\u0017"+
		"\u0000\u0117\u0118\u0005\u0018\u0000\u0000\u0118\u0119\u0005\u0016\u0000"+
		"\u0000\u0119\u011b\u0001\u0000\u0000\u0000\u011a\u0112\u0001\u0000\u0000"+
		"\u0000\u011b\u011e\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000"+
		"\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d9\u0001\u0000\u0000\u0000"+
		"\u011e\u011c\u0001\u0000\u0000\u0000\u011f\u0124\u0005\b\u0000\u0000\u0120"+
		"\u0123\u00032\u0019\u0000\u0121\u0123\u00034\u001a\u0000\u0122\u0120\u0001"+
		"\u0000\u0000\u0000\u0122\u0121\u0001\u0000\u0000\u0000\u0123\u0126\u0001"+
		"\u0000\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0124\u0125\u0001"+
		"\u0000\u0000\u0000\u0125\u0127\u0001\u0000\u0000\u0000\u0126\u0124\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0005\u000b\u0000\u0000\u0128;\u0001\u0000"+
		"\u0000\u0000\u0010TZisx\u008c\u008e\u00bf\u00c7\u00f7\u00f9\u0106\u0110"+
		"\u011c\u0122\u0124";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}