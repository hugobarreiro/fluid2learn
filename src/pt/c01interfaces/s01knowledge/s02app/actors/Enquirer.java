package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

import java.util.Hashtable;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
IBaseConhecimento bc = new BaseConhecimento();
		
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
		
		if (responder.finalAnswer(listaAnimais[animalTestado]))
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

	}

}
