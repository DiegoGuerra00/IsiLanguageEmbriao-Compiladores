// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
    import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, OPSOMA=15, OPSUB=16, 
		OPMUL=17, OPDIV=18, AP=19, FP=20, SC=21, COLON=22, OP=23, ATTR=24, VIR=25, 
		ACH=26, FCH=27, OPREL=28, ID=29, DOUBLE=30, INT=31, STRING=32, WS=33;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdenquanto = 10, RULE_cmdcase = 11, RULE_expr = 12, 
		RULE_expr_ = 13, RULE_termo = 14, RULE_termo_ = 15, RULE_fator = 16;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdselecao", "cmdenquanto", "cmdcase", "expr", "expr_", 
		"termo", "termo_", "fator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'int'", "'double'", "'texto'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'escolha'", "'caso'", "'break'", 
		"'padrao'", "'+'", "'-'", "'*'", "'/'", "'('", "')'", "';'", "':'", null, 
		"'='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "OPSOMA", "OPSUB", "OPMUL", "OPDIV", "AP", "FP", "SC", 
		"COLON", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "DOUBLE", "INT", 
		"STRING", "WS"
	};
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
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int _tipo;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
	    private String _exprEnquanto;
	    private String _exprCase;
	    private ArrayList<AbstractCommand> _caseDefault;
	    private HashMap<String, ArrayList<AbstractCommand>> _caseCommands = new HashMap<String, ArrayList<AbstractCommand>>();
	    private String _caseCondition; // usado para guardar o case e salvar no hashmap com os commandos correspondentes
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
	    private ArrayList<AbstractCommand> listaEnquanto;
	    private ArrayList<AbstractCommand> listaCase;
	    private static final String ANSI_YELLOW = "\u001B[33m";
	    private static final String ANSI_RED = "\u001B[31m	";
	    private static final String ANSI_RESET = "\u001B[0m";
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateJavaCode(){
			program.generateJavaTarget();
		}

	    public void generateDartCode() {
	        program.generateDartTarget();
	    }

	    // Checa a compatibilidade do tipo das variáveis
	    public void checkTypeComp(String id, int targetType, String expr) {
	        IsiVariable var = (IsiVariable) symbolTable.get(id);

	        // Pega o tipo esperado pela variável
	        String type = "";
	        if(var.getType() == 0) {
	               type = "int";
	        }
	        else if(var.getType() == 1) {
	                type = "double";
	        }
	        else {
	                type = "String";
	        }

	        if(var.getType() != targetType) {
	            throw new IsiSemanticException("Type mismatch for variable " + id + ". Expected " + type + ".");
	        }
	    }

	    // Checa se a condição do switch não é double
	    public void checkSwitchType(String id) {
	            IsiVariable var = (IsiVariable) symbolTable.get(id);
	            if(var.getType() == IsiVariable.DOUBLE) {
	                throw new IsiSemanticException("Cannot switch on a double type variable");
	            }
	    }

	    // Checa se os tipos dos cases batem com o switch
	    public void checkCaseType(int targetType, String condition) {
	        boolean isInt = condition.matches("[0-9]*");
	        boolean isString = condition.matches(".*[a-zA-Z].");
	        
	        if(!isString && ! isInt) {
	            throw new IsiSemanticException("You can only switch String and int");
	        }

	        if(!isInt && targetType == IsiVariable.INT) {
	            throw new IsiSemanticException("Case type must be the same of the switch variable");
	        }
	        else if(!isString && targetType == IsiVariable.TEXT) {
	            throw new IsiSemanticException("Case type must be the same of the switch variable");
	        }
	    }

	    public void checkUnused() {
	        ArrayList<IsiSymbol> unnused = symbolTable.getAllNotUsed();
	        for(IsiSymbol symbol: unnused) {
	            System.out.println(ANSI_YELLOW + "WARNING: Variable " + symbol.getName() + " declared but not used!" + ANSI_RESET); 
	        }
	    }

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			decl();
			setState(36);
			bloco();
			setState(37);
			match(T__1);
			  
			                checkUnused();
			                program.setVarTable(symbolTable);
			           	    program.setComandos(stack.pop());
			           
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

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				declaravar();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
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

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			tipo();
			setState(46);
			match(ID);

				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  symbol = new IsiVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  }
				                  else{
				                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
				                  }
			                    
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(48);
				match(VIR);
				setState(49);
				match(ID);

					                  _varName = _input.LT(-1).getText();
					                  _varValue = null;
					                  symbol = new IsiVariable(_varName, _tipo, _varValue);
					                  if (!symbolTable.exists(_varName)){
					                     symbolTable.add(symbol);	
					                  }
					                  else{
					                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
					                  }
				                    
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(SC);
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

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__2);
				 _tipo = IsiVariable.INT;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(T__3);
				 _tipo = IsiVariable.DOUBLE; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				match(T__4);
				 _tipo = IsiVariable.TEXT;  
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

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(67);
				cmd();
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
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

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdenquantoContext cmdenquanto() {
			return getRuleContext(CmdenquantoContext.class,0);
		}
		public CmdcaseContext cmdcase() {
			return getRuleContext(CmdcaseContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				cmdselecao();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				cmdenquanto();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(77);
				cmdcase();
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

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__5);
			setState(81);
			match(AP);
			setState(82);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                        
			setState(84);
			match(FP);
			setState(85);
			match(SC);

			              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var);
			              	stack.peek().add(cmd);
			              
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

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__6);
			setState(89);
			match(AP);
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(90);
				match(ID);
				 verificaID(_input.LT(-1).getText());
					                  _writeID = _input.LT(-1).getText();
				                     
				}
				break;
			case INT:
				{
				setState(92);
				match(INT);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case DOUBLE:
				{
				setState(94);
				match(DOUBLE);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case STRING:
				{
				setState(96);
				match(STRING);
				_writeID = _input.LT(-1).getText();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(100);
			match(FP);
			setState(101);
			match(SC);

			               	    CommandEscrita cmd = new CommandEscrita(_writeID);
			                    stack.peek().add(cmd);
			               
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

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                   
			setState(106);
			match(ATTR);
			 _exprContent = ""; 
			setState(108);
			expr();
			setState(109);
			match(SC);

			               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			               	 stack.peek().add(cmd);
			                 IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
			                 var.setValue(_exprContent);
			                 symbolTable.get(_exprID).setUsed();
			               
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

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__7);
			setState(113);
			match(AP);
			{
			setState(114);
			match(ID);
			}
			 _exprDecision = _input.LT(-1).getText(); 
			setState(116);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(118);
				match(ID);
				}
				break;
			case 2:
				{
				setState(119);
				expr();
				}
				break;
			}
			_exprDecision += _input.LT(-1).getText(); 
			setState(123);
			match(FP);
			setState(124);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                      stack.push(curThread);
			                    
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(126);
				cmd();
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(131);
			match(FCH);

			                       listaTrue = stack.pop();	
			                    
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(133);
				match(T__8);
				setState(134);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(136);
					cmd();
					}
					}
					setState(139); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(141);
				match(FCH);

				                   		listaFalse = stack.pop();
				                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				                   		stack.peek().add(cmd);
				                   	
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

	public static class CmdenquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdenquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdenquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdenquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdenquanto(this);
		}
	}

	public final CmdenquantoContext cmdenquanto() throws RecognitionException {
		CmdenquantoContext _localctx = new CmdenquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdenquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__9);
			setState(147);
			match(AP);
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(148);
				match(ID);
				}
				break;
			case 2:
				{
				setState(149);
				expr();
				}
				break;
			}
			_exprEnquanto = _input.LT(-1).getText();
			setState(153);
			match(OPREL);
			_exprEnquanto += _input.LT(-1).getText();
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(155);
				match(ID);
				}
				break;
			case 2:
				{
				setState(156);
				expr();
				}
				break;
			}
			_exprEnquanto += _input.LT(-1).getText();
			setState(160);
			match(FP);
			setState(161);
			match(ACH);

			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			              
			setState(164); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(163);
				cmd();
				}
				}
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(168);
			match(FCH);

			                listaEnquanto = stack.pop();
			                CommandEnquanto cmd = new CommandEnquanto(_exprEnquanto, listaEnquanto);
			                stack.peek().add(cmd);
			              
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

	public static class CmdcaseContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> COLON() { return getTokens(IsiLangParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(IsiLangParser.COLON, i);
		}
		public List<TerminalNode> SC() { return getTokens(IsiLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(IsiLangParser.SC, i);
		}
		public List<TerminalNode> INT() { return getTokens(IsiLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(IsiLangParser.INT, i);
		}
		public List<TerminalNode> STRING() { return getTokens(IsiLangParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IsiLangParser.STRING, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdcaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdcase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdcase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdcase(this);
		}
	}

	public final CmdcaseContext cmdcase() throws RecognitionException {
		CmdcaseContext _localctx = new CmdcaseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdcase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__10);
			setState(172);
			match(AP);
			setState(173);
			match(ID);

			                    checkSwitchType(_input.LT(-1).getText());
			                    _exprCase = _input.LT(-1).getText();
			                 
			setState(175);
			match(FP);
			setState(176);
			match(ACH);
			setState(191); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(177);
				match(T__11);
				setState(178);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==STRING) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				                                    _caseCondition = _input.LT(-1).getText();
				                                    IsiVariable var = (IsiVariable) symbolTable.get(_exprCase);
				                                    checkCaseType(var.getType(), _caseCondition);
				                               
				setState(180);
				match(COLON);

				                    curThread = new ArrayList<AbstractCommand>();
				                    stack.push(curThread);
				                
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(182);
					cmd();
					}
					}
					setState(185); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(187);
				match(T__12);
				setState(188);
				match(SC);

				                    listaCase = stack.pop();
				                    _caseCommands.put(_caseCondition, listaCase);
				                
				}
				}
				setState(193); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 );
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(195);
				match(T__13);
				setState(196);
				match(COLON);

				                    curThread = new ArrayList<AbstractCommand>();
				                    stack.push(curThread);
				                
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(198);
					cmd();
					}
					}
					setState(201); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(203);
				match(T__12);
				setState(204);
				match(SC);

				                    _caseDefault = stack.pop();
				                    CommandCase cmd = new CommandCase(_exprCase, _caseCommands, _caseDefault);
				                    stack.peek().add(cmd);
				                
				}
			}

			setState(209);
			match(FCH);
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

	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_Context expr_() {
			return getRuleContext(Expr_Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			termo();
			setState(212);
			expr_();
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

	public static class Expr_Context extends ParserRuleContext {
		public TerminalNode OPSOMA() { return getToken(IsiLangParser.OPSOMA, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_Context expr_() {
			return getRuleContext(Expr_Context.class,0);
		}
		public TerminalNode OPSUB() { return getToken(IsiLangParser.OPSUB, 0); }
		public Expr_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr_(this);
		}
	}

	public final Expr_Context expr_() throws RecognitionException {
		Expr_Context _localctx = new Expr_Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPSOMA:
				{
				setState(214);
				match(OPSOMA);
				_exprContent += '+';
				setState(216);
				termo();
				setState(217);
				expr_();
				}
				break;
			case OPSUB:
				{
				setState(219);
				match(OPSUB);
				_exprContent += '-';
				setState(221);
				termo();
				setState(222);
				expr_();
				}
				break;
			case FP:
			case SC:
			case OPREL:
				break;
			default:
				break;
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

	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_Context termo_() {
			return getRuleContext(Termo_Context.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			fator();
			setState(227);
			termo_();
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

	public static class Termo_Context extends ParserRuleContext {
		public TerminalNode OPMUL() { return getToken(IsiLangParser.OPMUL, 0); }
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_Context termo_() {
			return getRuleContext(Termo_Context.class,0);
		}
		public TerminalNode OPDIV() { return getToken(IsiLangParser.OPDIV, 0); }
		public Termo_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo_(this);
		}
	}

	public final Termo_Context termo_() throws RecognitionException {
		Termo_Context _localctx = new Termo_Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_termo_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPMUL:
				{
				setState(229);
				match(OPMUL);
				_exprContent += '*';
				setState(231);
				fator();
				setState(232);
				termo_();
				}
				break;
			case OPDIV:
				{
				setState(234);
				match(OPDIV);
				_exprContent += '/';
				setState(236);
				fator();
				setState(237);
				termo_();
				}
				break;
			case OPSOMA:
			case OPSUB:
			case FP:
			case SC:
			case OPREL:
				break;
			default:
				break;
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

	public static class FatorContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fator);
		try {
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				match(INT);

				                _exprContent += _input.LT(-1).getText();
				                checkTypeComp(_exprID, IsiVariable.INT, _exprContent);
				            
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(DOUBLE);

				                _exprContent += _input.LT(-1).getText();
				                checkTypeComp(_exprID, IsiVariable.DOUBLE, _exprContent);
				            
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(245);
				match(STRING);

				                    _exprContent += _input.LT(-1).getText();
				                    checkTypeComp(_exprID, IsiVariable.TEXT, _exprContent);
				                 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(247);
				match(ID);

				                verificaID(_input.LT(-1).getText());
				                _exprContent += _input.LT(-1).getText();
				                IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
				                checkTypeComp(_exprID, var.getType(), _exprContent);
				            
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(249);
				match(AP);
				_exprContent += _input.LT(-1).getText();
				setState(251);
				expr();
				setState(252);
				match(FP);
				_exprContent += _input.LT(-1).getText();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u0104\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5"+
		"\3\6\3\6\6\6G\n\6\r\6\16\6H\3\7\3\7\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\te\n"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13{\n\13\3\13\3\13\3\13\3\13\3\13\6\13\u0082\n"+
		"\13\r\13\16\13\u0083\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u008c\n\13\r\13"+
		"\16\13\u008d\3\13\3\13\3\13\5\13\u0093\n\13\3\f\3\f\3\f\3\f\5\f\u0099"+
		"\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a0\n\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a7\n"+
		"\f\r\f\16\f\u00a8\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\6\r\u00ba\n\r\r\r\16\r\u00bb\3\r\3\r\3\r\3\r\6\r\u00c2\n\r\r"+
		"\r\16\r\u00c3\3\r\3\r\3\r\3\r\6\r\u00ca\n\r\r\r\16\r\u00cb\3\r\3\r\3\r"+
		"\3\r\5\r\u00d2\n\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00e3\n\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00f2\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0102\n\22\3\22\2\2"+
		"\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\3\3\2!\"\2\u0112\2$\3"+
		"\2\2\2\4+\3\2\2\2\6/\3\2\2\2\bB\3\2\2\2\nD\3\2\2\2\fP\3\2\2\2\16R\3\2"+
		"\2\2\20Z\3\2\2\2\22j\3\2\2\2\24r\3\2\2\2\26\u0094\3\2\2\2\30\u00ad\3\2"+
		"\2\2\32\u00d5\3\2\2\2\34\u00e2\3\2\2\2\36\u00e4\3\2\2\2 \u00f1\3\2\2\2"+
		"\"\u0101\3\2\2\2$%\7\3\2\2%&\5\4\3\2&\'\5\n\6\2\'(\7\4\2\2()\b\2\1\2)"+
		"\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2\2"+
		"\2/\60\5\b\5\2\60\61\7\37\2\2\61\67\b\4\1\2\62\63\7\33\2\2\63\64\7\37"+
		"\2\2\64\66\b\4\1\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\2"+
		"8:\3\2\2\29\67\3\2\2\2:;\7\27\2\2;\7\3\2\2\2<=\7\5\2\2=C\b\5\1\2>?\7\6"+
		"\2\2?C\b\5\1\2@A\7\7\2\2AC\b\5\1\2B<\3\2\2\2B>\3\2\2\2B@\3\2\2\2C\t\3"+
		"\2\2\2DF\b\6\1\2EG\5\f\7\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\13"+
		"\3\2\2\2JQ\5\16\b\2KQ\5\20\t\2LQ\5\22\n\2MQ\5\24\13\2NQ\5\26\f\2OQ\5\30"+
		"\r\2PJ\3\2\2\2PK\3\2\2\2PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\r\3"+
		"\2\2\2RS\7\b\2\2ST\7\25\2\2TU\7\37\2\2UV\b\b\1\2VW\7\26\2\2WX\7\27\2\2"+
		"XY\b\b\1\2Y\17\3\2\2\2Z[\7\t\2\2[d\7\25\2\2\\]\7\37\2\2]e\b\t\1\2^_\7"+
		"!\2\2_e\b\t\1\2`a\7 \2\2ae\b\t\1\2bc\7\"\2\2ce\b\t\1\2d\\\3\2\2\2d^\3"+
		"\2\2\2d`\3\2\2\2db\3\2\2\2ef\3\2\2\2fg\7\26\2\2gh\7\27\2\2hi\b\t\1\2i"+
		"\21\3\2\2\2jk\7\37\2\2kl\b\n\1\2lm\7\32\2\2mn\b\n\1\2no\5\32\16\2op\7"+
		"\27\2\2pq\b\n\1\2q\23\3\2\2\2rs\7\n\2\2st\7\25\2\2tu\7\37\2\2uv\b\13\1"+
		"\2vw\7\36\2\2wz\b\13\1\2x{\7\37\2\2y{\5\32\16\2zx\3\2\2\2zy\3\2\2\2{|"+
		"\3\2\2\2|}\b\13\1\2}~\7\26\2\2~\177\7\34\2\2\177\u0081\b\13\1\2\u0080"+
		"\u0082\5\f\7\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2"+
		"\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\35\2\2\u0086"+
		"\u0092\b\13\1\2\u0087\u0088\7\13\2\2\u0088\u0089\7\34\2\2\u0089\u008b"+
		"\b\13\1\2\u008a\u008c\5\f\7\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2"+
		"\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090"+
		"\7\35\2\2\u0090\u0091\b\13\1\2\u0091\u0093\3\2\2\2\u0092\u0087\3\2\2\2"+
		"\u0092\u0093\3\2\2\2\u0093\25\3\2\2\2\u0094\u0095\7\f\2\2\u0095\u0098"+
		"\7\25\2\2\u0096\u0099\7\37\2\2\u0097\u0099\5\32\16\2\u0098\u0096\3\2\2"+
		"\2\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\b\f\1\2\u009b\u009c"+
		"\7\36\2\2\u009c\u009f\b\f\1\2\u009d\u00a0\7\37\2\2\u009e\u00a0\5\32\16"+
		"\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2"+
		"\b\f\1\2\u00a2\u00a3\7\26\2\2\u00a3\u00a4\7\34\2\2\u00a4\u00a6\b\f\1\2"+
		"\u00a5\u00a7\5\f\7\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6"+
		"\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7\35\2\2"+
		"\u00ab\u00ac\b\f\1\2\u00ac\27\3\2\2\2\u00ad\u00ae\7\r\2\2\u00ae\u00af"+
		"\7\25\2\2\u00af\u00b0\7\37\2\2\u00b0\u00b1\b\r\1\2\u00b1\u00b2\7\26\2"+
		"\2\u00b2\u00c1\7\34\2\2\u00b3\u00b4\7\16\2\2\u00b4\u00b5\t\2\2\2\u00b5"+
		"\u00b6\b\r\1\2\u00b6\u00b7\7\30\2\2\u00b7\u00b9\b\r\1\2\u00b8\u00ba\5"+
		"\f\7\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\7\17\2\2\u00be\u00bf\7"+
		"\27\2\2\u00bf\u00c0\b\r\1\2\u00c0\u00c2\3\2\2\2\u00c1\u00b3\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00d1\3\2"+
		"\2\2\u00c5\u00c6\7\20\2\2\u00c6\u00c7\7\30\2\2\u00c7\u00c9\b\r\1\2\u00c8"+
		"\u00ca\5\f\7\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00c9\3\2"+
		"\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\7\17\2\2\u00ce"+
		"\u00cf\7\27\2\2\u00cf\u00d0\b\r\1\2\u00d0\u00d2\3\2\2\2\u00d1\u00c5\3"+
		"\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7\35\2\2\u00d4"+
		"\31\3\2\2\2\u00d5\u00d6\5\36\20\2\u00d6\u00d7\5\34\17\2\u00d7\33\3\2\2"+
		"\2\u00d8\u00d9\7\21\2\2\u00d9\u00da\b\17\1\2\u00da\u00db\5\36\20\2\u00db"+
		"\u00dc\5\34\17\2\u00dc\u00e3\3\2\2\2\u00dd\u00de\7\22\2\2\u00de\u00df"+
		"\b\17\1\2\u00df\u00e0\5\36\20\2\u00e0\u00e1\5\34\17\2\u00e1\u00e3\3\2"+
		"\2\2\u00e2\u00d8\3\2\2\2\u00e2\u00dd\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\35\3\2\2\2\u00e4\u00e5\5\"\22\2\u00e5\u00e6\5 \21\2\u00e6\37\3\2\2\2"+
		"\u00e7\u00e8\7\23\2\2\u00e8\u00e9\b\21\1\2\u00e9\u00ea\5\"\22\2\u00ea"+
		"\u00eb\5 \21\2\u00eb\u00f2\3\2\2\2\u00ec\u00ed\7\24\2\2\u00ed\u00ee\b"+
		"\21\1\2\u00ee\u00ef\5\"\22\2\u00ef\u00f0\5 \21\2\u00f0\u00f2\3\2\2\2\u00f1"+
		"\u00e7\3\2\2\2\u00f1\u00ec\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2!\3\2\2\2"+
		"\u00f3\u00f4\7!\2\2\u00f4\u0102\b\22\1\2\u00f5\u00f6\7 \2\2\u00f6\u0102"+
		"\b\22\1\2\u00f7\u00f8\7\"\2\2\u00f8\u0102\b\22\1\2\u00f9\u00fa\7\37\2"+
		"\2\u00fa\u0102\b\22\1\2\u00fb\u00fc\7\25\2\2\u00fc\u00fd\b\22\1\2\u00fd"+
		"\u00fe\5\32\16\2\u00fe\u00ff\7\26\2\2\u00ff\u0100\b\22\1\2\u0100\u0102"+
		"\3\2\2\2\u0101\u00f3\3\2\2\2\u0101\u00f5\3\2\2\2\u0101\u00f7\3\2\2\2\u0101"+
		"\u00f9\3\2\2\2\u0101\u00fb\3\2\2\2\u0102#\3\2\2\2\26-\67BHPdz\u0083\u008d"+
		"\u0092\u0098\u009f\u00a8\u00bb\u00c3\u00cb\u00d1\u00e2\u00f1\u0101";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}