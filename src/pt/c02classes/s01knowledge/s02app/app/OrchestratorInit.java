package pt.c02classes.s01knowledge.s02app.app;

import java.util.*;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

public class OrchestratorInit {

	public static void main(String[] args) {
		
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		IBaseConhecimento base = new BaseConhecimento();
		
		int contador;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Resolver (A)nimals ou (M)aze?");
		String desafio = scanner.nextLine();
		switch (desafio.toUpperCase()){
			case "A":	base.setScenario("animals");
						System.out.println("Pense em um animal e escreva seu nome");
						String animal = scanner.nextLine();
						String listaAnimais[] = base.listaNomes();
						contador=0;
						while(contador<listaAnimais.length){
							if(listaAnimais[contador].equalsIgnoreCase(animal)){
								System.out.println("Enquirer com " + animal + "...");
								stat = new Statistics();
								resp = new ResponderAnimals(stat, animal);
								enq = new EnquirerAnimals();
								enq.connect(resp);
								enq.discover();
								System.out.println("----------------------------------------------------------------------------------------\n");
								break;
							}
							else if(contador==listaAnimais.length-1){
								System.out.println("Animal nao encontrado. Digite outro animal.");
								animal = scanner.nextLine();
								contador=-1;
							}
							contador++;
						}
						break;
			case "M":	base.setScenario("maze");
						System.out.println("Diga qual maze quer resolver");
						String maze = scanner.nextLine();
						String listaMazes[] = base.listaNomes();
						contador=0;
						while(contador<listaMazes.length){
							if(listaMazes[contador].equalsIgnoreCase(maze)){
								System.out.println("Enquirer com " + maze + "...");
								stat = new Statistics();
								resp = new ResponderMaze(stat, maze);
								enq = new EnquirerMaze();
								enq.connect(resp);
								enq.discover();
								System.out.println("----------------------------------------------------------------------------------------\n");
								break;
							}
							else if(contador==listaMazes.length-1){
								System.out.println("Maze nao encontrado. Digite outro maze.");
								maze = scanner.nextLine();
								contador=-1;
							}
							contador++;
						}
						break;
			default:	System.out.println("Opcao invalida!");
		}
		
		scanner.close();
	}

}
