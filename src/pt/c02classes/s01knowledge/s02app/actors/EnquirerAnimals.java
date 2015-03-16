package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Hashtable;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		
        IBaseConhecimento bc = new BaseConhecimento();
        IObjetoConhecimento obj;
		
		bc.setScenario("animals");
			
        String listaAnimais[] = bc.listaNomes();
        
        int animalTestado;
        Hashtable <String, String> basePerguntas = new Hashtable<String, String>();
        String searcher;

		animal:
        for (animalTestado = 0; animalTestado < listaAnimais.length ; animalTestado++){
        	obj = bc.recuperaObjeto(listaAnimais[animalTestado]);
        	IDeclaracao decl = obj.primeira();
        	
        	pergunta:
			while (decl != null) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				searcher = basePerguntas.get(pergunta);
				
				if(searcher != null){
					if(searcher.equalsIgnoreCase(respostaEsperada)){
						decl = obj.proxima();
						continue pergunta;
					}
					else
						continue animal;
				}
				else{
					String resposta = responder.ask(pergunta);
					basePerguntas.put(pergunta, resposta);
					searcher=basePerguntas.get(pergunta);
					if(searcher.equalsIgnoreCase(respostaEsperada)){
						decl = obj.proxima();
						continue pergunta;
					}
					else
						continue animal;
				}
			}
        	break animal;
        }
		
		if(animalTestado==listaAnimais.length)
			animalTestado--;
		
		boolean acertei = responder.finalAnswer(listaAnimais[animalTestado]);
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

		return acertei;
	}

}
