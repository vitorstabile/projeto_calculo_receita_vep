package model.services;

import java.util.List;

import model.entities.Ingrediente;
import model.entities.Receita;

public class ReceitaService {

	public void updateReceitaAdd(Receita receita, Ingrediente ingrediente) {
		receita.addIngrediente(ingrediente);
		receita.setRendBrutoReceita(updateRendBrutoReceita(receita.getIngredientes()));
		receita.setPerdaReceita(updatePerdaReceita(receita));
		receita.setCustoReceita(updateCustoReceita(receita.getIngredientes(), receita));
		updatePorcenIngrediente(receita.getIngredientes(), receita);
	}
	
	public void updateReceitaRemove(Receita receita, Ingrediente ingrediente) {
		receita.removeIngrediente(ingrediente);
		receita.setRendBrutoReceita(updateRendBrutoReceita(receita.getIngredientes()));
		receita.setPerdaReceita(updatePerdaReceita(receita));
		receita.setCustoReceita(updateCustoReceita(receita.getIngredientes(), receita));
		updatePorcenIngrediente(receita.getIngredientes(), receita);
	}

	public double updateRendBrutoReceita(List<Ingrediente> list) {

		double soma = 0;

		for (Ingrediente ingrediente : list) {
			soma = soma + ingrediente.getQtIngrediente();
		}
		return soma;
	}

	public double updatePerdaReceita(Receita receita) {
		
		double perda = ((receita.getRendBrutoReceita() - receita.getRendLiqReceita())/(receita.getRendBrutoReceita()))*100;
		
		return perda;

	}
	
	public double updateCustoReceita(List<Ingrediente> list, Receita receita) {

		double soma = 0;

		for (Ingrediente ingrediente : list) {
			soma = soma + ingrediente.getCustoIngrediente();
		}
		double custo = soma/receita.getRendLiqReceita();
		return custo;
	}
	
	public void updatePorcenIngrediente(List<Ingrediente> list, Receita receita) {

		for (Ingrediente ingrediente : list) {
			ingrediente.setPorcenIngrediente((ingrediente.getQtIngrediente()/receita.getRendBrutoReceita())*100);
		}
	}
}
