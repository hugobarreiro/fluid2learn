package pt.c02classes.s01knowledge.s02app.actors;

import java.util.*;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	Stack<String> caminho = new Stack<String>();
	String direcoes[]={"norte", "leste", "sul", "oeste"};
	
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public String invertercaminho(String original){
		switch (original){
			case "norte":	return "sul";
			case "leste":	return "oeste";
			case "sul":		return "norte";
			default:		return "leste";
		}
	}
	
	public boolean discover() {
		
		String tipo;
		laco:
		for (String pc:direcoes){
			if(caminho.isEmpty()){
				caminho.push("entrada");
			}
			if(!caminho.peek().equalsIgnoreCase(invertercaminho(pc))){
				System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
				tipo="P";
				System.out.println(tipo);
				System.out.println("  --> "+pc);
				String resposta = responder.ask(pc);
				System.out.println("  Resposta: " + resposta);
				switch (resposta){
					case "passagem":	System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
										tipo="M";
										System.out.println(tipo);
										System.out.println("  --> "+pc);
										responder.move(pc);
										System.out.println("  Movimento executado!");
										caminho.push(pc);
										if(discover()){
											if(caminho.peek().equalsIgnoreCase("entrada"))
												break laco;
											else{
												caminho.pop();
												return true;
											}
										}
										break;
					case "saida":		System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
										tipo="M";
										System.out.println(tipo);
										System.out.println("  --> "+pc);
										responder.move(pc);
										System.out.println("  Movimento executado!");
										caminho.pop();
										return true;
					default:			continue;				
				}
			}
		}
		if(caminho.peek().equalsIgnoreCase("entrada")){
			System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
			tipo="F";
			System.out.println(tipo);
			if (responder.finalAnswer("cheguei")){
				System.out.println("Você encontrou a saida!");
				return true;
			}
			else{
				System.out.println("Fuém fuém fuém!");
				return false;
			}
		}
			System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
			tipo="M";
			System.out.println(tipo);
			System.out.println("  --> "+caminho);
			responder.move(invertercaminho(caminho.peek()));
			caminho.pop();
			return false;
	}
}
		
		/*
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
		String tipo = scanner.nextLine();
		while (!tipo.equalsIgnoreCase("F")) {
		   System.out.print("  --> ");
		   String pc = scanner.nextLine();
		   switch (tipo.toUpperCase()) {
		      case "P": String resposta = responder.ask(pc);
		                System.out.println("  Resposta: " + resposta);
		                break;
		      case "M": boolean moveu = responder.move(pc);
		                System.out.println((moveu)?"  Movimento executado!":"Não é possível mover");
		                break;
		   }
			System.out.print("(P)ergunta, (M)ovimento ou (F)im? ");
			tipo = scanner.nextLine();
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		
		scanner.close();
		
		return true;
	}
	
}*/
