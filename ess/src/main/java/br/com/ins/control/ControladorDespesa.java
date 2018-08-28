package br.com.ins.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ins.core.Despesa;
import br.com.ins.dao.DespesaDAO;

@Stateless
public class ControladorDespesa implements Serializable {

	@EJB
	DespesaDAO daoDespesa;

	private static final long serialVersionUID = 1L;

	public void incluiDespesa(Despesa despesaSelecionada) {
		daoDespesa.insereDespesa(despesaSelecionada);

	}

	public void atualizaListaDespesas(List<Despesa> listaDespesas, List<Despesa> listaDespesaExcluida) {
		daoDespesa.atualizaListaDespesas(listaDespesas, listaDespesaExcluida);
	}

	public List<Despesa> retornaDespesas() {
		List<Despesa> listaDespesas = daoDespesa.retornaDespesas();
		return listaDespesas;
	}

}
